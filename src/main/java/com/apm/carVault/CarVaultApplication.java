package com.apm.carVault;

import com.apm.carVault.blockchain.CarVault;
import com.apm.carVault.exception.GraphQLErrorAdapter;
import com.apm.carVault.repository.CarImageRepository;
import com.apm.carVault.repository.CarRepository;
import com.apm.carVault.repository.TransactionsRepository;
import com.apm.carVault.repository.UserRepository;
import com.apm.carVault.resolver.Mutation;
import com.apm.carVault.resolver.Query;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class CarVaultApplication extends SpringBootServletInitializer {

	private final static String PRIVATE_KEY = "de18f69bbb0ba4338dd8287961606f73c8617c6b4f7776344ed09c445a893cc7";
	private final static String PUBLIC_KEY = "0xA2a0A9a1cDd2aAFEab2CBdFB7d263D26E36Ba91A";
	private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
	private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

	public static void main(String[] args) throws Exception{

		// Descomentar solo cuando se hacen cambios en el contrato. (Intentar averiguar si se puede ejecutar en el ciclo
		//de vida de maven
		//CarVaultApplication.deploy();

		SpringApplication.run(CarVaultApplication.class, args);
	}

	public static void deploy() throws Exception {
		Web3j web3j = Web3j.build(new HttpService(
				"https://rinkeby.infura.io/v3/82f0375fcd6844948892bf0305a6ba2a"));

		System.out.println("Connected to Ethereum client version: "
			+ web3j.web3ClientVersion().send().getWeb3ClientVersion());

		Credentials credentials = Credentials.create(PRIVATE_KEY);

		System.out.println("Credentials loaded");

		TransactionReceipt transactionReceipt = Transfer.sendFunds(
				web3j, credentials, PUBLIC_KEY,
				BigDecimal.ONE, Convert.Unit.WEI).send();

		System.out.println("Transaction complete" + transactionReceipt.getTransactionHash());

		System.out.println("Deploying smart contract");

		ContractGasProvider contractGasProvider = new DefaultGasProvider();

		CarVault contract = CarVault.deploy(
				web3j,
				credentials,
				contractGasProvider
				 ).send();

		String contractAddress = contract.getContractAddress();
		System.out.println("SMART CONTRACT ADDRESS: " + contractAddress);
		System.out.println("View contract at https://rinkeby.etherscan.io/address/" + contractAddress);
	}

	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors.stream()
						.filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream()
						.filter(e -> !isClientError(e))
						.map(GraphQLErrorAdapter::new)
						.collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}

	@Bean
	public Query query( UserRepository userRepository, CarRepository carRepository) {
		return new Query( userRepository, carRepository);
	}

	@Bean
	public Mutation mutation(UserRepository userRepository, CarRepository carRepository, TransactionsRepository transactionsRepository, CarImageRepository carImageRepository) {
		return new Mutation(userRepository, carRepository, transactionsRepository, carImageRepository);
	}


}
