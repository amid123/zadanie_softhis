/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.tools.datagenerator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pl.arek.zadanko.model.Customer;
import pl.arek.zadanko.model.Order;
import pl.arek.zadanko.model.OrderElement;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
public class SimpleDataGenerator extends RandomGenerator {

    public final List<String> names = new ArrayList<>();
    public final List<String> surnames = new ArrayList<>();
    public final List<String> products = new ArrayList<>();

    // max price
    private int maxRandomPrice = 2000;

    /**
     * just some dardcoded data here...
     */
    public SimpleDataGenerator() {

        names.add("Anna");
        names.add("Maria");
        names.add("Angela");
        names.add("Monika");
        names.add("Agnieszka");
        names.add("Agnieszka");
        names.add("Natalia");
        names.add("Andzrzej");
        names.add("Ferdynand");
        names.add("Mateusz");
        names.add("Adam");
        names.add("Wojciech");
        names.add("Robert");
        names.add("Jerzy");
        names.add("Jakub");
        names.add("Patryk");
        names.add("Alekasnder");
        names.add("Lech");
        names.add("Antoni");
        names.add("Marian");

        surnames.add("Kowalski");
        surnames.add("Nowak");
        surnames.add("Brzozowski");
        surnames.add("Kaczynski");
        surnames.add("Ziobro");
        surnames.add("Macierewicz");
        surnames.add("Kopacz");
        surnames.add("Tusk");
        surnames.add("Szydlo");
        surnames.add("Niesiolowski");
        surnames.add("Grodzki");
        surnames.add("Szpak");
        surnames.add("Kiepski");
        surnames.add("Aleksandrowicz");
        surnames.add("Wyrostek");
        surnames.add("Gibala");
        surnames.add("Marcinkiewicz");
        surnames.add("Mrozowicz");
        surnames.add("Olechowski");
        surnames.add("Duda");
        surnames.add("Thrump");

        products.add("Komputer");
        products.add("Myszka");
        products.add("Monitor");
        products.add("Klawiatura");
        products.add("Kawa");
        products.add("Herbata");
    }

    /**
     * entry point for data generation
     *
     * @param clientsNo
     * @param maxOrders
     * @param maxElems
     * @return
     */
    public List<Customer> generateData(int clientsNo, int maxOrders, int maxElems) {
        List<Customer> customers = generateClients(clientsNo, maxOrders, maxElems);
        return customers;
    }

    /**
     * generate clients
     *
     * @param clientsNo
     * @param maxOrders
     * @param maxElems
     * @return
     */
    private List<Customer> generateClients(int clientsNo, int maxOrders, int maxElems) {
        List<Customer> customers = new ArrayList();
        for (int i = 0; i < clientsNo; i++) {

            List<Order> orders = generateOrders(maxOrders, maxElems);

            Customer customer = new Customer(randomName(), randomSurname());
            customer.setOrders(orders);
            customers.add(customer);
        }
        return customers;
    }

    /**
     * generates orders consumes maxOrders and maxElems
     *
     * @param maxOrders maxiumum orders on the list
     * @param maxElems
     * @return
     */
    private List<Order> generateOrders(int maxOrders, int maxElems) {
        List<Order> orders = new ArrayList();

        for (int i = 0; i < intRandom(maxOrders); i++) {

            List<OrderElement> elements = generateElements(maxElems);

            Order order = new Order(randomDate());
            order.setOrderElement(elements);
            orders.add(order);
        }
        return orders;
    }

    /**
     * generates list with random order elements
     *
     * @param maxElems max elements on this list
     * @return
     */
    private List<OrderElement> generateElements(int maxElems) {
        List<OrderElement> elements = new ArrayList();

        for (int i = 0; i < intRandom(maxElems); i++) {
            elements.add(new OrderElement(randomProductName(), randomPrice()));
        }
        return elements;
    }

    /**
     * generates random date since 01.01.2012
     *
     * @return
     */
    private Date randomDate() {
        int minDay = (int) LocalDate.of(2012, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.now().toEpochDay();
        int randomDay = minDay + intRandom(maxDay - minDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        Date d = toDate(randomDate);
        return d;
    }

    /**
     * just convert LocalDate to Date
     *
     * @param randomDate
     * @return
     */
    private Date toDate(LocalDate randomDate) {
        Date d = Date.from(randomDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        return d;
    }

    /**
     * get random product name from list
     *
     * @return
     */
    private String randomProductName() {

        String productName = this.products.get(intRandom(products.size() - 1));
        return productName;
    }

    /**
     * get random price with max is specified as maxRandomPrice in this class
     *
     * @return
     */
    private float randomPrice() {
        float price = floatRandom(this.maxRandomPrice);
        return price;
    }

    /**
     * get random surname from list
     *
     * @return
     */
    private String randomSurname() {

        String surname = this.surnames.get(intRandom(surnames.size() - 1));
        return surname;
    }

    /**
     * get random name from list
     *
     * @return
     */
    private String randomName() {

        String name = this.names.get(intRandom(names.size() - 1));
        return name;
    }
}
