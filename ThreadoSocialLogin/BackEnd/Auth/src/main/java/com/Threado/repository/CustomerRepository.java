package com.Threado.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Threado.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	Customer findByEmail(String email);
}
