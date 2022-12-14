package com.anuj.RewardSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anuj.RewardSystem.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
