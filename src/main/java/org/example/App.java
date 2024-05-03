package org.example;

import org.example.config.HibernateConfig;
import org.example.entity.*;
import org.example.enums.FamilyStatus;
import org.example.enums.Gender;
import org.example.enums.HouseType;
import org.example.service.*;
import org.example.service.serviceImpl.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        HibernateConfig.getEntityManagerFactory();
        while (true) {
            System.out.println("AGENCY\n" +
                    " 1.Save\n" +
                    " 2.GetallAgency\n" +
                    " 3.FindByIDAgency\n" +
                    " 4.UpdateAgency\n" +
                    " 5.DeleteAgencyById\n" +
                    " ------------------- Address -------------------\n" +
                    " 6.GetAllAddress\n" +
                    " 7.FindAddressbyId\n" +
                    " 8.UpdateAddress\n" +
                    " 9.GetAlladdressAgency\n" +
                    " 10.CountAgencyByCityName\n" +
                    " 11.GroupByRegion\n" +
                    " ------------------- Customer -------------------\n" +
                    " 12.SaveCustomerOzu\n" +
                    " 13.GetAllCustomer\n" +
                    " 14.FindByCustomerId\n" +
                    " 15.UpdateCustomerById\n" +
                    " 16.DeleteCustomerById\n" +
                    " 17.SaveCustomerWithRent\n" +
                    " ------------------- Owner -------------------\n" +
                    " 18.AssignOwnerToAgency\n" +
                    " 19.SaveOwnerWithHouse\n" +
                    " 20.SaveOwner\n" +
                    " 21.FindByOwnerId\n" +
                    " 22.GetAllOwner\n" +
                    " 23.DeleteOwnerById\n" +
                    " 24.UpdateOwnerById\n" +
                    " 25.getOwnersByAgencyId\n" +
                    " ------------------- House -------------------\n" +
                    " 26.SaveHouseWithOwner\n" +
                    " 27.FindHouseByID\n" +
                    " 28.FindAllHouse\n" +
                    " 29.UpdateHouseById\n" +
                    " 30.DeleteHouseById\n" +
                    " 31.GetHousesInRegion\n" +
                    " 32.AllHousesByAgencyId\n" +
                    " 33.AllHousesByOwnerId\n" +
                    " 34.HousesBetweenDates\n" +
                    " ------------------- RentInfo -------------------\n" +
                    " 35.RentInFoBetweenDate\n" +
                    " 36.HousesByAgencyIDAndDate\n" +
                    " Выберите команду: ");
            Scanner scannerLn = new Scanner(System.in);
            Scanner scannerNum = new Scanner(System.in);
            String command = scannerLn.nextLine();
            AgencyService agencyService = new AgencyServiceImpl();
            AddressService addressService = new AddressServiceImpl();
            CustomerService customerService = new CustomerServiceImpl();
            OwnerService ownerService = new OwnerServiceImpl();
            HouseService houseService = new HouseServiceImpl();
            RentInfoService rentInfoService = new RentInfoServiceImpl();
            switch (command) {
                case "1" -> {
                    System.out.println("Введите название агентства: ");
                    String name = scannerLn.nextLine();

                    System.out.println("Введите номер телефона агентства: ");
                    String phoneNumber = scannerLn.nextLine();

                    System.out.println("Введите название города: ");
                    String city = scannerLn.nextLine();

                    System.out.println("Введите название области: ");
                    String region = scannerLn.nextLine();

                    System.out.println("Введите название улицы: ");
                    String street = scannerLn.nextLine();

                    Address address = new Address(city, region, street);
                    System.out.println(agencyService.saveAgency(new Agency(name, phoneNumber, address)));
                }

                case "2" -> {
                    System.out.println(agencyService.getAllAgencies());
                }
                case "3" -> {
                    System.out.println("Введите id агентства: ");
                    Long id = scannerNum.nextLong();
                    System.out.println(agencyService.getAgencyById(id));
                }
                case "4" -> {
                    System.out.println("Введите ID агентства которого хотите изменить:");
                    Long agencyId = scannerNum.nextLong();
                    Agency agency = new Agency();
                    System.out.println("Введите новое имя:");
                    String name = scannerLn.nextLine();
                    agency.setName(name);

                    System.out.println("Введите новый номер телефона: ");
                    String phoneNumber = scannerLn.nextLine();
                    agency.setPhoneNumber(phoneNumber);
                    System.out.println(agencyService.updateAgencyById(agencyId, agency));
                }
                case "5" -> {
                    System.out.println("Введите id агентства которого хотите удалить:");
                    Long id = scannerNum.nextLong();
                    System.out.println(agencyService.deleteAgencyById(id));
                }
                case "6" -> {
                    System.out.println(addressService.getAllAddress());
                }
                case "7" -> {
                    System.out.println("Введите id адреса: ");
                    Long id = scannerNum.nextLong();
                    System.out.println(addressService.findAddressById(id));
                }
                case "8" -> {
                    System.out.println("Введите id адреса которого хотите изменить:");
                    Long id = scannerNum.nextLong();
                    System.out.println("Введите название города:");
                    String city = scannerLn.nextLine();
                    System.out.println("Введите название области:");
                    String region = scannerLn.nextLine();
                    System.out.println("Введите название улицы:");
                    String street = scannerLn.nextLine();
                    Address address = new Address();
                    address.setCity(city);
                    address.setStreet(street);
                    address.setRegion(region);
                    System.out.println(addressService.updateAddress(id, address));

                }
                case "9" -> {
                    System.out.println(addressService.getAllAgencyWithAddress());
                }
                case "10" -> {
                    System.out.println("Введите название города:");
                    String city = scannerLn.nextLine();
                    System.out.println(addressService.getAgencyCountByCityName(city));
                }
                case "11" -> {
                    System.out.println(addressService.groupByRegion());
                }
                case "12" -> {
                    System.out.println("Введите имя клиента: ");
                    String firstName = scannerLn.nextLine();

                    System.out.println("Введите фамилию клиента: ");
                    String lastName = scannerLn.nextLine();

                    System.out.println("Введите адрес эл-почты клиента: ");
                    String email = scannerLn.nextLine();

                    System.out.println("Введите год рождения клиента: ");
                    int year = scannerNum.nextInt();

                    System.out.println("Введите месяц рождения клиента: ");
                    int month = scannerNum.nextInt();

                    System.out.println("Введите день рождения клиента: ");
                    int day = scannerNum.nextInt();

                    LocalDate dateOfBirth = LocalDate.of(year, month, day);

                    System.out.println("Введите пол клиента (male/female): ");
                    Gender gender;

                    if (scannerLn.nextLine().equalsIgnoreCase("male")) {
                        gender = Gender.MALE;
                    } else if (scannerLn.nextLine().equalsIgnoreCase("female")) {
                        gender = Gender.FEMALE;
                    } else {
                        System.out.println("Не правильный пол!");
                        continue;
                    }

                    System.out.println("Введите национальность клиента: ");
                    String nationality = scannerLn.nextLine();

                    System.out.println("Введите семейный статус клиента (married/unmarried): ");
                    FamilyStatus familyStatus;

                    String familyStatusStr = scannerLn.nextLine();

                    if (familyStatusStr.equalsIgnoreCase("married")) {
                        familyStatus = FamilyStatus.MARRIED;
                    } else if (familyStatusStr.equalsIgnoreCase("unmarried")) {
                        familyStatus = FamilyStatus.MARRIED;
                    } else {
                        System.out.println("Не правильный семейный статаус!");
                        continue;
                    }

                    Customer customer = new Customer(firstName, lastName, email, dateOfBirth, gender, nationality, familyStatus);
                    System.out.println(customerService.saveCustomer(customer));
                }
                case "13" -> {
                    System.out.println(customerService.getAllCustomers());
                }
                case "14" -> {
                    System.out.println("Введите id клиента:");
                    Long id = scannerNum.nextLong();
                    System.out.println(customerService.findByCustomerId(id));
                }
                case "15" -> {
                    System.out.println("Введите id клиента которого хотите обновить: ");
                    Long id = scannerNum.nextLong();

                    System.out.println("Введите новое имя клиента: ");
                    String firstName = scannerLn.nextLine();

                    System.out.println("Введите новую фамилию клиента: ");
                    String lastName = scannerLn.nextLine();

                    System.out.println("Введите новый адрес эл-почты клиента: ");
                    String email = scannerLn.nextLine();

                    System.out.println("Введите год рождения клиента: ");
                    int year = scannerNum.nextInt();

                    System.out.println("Введите месяц рождения клиента: ");
                    int month = scannerNum.nextInt();

                    System.out.println("Введите день рождения клиента: ");
                    int day = scannerNum.nextInt();

                    LocalDate dateOfBirth = LocalDate.of(year, month, day);

                    System.out.println("Введите новый пол клиента (male/female): ");
                    Gender gender;

                    if (scannerLn.nextLine().equalsIgnoreCase("male")) {
                        gender = Gender.MALE;
                    } else if (scannerLn.nextLine().equalsIgnoreCase("female")) {
                        gender = Gender.FEMALE;
                    } else {
                        System.out.println("Не правильный пол!");
                        continue;
                    }

                    System.out.println("Введите новую национальность клиента: ");
                    String nationality = scannerLn.nextLine();

                    System.out.println("Введите новый семейный статус клиента (married/unmarried): ");
                    FamilyStatus familyStatus;

                    String familyStatusStr = scannerLn.nextLine();

                    if (familyStatusStr.equalsIgnoreCase("married")) {
                        familyStatus = FamilyStatus.MARRIED;
                    } else if (familyStatusStr.equalsIgnoreCase("unmarried")) {
                        familyStatus = FamilyStatus.MARRIED;
                    } else {
                        System.out.println("Не правильный семейный статаус!");
                        continue;
                    }

                    System.out.println(customerService.updateCustomerById(id, new Customer(firstName, lastName, email, dateOfBirth, gender, nationality, familyStatus)));
                }
                case "16" -> {
                    System.out.println("Введите ID customer которое хотите удалить:");
                    Long id = scannerNum.nextLong();
                    customerService.deleteCustomerById(id);
                }
                case "17" -> {
                    System.out.println("Введите имя клиента: ");
                    String firstName = scannerLn.nextLine();

                    System.out.println("Введите фамилию клиента: ");
                    String lastName = scannerLn.nextLine();

                    System.out.println("Введите адрес эл-почты клиента: ");
                    String email = scannerLn.nextLine();

                    System.out.println("Введите год рождения клиента: ");
                    int year = scannerNum.nextInt();

                    System.out.println("Введите месяц рождения клиента: ");
                    int month = scannerNum.nextInt();

                    System.out.println("Введите день рождения клиента: ");
                    int day = scannerNum.nextInt();

                    LocalDate dateOfBirth = LocalDate.of(year, month, day);

                    System.out.println("Введите пол клиента (male/female): ");
                    Gender gender;

                    if (scannerLn.nextLine().equalsIgnoreCase("male")) {
                        gender = Gender.MALE;
                    } else if (scannerLn.nextLine().equalsIgnoreCase("female")) {
                        gender = Gender.FEMALE;
                    } else {
                        System.out.println("Не правильный пол!");
                        continue;
                    }

                    System.out.println("Введите национальность клиента: ");
                    String nationality = scannerLn.nextLine();

                    System.out.println("Введите семейный статус клиента (married/unmarried): ");
                    FamilyStatus familyStatus;

                    String familyStatusStr = scannerLn.nextLine();

                    if (familyStatusStr.equalsIgnoreCase("married")) {
                        familyStatus = FamilyStatus.MARRIED;
                    } else if (familyStatusStr.equalsIgnoreCase("unmarried")) {
                        familyStatus = FamilyStatus.MARRIED;
                    } else {
                        System.out.println("Не правильный семейный статаус!");
                        continue;
                    }

                    System.out.println("Введите id дома: ");
                    Long houseId = scannerNum.nextLong();

                    System.out.println("Введите id агентства: ");
                    Long agencyId = scannerNum.nextLong();

                    System.out.println("Введите год начала: ");
                    int yearOfStart = scannerNum.nextInt();

                    System.out.println("Введите месяц начала: ");
                    int monthOfStart = scannerNum.nextInt();

                    System.out.println("Введите день начала: ");
                    int dayOfStart = scannerNum.nextInt();

                    System.out.println("Введите год конца: ");
                    int yearOfEnd = scannerNum.nextInt();

                    System.out.println("Введите месяц конца: ");
                    int monthOfEnd = scannerNum.nextInt();

                    System.out.println("Введите день конца: ");
                    int dayOfEnd = scannerNum.nextInt();

                    System.out.println(customerService.saveCustomerRentInto(new Customer(firstName, lastName, email, dateOfBirth, gender, nationality, familyStatus), houseId, agencyId, LocalDate.of(yearOfStart, monthOfStart, dayOfStart), LocalDate.of(yearOfEnd, monthOfEnd, dayOfEnd)));
                }
                case "18" -> {
                    System.out.println("Введите id продавца: ");
                    Long ownerId = scannerNum.nextLong();

                    System.out.println("Введите id агентства: ");
                    Long agencyId = scannerNum.nextLong();

                    System.out.println(ownerService.assignOwnerToAgency(ownerId, agencyId));
                }
                case "19" -> {
                    System.out.println("Введите имя продавца: ");
                    String firstName = scannerLn.nextLine();

                    System.out.println("Введите фамилию продавца: ");
                    String lastName = scannerLn.nextLine();

                    System.out.println("Введите адрес эл-почты продавца: ");
                    String email = scannerLn.nextLine();

                    System.out.println("Введите год рождения продавца: ");
                    int year = scannerNum.nextInt();

                    System.out.println("Введите месяц рождения продавца: ");
                    int month = scannerNum.nextInt();

                    System.out.println("Введите день рождения продавца: ");
                    int day = scannerNum.nextInt();

                    System.out.println("Введите пол продавца (male/female): ");
                    Gender gender;

                    if (scannerLn.nextLine().equalsIgnoreCase("male")) {
                        gender = Gender.MALE;
                    } else if (scannerLn.nextLine().equalsIgnoreCase("female")) {
                        gender = Gender.FEMALE;
                    } else {
                        System.out.println("Не правильный пол!");
                        continue;
                    }

                    System.out.println("Введите тип дома (house, apartment, mobile_home)");
                    HouseType houseType;

                    String houseTypeStr = scannerLn.nextLine();

                    if (houseTypeStr.equalsIgnoreCase("house")) {
                        houseType = HouseType.HOUSE;
                    } else if (houseTypeStr.equalsIgnoreCase("apartment")) {
                        houseType = HouseType.APARTMENT;
                    } else if (houseTypeStr.equalsIgnoreCase("mobile_home")) {
                        houseType = HouseType.MOBILE_HOME;
                    } else {
                        System.out.println("Не правильный тип дома!");
                        continue;
                    }

                    System.out.println("Введите стоимость дома: ");
                    BigDecimal bigDecimal = new BigDecimal(scannerLn.nextLine());

                    System.out.println("Введите рейтинг дома: ");
                    double rating = scannerNum.nextDouble();

                    System.out.println("Введите описание дома: ");
                    String description = scannerLn.nextLine();

                    System.out.println("Введите количество комнат: ");
                    int room = scannerNum.nextInt();

                    System.out.println("Введите есть ли в комнате мебель или нет (true/false) ");
                    boolean furniture = false;

                    String furnitureStr = scannerLn.nextLine();

                    if (furnitureStr.equalsIgnoreCase("true")) {
                        furniture = true;
                    } else if (furnitureStr.equalsIgnoreCase("false")) {
                        furniture = false;
                    } else {
                        System.out.println("Не правильный вариант!");
                    }

                    House house = new House(houseType, bigDecimal, rating, description, room, furniture);
                    System.out.println(ownerService.saveOwnerWithHouse(new Owner(firstName, lastName, email, LocalDate.of(year, month, day), gender, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()), house));
                }
                case "20" -> {
                    System.out.println("Введите имя продавца: ");
                    String firstName = scannerLn.nextLine();

                    System.out.println("Введите фамилию продавца: ");
                    String lastName = scannerLn.nextLine();

                    System.out.println("Введите адрес эл-почты продавца: ");
                    String email = scannerLn.nextLine();

                    System.out.println("Введите год рождения продавца: ");
                    int year = scannerNum.nextInt();

                    System.out.println("Введите месяц рождения продавца: ");
                    int month = scannerNum.nextInt();

                    System.out.println("Введите день рождения продавца: ");
                    int day = scannerNum.nextInt();

                    System.out.println("Введите пол продавца (male/female): ");
                    Gender gender;

                    if (scannerLn.nextLine().equalsIgnoreCase("male")) {
                        gender = Gender.MALE;
                    } else if (scannerLn.nextLine().equalsIgnoreCase("female")) {
                        gender = Gender.FEMALE;
                    } else {
                        System.out.println("Не правильный пол!");
                        continue;
                    }

                    Owner owner = new Owner(firstName, lastName, email, LocalDate.of(year, month, day), gender, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                    System.out.println(ownerService.saveOwner(owner));
                }
                case "21" -> {
                    System.out.println("Введите ID продавца:");
                    Long id = scannerNum.nextLong();
                    System.out.println(ownerService.findOwnerById(id));
                }
                case "22" -> {
                    System.out.println(ownerService.getAllOwners());
                }
                case "23" -> {
                    System.out.println("Введите ID продавца которого хотите удалить:");
                    Long id = scannerNum.nextLong();
                    System.out.println(ownerService.deleteOwnerById(id));
                }
                case "24" -> {
                    System.out.println("Введите ID продавца котрого хотите обновить:");
                    Long id = scannerNum.nextLong();
                    Owner owner = new Owner();

                    System.out.println("Введите новое имя продавца: ");
                    owner.setFirstName(scannerLn.nextLine());

                    System.out.println("Введите новую фамилию продавца: ");
                    owner.setLastName(scannerLn.nextLine());

                    System.out.println("Введите новый адрес эл-почты продавца: ");
                    owner.setEmail(scannerLn.nextLine());

                    System.out.println("Введите год рождения продавца: ");
                    int year = scannerNum.nextInt();

                    System.out.println("Введите месяц рождения продавца: ");
                    int month = scannerNum.nextInt();

                    System.out.println("Введите день рождения продавца: ");
                    int day = scannerNum.nextInt();

                    owner.setDateOfBirth(LocalDate.of(year, month, day));

                    System.out.println("Введите пол продавца (male/female): ");
                    Gender gender;

                    if (scannerLn.nextLine().equalsIgnoreCase("male")) {
                        gender = Gender.MALE;
                    } else if (scannerLn.nextLine().equalsIgnoreCase("female")) {
                        gender = Gender.FEMALE;
                    } else {
                        System.out.println("Не правильный пол!");
                        continue;
                    }

                    owner.setGender(gender);

                    System.out.println(ownerService.updateOwnerById(id, owner));
                }
                case "25" -> {
                    System.out.println("Введите id агентства:");
                    Long id = scannerNum.nextLong();
                    System.out.println(ownerService.getOwnersByAgencyId(id));
                }
                case "26" -> {
                    System.out.println("Введите ID продовца:");
                    Long id = scannerNum.nextLong();

                    System.out.println("Введите тип дома (house, apartment, mobile_home)");
                    HouseType houseType;

                    String houseTypeStr = scannerLn.nextLine();

                    if (houseTypeStr.equalsIgnoreCase("house")) {
                        houseType = HouseType.HOUSE;
                    } else if (houseTypeStr.equalsIgnoreCase("apartment")) {
                        houseType = HouseType.APARTMENT;
                    } else if (houseTypeStr.equalsIgnoreCase("mobile_home")) {
                        houseType = HouseType.MOBILE_HOME;
                    } else {
                        System.out.println("Не правильный тип дома!");
                        continue;
                    }

                    System.out.println("Введите стоимость дома: ");
                    BigDecimal bigDecimal = new BigDecimal(scannerLn.nextLine());

                    System.out.println("Введите рейтинг дома: ");
                    double rating = scannerNum.nextDouble();

                    System.out.println("Введите описание дома: ");
                    String description = scannerLn.nextLine();

                    System.out.println("Введите количество комнат: ");
                    int room = scannerNum.nextInt();

                    System.out.println("Введите есть ли в комнате мебель или нет (true/false) ");
                    boolean furniture = scannerLn.nextBoolean();

                    House house = new House(houseType, bigDecimal, rating, description, room, furniture);
                    System.out.println(houseService.saveHouse(id, house));
                }
                case "27" -> {
                    System.out.println("Введите ID дома которого хотите найти:");
                    Long id = scannerNum.nextLong();
                    System.out.println(houseService.findHouseById(id));
                }
                case "28" -> {
                    System.out.println(houseService.getAllHouses());
                }
                case "29" -> {
                    System.out.println("Введите ID дома которого хотите обновить:");
                    Long id = scannerNum.nextLong();

                    System.out.println("Введите новый тип дома (house, apartment, mobile_home)");
                    HouseType houseType;

                    String houseTypeStr = scannerLn.nextLine();

                    if (houseTypeStr.equalsIgnoreCase("house")) {
                        houseType = HouseType.HOUSE;
                    } else if (houseTypeStr.equalsIgnoreCase("apartment")) {
                        houseType = HouseType.APARTMENT;
                    } else if (houseTypeStr.equalsIgnoreCase("mobile_home")) {
                        houseType = HouseType.MOBILE_HOME;
                    } else {
                        System.out.println("Не правильный тип дома!");
                        continue;
                    }

                    System.out.println("Введите новую стоимость дома: ");
                    BigDecimal bigDecimal = new BigDecimal(scannerLn.nextLine());

                    System.out.println("Введите новый рейтинг дома: ");
                    double rating = scannerNum.nextDouble();

                    System.out.println("Введите новое описание дома: ");
                    String description = scannerLn.nextLine();

                    System.out.println("Введите новое количество комнат: ");
                    int room = scannerNum.nextInt();

                    System.out.println("Введите есть ли в комнате мебель или нет (true/false) ");
                    boolean furniture = scannerLn.nextBoolean();


                    House newHouse = new House(houseType, bigDecimal, rating, description, room, furniture);
                    System.out.println(houseService.updateHouseById(id, newHouse));
                }
                case "30" -> {
                    System.out.println("Введите ID дома которого хотите удалить:");
                    long Id = scannerNum.nextLong();
                    System.out.println(houseService.deleteHouseById(Id));
                }
                case "31" -> {
                    System.out.println("Введите область:");
                    String region = scannerLn.nextLine();
                    System.out.println(houseService.getHousesInRegion(region));
                }
                case "32" -> {
                    System.out.println("Введите ID агентства:");
                    Long id = scannerNum.nextLong();
                    System.out.println(houseService.allHousesByAgencyId(id));
                }
                case "33" -> {
                    System.out.println("Введите ID продавца:");
                    long id = scannerNum.nextLong();
                    System.out.println(houseService.allHousesByOwnerId(id));
                }
                case "34" -> {
                    System.out.println("Введите год начала: ");
                    int yearOfStart = scannerNum.nextInt();

                    System.out.println("Введите месяц начала: ");
                    int monthOfStart = scannerNum.nextInt();

                    System.out.println("Введите день начала: ");
                    int dayOfStart = scannerNum.nextInt();

                    System.out.println("Введите год конца: ");
                    int yearOfEnd = scannerNum.nextInt();

                    System.out.println("Введите месяц конца: ");
                    int monthOfEnd = scannerNum.nextInt();

                    System.out.println("Введите день конца: ");
                    int dayOfEnd = scannerNum.nextInt();
                    System.out.println(houseService.housesBetweenDates(LocalDate.of(yearOfStart, monthOfStart, dayOfStart), LocalDate.of(yearOfEnd, monthOfEnd, dayOfEnd)));
                }
                case "35" -> {
                    System.out.println("Введите год начала: ");
                    int yearOfStart = scannerNum.nextInt();

                    System.out.println("Введите месяц начала: ");
                    int monthOfStart = scannerNum.nextInt();

                    System.out.println("Введите день начала: ");
                    int dayOfStart = scannerNum.nextInt();

                    System.out.println("Введите год конца: ");
                    int yearOfEnd = scannerNum.nextInt();

                    System.out.println("Введите месяц конца: ");
                    int monthOfEnd = scannerNum.nextInt();

                    System.out.println("Введите день конца: ");
                    int dayOfEnd = scannerNum.nextInt();
                    System.out.println(rentInfoService.rentInfoBetweenDates(LocalDate.of(yearOfStart, monthOfStart, dayOfStart), LocalDate.of(yearOfEnd, monthOfEnd, dayOfEnd)));
                }
                case "36" -> {
                    System.out.println("Введите ID агентства:");
                    Long id = scannerNum.nextLong();
                    System.out.println(rentInfoService.housesByAgencyIdAndDate(id));
                }
            }
        }
    }
}