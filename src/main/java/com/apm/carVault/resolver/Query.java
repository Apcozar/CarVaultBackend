package com.apm.carVault.resolver;

import com.apm.carVault.blockchain.CarVault;
import com.apm.carVault.model.Car;
import com.apm.carVault.model.CarDocument;
import com.apm.carVault.model.User;
import com.apm.carVault.repository.CarRepository;
import com.apm.carVault.repository.UserRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.List;

public class Query implements GraphQLQueryResolver {
    private final static String PRIVATE_KEY = "de18f69bbb0ba4338dd8287961606f73c8617c6b4f7776344ed09c445a893cc7";
    private final static String PUBLIC_KEY = "0xA2a0A9a1cDd2aAFEab2CBdFB7d263D26E36Ba91A";
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    private final static Web3j web3j = Web3j.build(new HttpService(
            "https://rinkeby.infura.io/v3/82f0375fcd6844948892bf0305a6ba2a"));
    private final static Credentials credentials = Credentials.create(PRIVATE_KEY);

    private UserRepository userRepository;
    private CarRepository carRepository;
    private final static CarVault contract = CarVault.load("0xdb5d8175230eebe9ae6a7432bf14b2caff7ce3b7",
            web3j, credentials, GAS_PRICE, GAS_LIMIT);

    public Query( UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    public User getUser(Long id){
        return userRepository.findById(id).get();
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).get(0);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).get(0);
    }

    public Car getCarById(Long id){return carRepository.findById(id).get();}
    public Car getCarByVin(String vin){return carRepository.findByVin(vin).get(0);}
    public List<Car> getCarsByUsername(String username) throws Exception {
        return contract.getCarsByOwner(username).send();}
    public List<BigInteger> getDocumentsIdByVin(String vin) throws Exception {
        return contract.getDocumentsByVin(vin).send();}

}
