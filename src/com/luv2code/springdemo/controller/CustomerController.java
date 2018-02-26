package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	
	//inject customerService
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list") //only respond to get requests
	public String listCustomers(Model model) {
		
		//get the customer from the service
		List<Customer> theCustomers = customerService.getCustomers();
		
		//add the customers to the model
		model.addAttribute("customers", theCustomers);
		return "list-customers";
	}
	
	//display the form 
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Customer customer = new Customer();
		//create model attribute to bind form data
		theModel.addAttribute("customer",customer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		//save customer using the service
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId") int id, Model model) {
		
		
		//get customer using id from the service
		
		Customer customer = customerService.getCustomer(id);
		//set customer as model attribute
		model.addAttribute("customer",customer);
		
		//send the model over to the form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int cusId) {
		customerService.deleteCustomer(cusId);
		
		//delete the customer
		return "redirect:/customer/list";
		
	}
	
	@PostMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String searchName, Model theModel) {
		//search customers from the service 
		List<Customer> theCustomers = customerService.searchCustomers(searchName);
		
		//add found customers to the model 
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
 	

}
