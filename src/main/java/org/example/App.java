package org.example;

import org.example.entity.*;
import org.example.enums.FamilyStatus;
import org.example.enums.Gender;
import org.example.enums.HouseType;
import org.example.service.*;
import org.example.service.serviceImpl.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
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
                    " 20. saveOwner\n" +
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
            Scanner scannerString = new Scanner(System.in);
            Scanner scannerLong = new Scanner(System.in);
            String comand = scannerString.nextLine();
            AgencyService agencyService = new AgencyServiceImpl();
            AddressService addressService = new AddressServiceImpl();
            CustomerService customerService = new CustomerServiceImpl();
            OwnerService ownerService = new OwnerServiceImpl();
            HouseService houseService = new HouseServiceImpl();
            RentInfoService rentInfoService = new RentInfoServiceImpl();
            //TODO save
            //  System.out.println(agencyService.saveAgency(new Agency("Iprofi", "+996099878979", address1)));
            // System.out.println(agencyService.saveAgency(new Agency("GX", "+996099878979", address1)));
            switch (comand) {
                case "1" -> {
                    Address address1 = new Address("Бишкек", "Джал", "Токтоналиева");
                    System.out.println(agencyService.saveAgency(new Agency("Aizix", "+996099878979", address1)));
                }

                case "2" -> {
                    System.out.println(agencyService.getAllAgencies());
                }
                case "3" -> {
                    System.out.println("Введите id Agency: ");
                    Long id = scannerLong.nextLong();
                    System.out.println(agencyService.getAgencyById(id));
                }
                case "4" -> {
                    System.out.println("Введите ID Agency которое хотите изменить:");
                    Long idAgency = scannerLong.nextLong();
                    Agency agency = new Agency();
                    System.out.println("Введите Новое имя:");
                    String name = scannerString.nextLine();
                    agency.setName(name);
                    System.out.println(agencyService.updateAgencyById(idAgency, agency));
                }
                case "5" -> {
                    System.out.println("Введите id которое хотите удалить:");
                    Long id = scannerLong.nextLong();
                    agencyService.deleteAgencyById(id);
                }
                case "6" -> {
                    System.out.println(addressService.getAllAddress());
                }
                case "7" -> {
                    System.out.println("Введите id Address: ");
                    Long id = scannerLong.nextLong();
                    System.out.println(addressService.findAddressById(id));
                }
                case "8" -> {
                    System.out.println("Введите id Address которое хотите изменить:");
                    Long id = scannerLong.nextLong();
                    System.out.println("Введите название города:");
                    String city = scannerString.nextLine();
                    System.out.println("Введите Region:");
                    String region = scannerString.nextLine();
                    System.out.println("Введите название улицы:");
                    String street = scannerString.nextLine();
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
                    System.out.println("Введите Название города:");
                    String city = scannerString.nextLine();
                    System.out.println(addressService.getAgencyCountByCityName(city));
                }
                case "11" -> {
                    System.out.println(addressService.groupByRegion());
                }
                case "12" -> {
                    Customer customer = new Customer("Искак", "Turdumambetov", "I@gmail.com", Gender.MALE, "Kyrgyz", FamilyStatus.UNMARRIED);
                    System.out.println(customerService.saveCustomer(customer));
                }
                case "13" -> {
                    System.out.println(customerService.getAllCustomers());
                }
                case "14" -> {
                    System.out.println("Введите id Customer:");
                    Long id = scannerLong.nextLong();
                    System.out.println(customerService.findByCustomerId(id));
                }
                case "15" -> {
                    System.out.println("Введите ID customer:");
                    Long id = scannerLong.nextLong();
                    System.out.println("Введите новое имя  для Customer :");
                    String name = scannerString.nextLine();
                    Customer customer = new Customer();
                    customer.setFirstName(name);
                    System.out.println(customerService.updateCustomerById(id, customer));
                }
                case "16" -> {
                    System.out.println("Введите ID customer которое хотите удалить:");
                    Long id = scannerLong.nextLong();
                    customerService.deleteCustomerById(id);
                }
                case "17" -> {
                    System.out.println(customerService.saveCustomerRentInto(new Customer("Нурмухаммед", "Медетов", "n@gmail.com", Gender.MALE, "Kyrgyz", FamilyStatus.UNMARRIED), 1L, 1L, LocalDate.of(2024, 5, 25), LocalDate.of(2024, 6, 2)));
                }
                case "18" -> {
                    ownerService.assignOwnerToAgency(1L, 1L);
                }
                case "19" -> {
                    ownerService.saveOwner(new Owner("Нурпери", "Medetova", "nn@gmail.com", LocalDate.of(2001, 12, 27), Gender.FEMALE), new House(HouseType.HOUSE, BigDecimal.valueOf(2500.1), 4.6, "Зынк", 3, true));
                }
                case "20" -> {
                    Owner owner = new Owner("Kamchybek", "Turdakunov", "1@gmail.com", LocalDate.of(2000, 11, 11), Gender.MALE);
                    System.out.println(ownerService.saveOwner(owner, new House()));
//
                }
                case "21" -> {
                    System.out.println("Введите ID Owner:");
                    Long id = scannerLong.nextLong();
                    System.out.println(ownerService.findOwnerById(id));
                }
                case "22" -> {
                    System.out.println(ownerService.getAllOwners());

                }
                case "23" -> {
                    System.out.println("Введите ID:");
                    Long id = scannerLong.nextLong();
                    System.out.println(ownerService.deleteOwnerById(id));
                }
                case "24" -> {
                    System.out.println("Введите Id Owner:");
                    Long id = scannerLong.nextLong();
                    Owner owner = new Owner();
                    System.out.println("Введите новое имя Owner:");
                    String name = scannerString.nextLine();
                    owner.setFirstName(name);
                    System.out.println(ownerService.updateOwnerById(id, owner));
                }
                case "25" -> {
                    System.out.println("Введите id Agency:");
                    Long id = scannerLong.nextLong();
                    System.out.println(ownerService.getOwnersByAgencyId(id));
                }
                case "26" -> {
                    System.out.println("Введите ID Owner:");
                    Long id = scannerLong.nextLong();
                    System.out.println(houseService.saveHouse(id, new House(HouseType.MOBILE_HOME, BigDecimal.valueOf(25000), 4.5, "Lux", 5, true)));
                }
                case "27" -> {
                    System.out.println("Введите ID:");
                    Long id = scannerLong.nextLong();
                    System.out.println(houseService.findHouseById(id));
                }
                case "28" -> {
                    System.out.println(houseService.getAllHouses());
                }
                case "29" -> {
                    System.out.println("Введите ID House:");
                    Long id = scannerLong.nextLong();
                    System.out.println(houseService.updateHouseById(id, new House(HouseType.APARTMENT, BigDecimal.valueOf(15000), 3.4, "ORTO", 2, false)));
                }
                case "30" -> {
                    System.out.println("Введите ID которое хотите удалить:");
                    long Id = scannerLong.nextLong();
                    System.out.println(houseService.deleteHouseById(Id));
                }
                case "31" -> {
                    System.out.println("Введите Region:");
                    String region = scannerString.nextLine();
                    System.out.println(houseService.getHousesInRegion(region));
                }
                case "32" -> {
                    System.out.println("Введите ID Agency:");
                    Long id = scannerLong.nextLong();
                    System.out.println(houseService.allHousesByAgencyId(id));
                }
                case "33" -> {
                    System.out.println("Введите ID Owner:");
                    long id = scannerLong.nextLong();
                    System.out.println(houseService.allHousesByOwnerId(id));
                }
                case "34" -> {
                    System.out.println(houseService.housesBetweenDates(LocalDate.of(2000, 11, 11), LocalDate.of(2024, 12, 12)));
                }
                case "35" -> {
                    System.out.println(rentInfoService.rentInfoBetweenDates(LocalDate.of(2018, 12, 12), LocalDate.of(2023, 12, 12)));
                }
                case "36" -> {
                    System.out.println("Введите Id Agency:");
                    Long id = scannerLong.nextLong();
                    System.out.println(rentInfoService.housesByAgencyIdAndDate(id));

                }


            }
        }
    }
}