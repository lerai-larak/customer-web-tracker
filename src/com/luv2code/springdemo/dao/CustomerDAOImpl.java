package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository //allow spring to scan, find this repository and handle exception translation
public class CustomerDAOImpl implements CustomerDAO {

	//inject the session factory
	@Autowired
	private SessionFactory sessionFactory; //points to the instance in spring-mvc-crud.xml
		
	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//create a query 
		Query<Customer> theQuery = session.createQuery("from Customer order by lastName", Customer.class);
		
		//return a list of customers retrieved
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		//get the hibernate session
		Session session = sessionFactory.getCurrentSession();
		//save the customer
		session.saveOrUpdate(customer); //use the same method for saving/updates 
	}

	@Override
	public Customer getCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, id);
		return customer;
	}

	@Override
	public void deleteCustomer(int cusId) {
		Session session = sessionFactory.getCurrentSession();
		
		//delete customer using primary key
		Query theQuery = session.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId",cusId);
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String searchName) {
		Query query;
		Session session = sessionFactory.getCurrentSession();
		if(searchName != null && searchName.trim().length()>0) {
			//search for lastname or firstname 
			query = session.createQuery
					("from Customer where lower(firstName) "
							+ "like:theName or lower(lastName) "
							+ "like theName");
			query.setParameter("theName", "%" + searchName.toLowerCase() + "%");
		}
		else {
			//searchName is empty...get all customers
			query = session.createQuery("from Customer",Customer.class);
		}
		
		//execute query and get the results list
		List<Customer> customersFound = query.getResultList();
		return customersFound;
	}

}
