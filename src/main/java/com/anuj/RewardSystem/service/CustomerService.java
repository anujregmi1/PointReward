package com.anuj.RewardSystem.service;

import com.anuj.RewardSystem.model.Customer;
import com.anuj.RewardSystem.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;
    public Optional<Customer> getCustomerById(long id) {

     return   customerDAO.findById(id);
    }
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer saveOrUpdateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteCustomerById(long id) {
		// TODO Auto-generated method stub
		
	}
}
