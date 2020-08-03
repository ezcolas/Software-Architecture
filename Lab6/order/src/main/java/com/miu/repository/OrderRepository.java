package com.miu.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.miu.domain.Order;


public interface OrderRepository extends MongoRepository<Order, String> {

}
