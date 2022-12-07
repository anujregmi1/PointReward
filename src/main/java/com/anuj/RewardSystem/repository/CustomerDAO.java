package com.anuj.RewardSystem.repository;

import com.anuj.RewardSystem.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerDAO {

    private List<Customer> allCustomers;

  public  CustomerDAO()
    {
       this. allCustomers= new ArrayList<>();
    }

    public Optional<Customer> findById(long id) {
      return allCustomers.stream().filter(c -> c.getId()==id).findAny();
    }

    public List<Customer> findAll() {
        return allCustomers;
    }

    public void deleteById(long id) {
        Customer customer = allCustomers.stream().filter(c -> c.getId()==id).findAny().orElse(null);
         allCustomers.remove(customer);
    }
}
