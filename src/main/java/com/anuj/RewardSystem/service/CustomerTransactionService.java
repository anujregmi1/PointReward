package com.anuj.RewardSystem.service;


import com.anuj.RewardSystem.model.CustomerTransaction;
import com.anuj.RewardSystem.model.RewardsRequest;

public interface CustomerTransactionService {

	public CustomerTransaction calculateRewards(RewardsRequest rewardsRequest);

	
}
