# Building Management application - BackEnd 
A Spring Boot application

    By Soumya KUMBAR 

The Application has:
* 4 Related tables - Building, Room, Window, Heater
* 4 Data Transfer Objects(DTO) - BuildingDto, RoomDto, WindowDto, HeaterDto
* 4 Data Access Objects (DAO) - BuildingDao, RoomDao, HeaterDao, WindowDao
* 1 Different types of users

* Total no of unit tests 30
    *  Dao - 12 unit tests
    * endpoints - 25 unit tests

User Credentials:
* ROLE **ADMIN**
    * username: admin
    * password: adminpwd
* Role **USER**
    * username: user
    * password: password


The application is hosted on Clever Cloud at [faircorp-SoumyaKUMBAR](https://app-e342fcf0-ea0d-4a05-a31c-c8c9c99ae109.cleverapps.io/)



# **End-points**


## building-controller (Requires user with user role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api/buildings | returns a list of all buildings|
| **POST** | /api/buildings | creates or updates a new building |
| **GET** | /api/buildings/{id} | returns a building by id|
| **DELETE** | /api/buildings/{id} | deletes a building by id and deletes associated rooms, heaters and windows|


## room-controller (Requires user with user role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api/rooms | returns a list of all rooms|
| **POST** | /api/rooms | creates or updates a new room |
| **GET** | /api/rooms/{id} | returns a specific room by id|
| **DELETE** | /api/rooms/{id} | deletes a room by id and associated windows and heaters|
| **PUT** | /api/rooms/{id}/switchHeater | inverts all heaters statuses in room if it was ON it will become OFF or if it was OFF it will become ON|
| **PUT** | /api/rooms/{id}/switchWindow | inverts all windows statuses in room if it was OPEN it will become CLOSED or if it was CLOSED it will become OPEN|

## window-controller (Requires user with user role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api/windows | returns list of all windows|
| **POST** | /api/windows | creates or updates a new window |
| **GET** | /api/windows/{id} | returns a specific window by id|
| **DELETE** | /api/windows/{id} | deletes a window by id|
| **PUT** | /api/windows/{id}/switch | inverts window's status if it was OPEN it will become CLOSED or if it was CLOSED it will become OPEN|


## heater-controller (Requires user with user role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api/heaters | returns a list of all heaters|
| **POST** | /api/heaters | creates a new heater |
| **GET** | /api/heaters/{id} | returns a specific heater by id|
| **DELETE** | /api/heaters/{id} | deletes a heater by id|
| **PUT** | /api/heaters/{id}/switch | inverts heater's status if it was ON it will become OFF or if it was OFF it will become ON|

## Test-Coverage
All unit testings passed successfully