/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.model.repositories;

import java.util.Date;
import java.util.List;
import pl.arek.zadanko.model.Customer;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
public interface CustomerRepository {

    public boolean saveClient(Customer k);
    public List<Customer> getCustomersBySurname(String surname);
    public List<Customer> getCustomersByMinMaxDate(Date min, Date max);
//    public List<Customer> getCustomersByMinMaxTotalAmount(float min, float max);
    public List<Customer> getAllCustomers();
}
