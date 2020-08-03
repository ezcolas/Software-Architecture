package com.miu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miu.domain.Order;
import com.miu.domain.OrderFactory;
import com.miu.integration.EmailSender;
import com.miu.integration.Logger;
import com.miu.repository.OrderRepository;

import shop.customers.service.CustomerService;
import shop.customers.service.OrderCustomerDTO;
import shop.shopping.service.ShoppingCartDTO;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CustomerService customerService;
	@Autowired
	EmailSender emailSender;
	@Autowired
	Logger logger;

	public OrderDTO getOrder(String orderNumber) {
		Optional<Order> optOrder = orderRepository.findById(orderNumber);
		if (optOrder.isPresent()) {
			return OrderAdapter.getOrderDTO(optOrder.get());
		} else
			return null;
	}

	public void createOrder(ShoppingCartDTO shoppingCartDTO) {
		Order order = OrderFactory.createOrder(shoppingCartDTO);
		orderRepository.save(order);
	}

	public void confirm(String orderNumber) {
		Optional<Order> optOrder = orderRepository.findById(orderNumber);
		if (optOrder.isPresent()) {
			Order order = optOrder.get();
			order.confirm();
			emailSender.sendEmail("Thank you for your order with order number " + order.getOrdernumber(),
					"customer@gmail.com");
			logger.log("new order with order number " + order.getOrdernumber());
		}
	}

	public void setCustomer(String orderNumber, String customerNumber) {
		Optional<Order> optOrder = orderRepository.findById(orderNumber);
		if (optOrder.isPresent()) {
			Order order = optOrder.get();
			OrderCustomerDTO customerDTO = customerService.getOrderCustomer(customerNumber);
			if (customerDTO != null) {
				order.setCustomer(OrderCustomerAdapter.getCustomer(customerDTO));
			}
			orderRepository.save(order);
		}
	}

}
