/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.services;

import pl.arek.zadanko.model.Customer;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
public interface CustomerService {
    
    public void createCustomers(int clientsNo);
    public void saveCustomer(Customer k);
}
