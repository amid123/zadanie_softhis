/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.model.repositories.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.arek.zadanko.model.Customer;
import pl.arek.zadanko.model.repositories.CustomerRepository;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    SessionFactory sessionFactory;

    public boolean saveClient(Customer k) {

        Session session = getSession();

        try {
            session.beginTransaction();

            session.save(k);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    private Session getSession() throws HibernateException {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Override
    public List<Customer> getAllCustomers() {
        Session session = getSession();
        List<Customer> customers = null;
        try {
            session.beginTransaction();

            Query q = session.getNamedQuery("selectAllCustomers");

            customers = q.list();

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersBySurname(String surname) {

        Session session = getSession();
        List<Customer> customers = null;
        try {
            session.beginTransaction();

            Query q = session.getNamedQuery("selectBySurname");
            q.setParameter("surname", "%" + surname + "%");

            customers = q.list();

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersByMinMaxDate(Date minDate, Date maxDate) {

        Session session = getSession();
        List<Customer> customers = null;
        try {
            session.beginTransaction();

            Query q = session.getNamedQuery("selectByMinMaxDate");
            q.setParameter("minDate", minDate);
            q.setParameter("maxDate", maxDate);

            customers = q.list();

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return customers;

    }

//    @Override
//    public List<Customer> getCustomersByMinMaxTotalAmount(float min, float max) {
//
//        Session session = getSession();
//        List<Customer> customers = null;
//        try {
//            session.beginTransaction();
//
//            Query q = session.getNamedQuery("selectByMinMaxTotalAmount");
//            q.setParameter("minAmount", (double)min);
//            q.setParameter("maxAmount", (double)max);
//
//            customers = q.list();
//
//            session.getTransaction().commit();
//        } catch (HibernateException ex) {
//            session.getTransaction().rollback();
//            ex.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return customers;
//    }
}
