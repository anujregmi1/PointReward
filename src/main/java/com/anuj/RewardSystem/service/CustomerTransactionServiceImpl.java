package com.anuj.RewardSystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anuj.RewardSystem.model.CustomerTransaction;
import com.anuj.RewardSystem.model.RewardsRequest;
import com.anuj.RewardSystem.repository.TransactionRepository;

@Service
public class CustomerTransactionServiceImpl implements CustomerTransactionService {
	
	private static final int hundred = 100;
	private static final int fifty = 50;
	

	@Autowired
	TransactionRepository transactionRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerTransactionServiceImpl.class);
		
	@Override
	public CustomerTransaction calculateRewards(RewardsRequest rewardsRequest) {
		
		LOGGER.info("Inside calculateRewards()  :");
		int pointsOverfifty  = 0;
		int pointsOverHundred = 0;
		int totalRewardPoints = 0 ;		
		double transaction_amount = rewardsRequest.getTransactionAmount();
		if(transaction_amount > hundred) {			
			pointsOverHundred = (int) ((transaction_amount - hundred)*2);
			pointsOverfifty = (int) ((transaction_amount - fifty) - (transaction_amount - hundred));			
		}else {
			pointsOverfifty = (int) (transaction_amount - fifty);			
		}
		totalRewardPoints = pointsOverHundred+pointsOverfifty;		
		CustomerTransaction customerTransaction = new CustomerTransaction();		
		customerTransaction.setCustomer_id(rewardsRequest.getCustomerId());
		customerTransaction.setTransacation_amount(transaction_amount);
		customerTransaction.setTransacation_date(rewardsRequest.getTransactionDate());
		customerTransaction.setRewards_points(totalRewardPoints);
		CustomerTransaction savedTransaction = transactionRepository.save(customerTransaction);
		return savedTransaction;		
		
	}
	
}
