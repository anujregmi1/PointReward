package com.anuj.RewardSystem.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuj.RewardSystem.model.Customer;
import com.anuj.RewardSystem.model.RewardsRequest;
import com.anuj.RewardSystem.model.Transaction;
import com.anuj.RewardSystem.service.CustomerService;
import com.anuj.RewardSystem.service.CustomerTransactionService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerTransactionService customerTransactionService;

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable long id) {
		Customer customer = customerService.getCustomerById(id).orElse(null);
		if (Objects.nonNull(customer)) {
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {

		Customer customerDB = customerService.saveOrUpdateCustomer(customer);

		return new ResponseEntity<>(customerDB, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {

		Customer customerDB = customerService.saveOrUpdateCustomer(customer);

		return new ResponseEntity<>(customerDB, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable long id) {
		customerService.deleteCustomerById(id);

		return new ResponseEntity<>("Successful", HttpStatus.OK);

	}

	@PostMapping("/rewards")
	public String getRewards(Customer customer) {

		for (Transaction transaction : customer.getTransactions()) {

			int amount = (int) transaction.getAmount();
			int reward = 0;
			if (amount > 100) {
				int hundredTimes = amount - (amount - 50);
				int overHundred = (amount % 100) * 2;
				reward = hundredTimes + overHundred;
				System.out.println(reward);
			} else
				System.out.println("Not eligible for reward program");
		}

		return "";
	}

	@PostMapping("/calculateRewards")
	public ResponseEntity<Void> calculateRewards(RewardsRequest request) {
		customerTransactionService.calculateRewards(request);
		return new ResponseEntity<>(HttpStatus.OK);

	}

//	@RequestMapping(value = "/showRewards", method = RequestMethod.GET)
//	public String generateCustomerRewardPoints(ModelMap modelMap) {
//		List<Object[]> customerRewardsReport = transactionRepository.rewardsPointsPerMonth();
//		List<Object[]> totalRewardsPoints = transactionRepository.totalRewardsPoints();
//		modelMap.addAttribute("customerRewardsReport", customerRewardsReport);
//		modelMap.addAttribute("totalRewardsPoints", totalRewardsPoints);
//		return "displayRewards";
//
//	}

}
