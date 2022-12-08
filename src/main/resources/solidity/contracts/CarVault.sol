// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "./Ownable.sol";

contract CarVault is Ownable{

    event NewCar(string indexed owner, string indexed vin);
    event ChangeOwner(string indexed newOwner, string indexed vin);
    event NewDocument(string indexed vin, uint256 indexed docId);

    struct Car {
        string owner;
        string vin;
        uint32 kms;
        uint16 cv;
        uint8 numOwners;
    }

    struct CarDocument{
        string vin;
        uint256 docId;
    }

    Car[] private cars;

    mapping(string => Car) vinToCar;
    mapping(string => CarDocument[]) vinToDocuments;
    mapping(string => uint) ownerCarsCount;


    function createCar(string calldata _owner, string calldata _vin, uint32 _kms, uint16 _cv) onlyOwner public {

        vinToCar[_vin] = Car(_owner, _vin, _kms, _cv, 1);
        cars.push(Car(_owner, _vin, _kms, _cv, 1));
        ownerCarsCount[_owner]++;
        emit NewCar(_owner, _vin);
    }

    function changeOwnerShip(string calldata _newOwner, string calldata _vin) onlyOwner public {

        for (uint i = 0; i < cars.length;i++){
            if (keccak256(abi.encodePacked(cars[i].vin)) == keccak256(abi.encodePacked(_vin))){
                cars[i].owner = _newOwner;
                cars[i].numOwners++;
                break;
            }
        }
        emit ChangeOwner(_newOwner, _vin);
    }

    function updateCv (string calldata _vin, uint16 _cv) onlyOwner public {

        for (uint i = 0; i < cars.length;i++){
            if (keccak256(abi.encodePacked(cars[i].vin)) == keccak256(abi.encodePacked(_vin))){
                cars[i].cv = _cv;
                break;
            }
        }
    }

    function updateKms (string calldata _vin, uint32 _kms) onlyOwner public {

        for (uint i = 0; i < cars.length;i++){
            if (keccak256(abi.encodePacked(cars[i].vin)) == keccak256(abi.encodePacked(_vin))){
                cars[i].kms = _kms;
                break;
            }
        }
    }

    function updateKmsAndCv (string calldata _vin, uint32 _kms, uint16 _cv) onlyOwner public {

        for (uint i = 0; i < cars.length;i++){
            if (keccak256(abi.encodePacked(cars[i].vin)) == keccak256(abi.encodePacked(_vin))){
                cars[i].kms = _kms;
                cars[i].cv = _cv;
                break;
            }
        }
    }

    function addDocument(string calldata _vin, uint256 _docId) onlyOwner public {

        vinToDocuments[_vin].push(CarDocument(_vin, _docId));

        emit NewDocument(_vin, _docId);
    }

    function deleteCar(string calldata _vin) onlyOwner public {

        for(uint i = 0;i < cars.length;i++){
            if (keccak256(abi.encodePacked(cars[i].vin)) == keccak256(abi.encodePacked(_vin))) {
                cars[i] = cars[cars.length - 1];
                ownerCarsCount[cars[i].owner]--;
                cars.pop();
                delete(vinToCar[_vin]);
                delete(vinToDocuments[_vin]);
            }
        }
    }

    function deleteDocument(uint256 _docId, string calldata _vin) onlyOwner public {

        CarDocument[] storage docsList = vinToDocuments[_vin];

        for(uint i = 0;i < docsList.length;i++){
            if (docsList[i].docId == _docId){
                docsList[i] = docsList[docsList.length - 1];
                docsList.pop();
                break;
            }
        }
    }

    function getCarsByOwner(string calldata _owner) onlyOwner public view returns (Car[] memory){
        Car[] memory result = new Car[](ownerCarsCount[_owner]);
        uint index;

        for(uint i = 0; i < cars.length;i++){
            if (keccak256(abi.encodePacked(cars[i].owner)) == keccak256(abi.encodePacked(_owner))){
                result[index] = cars[i];
                index++;
            }
        }
        return result;
    }

    function getCars() public view returns (Car[] memory){
        return cars;
    }

    function getCountCarsByOwner(string calldata _owner) onlyOwner public view returns (uint) {
        return ownerCarsCount[_owner];
    }

    function getDocumentsByVin(string calldata _vin) onlyOwner public view returns (CarDocument[] memory){
        return vinToDocuments[_vin];
    }

    function getOwnerByVin(string calldata _vin) onlyOwner public view returns (string memory) {
        return vinToCar[_vin].owner;
    }

    function getKmsByVin(string calldata _vin) onlyOwner public view returns (uint32) {
        return vinToCar[_vin].kms;
    }

    function getCvByVin(string calldata _vin) onlyOwner public view returns (uint16) {
        return vinToCar[_vin].cv;
    }

    function getNumOwnersByVin(string calldata _vin) onlyOwner public view returns (uint8) {
        return vinToCar[_vin].numOwners;
    }

    function getCarByVin(string calldata _vin) onlyOwner public view returns (Car memory) {
        return vinToCar[_vin];
    }
}