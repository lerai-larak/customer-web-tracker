package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	//inject the customer DAO
	@Autowired
	private CustomerDAO customerDao;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDao.getCustomers(); //delegate calls to DAO
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		customerDao.saveCustomer(customer);

	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		
		return customerDao.getCustomer(id);
	}

	@Override
	@Transactional
	public void deleteCustomer(int cusId) {
		customerDao.deleteCustomer(cusId);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String searchName) {
		return customerDao.searchCustomers(searchName);
	}

}
