/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import pl.arek.zadanko.model.OrderElement;
import pl.arek.zadanko.model.Customer;
import pl.arek.zadanko.model.Order;
import pl.arek.zadanko.tools.datagenerator.SimpleDataGenerator;
import pl.arek.zadanko.model.repositories.CustomerRepository;
import pl.arek.zadanko.services.CustomerService;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    SimpleDataGenerator dataGenerator;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void createCustomers(int clientsNo) {
        List<Customer> generateData = this.dataGenerator.generateData(clientsNo, 5, 10);

        for (Customer k : generateData) {
            saveCustomer(k);
        }
    }

    @Override
    public void saveCustomer(Customer customer) {

        for (Order z : customer.getOrders()) {
            recalculateAmount(z);
        }

        this.customerRepository.saveClient(customer);
    }

    /**
     * recalculate orders amounts
     *
     * @param order
     */
    private void recalculateAmount(Order order) {
        float kwota = 0;
        for (OrderElement e : order.getOrderElement()) {
            kwota += e.getPrice();
        }
        order.setAmount(kwota);
    }

}
