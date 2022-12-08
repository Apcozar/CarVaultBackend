package com.apm.carVault.resolver;

import com.apm.carVault.blockchain.CarVault;
import com.apm.carVault.model.Car;
import com.apm.carVault.model.CarDocument;
import com.apm.carVault.model.User;
import com.apm.carVault.model.*;
import com.apm.carVault.repository.CarImageRepository;
import com.apm.carVault.repository.CarRepository;
import com.apm.carVault.repository.TransactionsRepository;
import com.apm.carVault.repository.UserRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.awt.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Mutation implements GraphQLMutationResolver {
    private final static String PRIVATE_KEY = "de18f69bbb0ba4338dd8287961606f73c8617c6b4f7776344ed09c445a893cc7";
    private final static String PUBLIC_KEY = "0xA2a0A9a1cDd2aAFEab2CBdFB7d263D26E36Ba91A";
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    private UserRepository userRepository;
    private CarRepository carRepository;
    private TransactionsRepository transactionsRepository;
    private CarImageRepository carImageRepository;

    private final static Web3j web3j = Web3j.build(new HttpService(
            "https://rinkeby.infura.io/v3/82f0375fcd6844948892bf0305a6ba2a"));
    private final static  Credentials credentials = Credentials.create(PRIVATE_KEY);
    private final static CarVault contract = CarVault.load("0xdb5d8175230eebe9ae6a7432bf14b2caff7ce3b7",
            web3j, credentials, GAS_PRICE, GAS_LIMIT);

    public Mutation( UserRepository userRepository, CarRepository carRepository, TransactionsRepository transactionsRepository, CarImageRepository carImageRepository) {
        this.userRepository= userRepository;
        this.carRepository=carRepository;
        this.transactionsRepository=transactionsRepository;
        this.carImageRepository=carImageRepository;
    }

    public Long deleteCarImage(Long imageId){
        Optional<CarImage> optionalCarImage = carImageRepository.findById(imageId);
        if(optionalCarImage.isPresent()){
            carImageRepository.delete(optionalCarImage.get());
            return imageId;
        }
        return null;
    }

    public Car newCar(Long userId, String vin, String brand, String model, String description, int kilometers, int horsepower,  int year, String address, String manufacturer, String origin, String fuel, String color) throws Exception {
       Optional<User> optionalUser = userRepository.findById(userId);
       List<Car> foundCars = carRepository.findByVin(vin);
        if(foundCars.size() >0){
            throw new Error ("Car with this vin already exists");
        }

       if(optionalUser.isPresent()){
           Car car = new Car();
           User user = optionalUser.get();
           car.setOwner(user);
           car.setVin(vin);
           car.setBrand(brand);
           car.setModel(model);
           car.setAddress(address);
           car.setColor(color);
           car.setDescription(description);
           car.setFuel(fuel);
           car.setKilometers(kilometers);
           car.setManufacturer(manufacturer);
           car.setOrigin(origin);
           car.setYear(year);
           car.setHorsepower(horsepower);

           carRepository.save(car);

           TransactionReceipt receipt = contract.createCar(user.getUsername(),
                   vin,BigInteger.valueOf(kilometers),BigInteger.valueOf(horsepower)).send();

            addTransaction(car, receipt);

           //List<Car> cars = contract.getCarsByOwner("Christian").send();
           //System.out.println(cars.get(0).getOwner().getUsername());

           return car;
       }
       return null;
    }

    public Car transferCar(Long carId, Long userId) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Car> optionalCar = carRepository.findById(carId);

        if(optionalUser.isPresent() && optionalCar.isPresent()){
           Car car = optionalCar.get();
           User user = optionalUser.get();
           car.setOwner(user);

           carRepository.save(car);


           return car;
        }
        return null;

    }

    private void addTransaction(Car car, TransactionReceipt receipt) throws IOException {
        String transactionHash = receipt.getTransactionHash();
        DefaultBlockParameter blockParameter = new DefaultBlockParameterNumber(receipt.getBlockNumber());
        EthBlock ethBlock =
                web3j.ethGetBlockByNumber(blockParameter,true).send();
        BigInteger timestamp = ethBlock.getBlock().getTimestamp();

        CarTransaction newTransaction = new CarTransaction();

        newTransaction.setCar(car);
        newTransaction.setHash(transactionHash);
        newTransaction.setDate(new Date(timestamp.longValue()));

        transactionsRepository.save(newTransaction);
    }

    public Long deleteCar(Long carId) throws Exception {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if(optionalCar.isPresent()){
            Car car = optionalCar.get();
            carRepository.delete(car);

            TransactionReceipt receipt = contract.deleteCar(car.getVin()).send();
            addTransaction(car, receipt);
        }
        return carId;
    }

    public User newUser(String username, String email, String firstname, String surname, String phone, String profilePicture){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setFirstname(firstname);
        user.setSurname(surname);
        user.setProfilePicture(profilePicture);

        userRepository.save(user);

        return user;
    }

    public User updateUser(Long userId, String firstname, String surname, String phone, String profilePicture){
        Optional<User> foundUser = userRepository.findById(userId);

        if(foundUser.isPresent()){
            User user = foundUser.get();

          if(phone!=null){
              user.setPhone(phone);
          }
           if(firstname!=null){
               user.setFirstname(firstname);
           }
           if(surname!=null){
               user.setSurname(surname);
           }
            if(profilePicture!=null){
                user.setProfilePicture(profilePicture);
            }
            userRepository.save(user);

            return user;

        }
        return null;
    }

    public Car updateCar(Long carId, String vin, String brand, String model, String description, int kilometers, int horsepower,  int year, String address, String manufacturer, String origin, String fuel, String color, List<String> images) throws Exception {
        Optional<Car> foundCar = carRepository.findById(carId);

        if (foundCar.isPresent()) {
            Car car = foundCar.get();
            if(vin !=null){
                car.setVin(vin);
            }
            if(brand!=null) {
                car.setBrand(brand);
            }
            if(model!=null){
                car.setModel(model);
            }

            if(address!=null){
                car.setAddress(address);
            }
            if(color!=null){
                car.setColor(color);
            }

            if(description!=null){
                car.setDescription(description);
            }

            if(fuel!=null){
                car.setFuel(fuel);
            }

            car.setKilometers(kilometers);
            TransactionReceipt updateKmReceipt = contract.updateKms(carId.toString(),BigInteger.valueOf(kilometers)).send();
            addTransaction(car,updateKmReceipt);

            if(manufacturer!=null){
                car.setManufacturer(manufacturer);
            }
            if(origin!=null){
                car.setOrigin(origin);
            }

            if(year!=0){
                car.setYear(year);
            }
            if(horsepower!=0){
                car.setHorsepower(horsepower);
                TransactionReceipt updateCvReceipt = contract.updateCv(carId.toString(),BigInteger.valueOf(horsepower)).send();
                addTransaction(car,updateCvReceipt);
            }

            if(images!=null){
                car.setImages(images.stream().map(image -> new CarImage(image,car)).collect(Collectors.toList()));
            }
            carRepository.save(car);
            return car;
        }
        return null;
    }

    private Date getDocumentDate (CarDocumentInput documentInput){
        if(documentInput.getDocumentDate() != null){
            return documentInput.getDocumentDate();

        }
        else{
            return new Date();
        }
    }

    //check
    public Car updateCarDocuments(Long carId, List<CarDocumentInput> documents) {
        Optional<Car> foundCar = carRepository.findById(carId);

        if (foundCar.isPresent()) {
            Car car = foundCar.get();

            car.setDocuments(documents.stream().map(carDocumentInput -> new CarDocument(carDocumentInput.getContent(), carDocumentInput.getDocumentType(), carDocumentInput.getDocumentName(), car, getDocumentDate(carDocumentInput) )).collect(Collectors.toList()));
            carRepository.save(car);

            return car;
        }
        return null;
    }
}
