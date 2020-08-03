package com.miu.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.miu.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
