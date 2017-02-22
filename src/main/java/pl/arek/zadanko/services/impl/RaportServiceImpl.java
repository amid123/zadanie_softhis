/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import pl.arek.zadanko.model.Customer;
import pl.arek.zadanko.model.Order;
import pl.arek.zadanko.model.repositories.CustomerRepository;
import pl.arek.zadanko.model.viewmodel.RaportFilter;
import pl.arek.zadanko.model.viewmodel.RaportRow;
import pl.arek.zadanko.services.RaportService;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
public class RaportServiceImpl implements RaportService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<RaportRow> generateRaport(RaportFilter filter) {
        List<RaportRow> raportRows = new ArrayList();

        if (filter.getFilterType() == RaportFilter.FilterType.DEFAULT) {
            generateDefault(raportRows);
        } else if (filter.getFilterType() == RaportFilter.FilterType.NAME) {
            generateFilteredBySurname(filter, raportRows);
        } else if (filter.getFilterType() == RaportFilter.FilterType.DATE) {
            generateFilteredByMinMaxDate(filter, raportRows);
        } else if (filter.getFilterType() == RaportFilter.FilterType.PRICE) {
//            generateFilteredByMinMaxTotalAmount(filter, raportRows);
        }

        return raportRows;
    }

//    private void generateFilteredByMinMaxTotalAmount(RaportFilter filter, List<RaportRow> raportRows) {
//        List<Customer> customers = this.customerRepository.getCustomersByMinMaxTotalAmount(filter.getMinprice(), filter.getMaxprice());
//        for (Customer c : customers) {
//            RaportRow raportRow = new RaportRow();
//            buildRaport(raportRow, c);
//            raportRows.add(raportRow);
//        }
//    }

    private void generateFilteredByMinMaxDate(RaportFilter filter, List<RaportRow> raportRows) {
        List<Customer> customers = this.customerRepository.getCustomersByMinMaxDate(filter.getMinDate(), filter.getMaxDate());
        for (Customer c : customers) {
            RaportRow raportRow = new RaportRow();
            buildRaport(raportRow, c);
            raportRows.add(raportRow);
        }
    }

    private void generateFilteredBySurname(RaportFilter filter, List<RaportRow> raportRows) {
        List<Customer> customers = this.customerRepository.getCustomersBySurname(filter.getSurname());
        for (Customer c : customers) {
            RaportRow raportRow = new RaportRow();
            buildRaport(raportRow, c);
            raportRows.add(raportRow);
        }
    }

    private void generateDefault(List<RaportRow> raportRows) {
        List<Customer> customers = this.customerRepository.getAllCustomers();
        for (Customer c : customers) {
            RaportRow raportRow = new RaportRow();
            buildRaport(raportRow, c);
            raportRows.add(raportRow);
        }
    }

    private void buildRaport(RaportRow raportRow, Customer c) {
        setCustomerDetails(raportRow, c);
        setBestThreeOrders(c, raportRow);
        setTotalOrderAmount(c, raportRow);
        setLastOrder(c, raportRow);
    }

    private void setLastOrder(Customer c, RaportRow raportRow) {
        Collections.sort(c.getOrders(), new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        raportRow.setLastOrderDate(c.getOrders().get(0).getDate());
    }

    private void setTotalOrderAmount(Customer c, RaportRow raportRow) {
        float totalAmount = 0;
        for (Order o : c.getOrders()) {
            totalAmount += o.getAmount();
        }

        raportRow.setTotalAmount(totalAmount);
    }

    private void setBestThreeOrders(Customer c, RaportRow raportRow) {
        Collections.sort(c.getOrders());
        List<Order> orders = c.getOrders();
        Iterator<Order> it = orders.iterator();

        int i = 0;
        while (it.hasNext()) {
            i++;
            if (i > 3) {
                break;
            }

            raportRow.getBestThreeOrders().add(it.next());
        }
    }

    private void setCustomerDetails(RaportRow raportRow, Customer c) {
        raportRow.setName(c.getName());
        raportRow.setSurname(c.getSurname());
    }
}
