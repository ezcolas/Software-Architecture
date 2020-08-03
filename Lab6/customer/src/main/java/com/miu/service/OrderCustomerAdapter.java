package com.miu.service;

import com.miu.domain.Customer;

public class OrderCustomerAdapter {

	public static OrderCustomerDTO getOrderCustomerDTO(Customer customer) {
		OrderCustomerDTO customerDTO = new OrderCustomerDTO(customer.getCustomerNumber(), customer.getFirstname(),
				customer.getLastname(), customer.getEmail(), customer.getPhone());
		if (customer.getAddress() != null) {
			customerDTO.setStreet(customer.getAddress().getStreet());
			customerDTO.setCity(customer.getAddress().getCity());
			customerDTO.setZip(customer.getAddress().getZip());
			customerDTO.setCountry(customer.getAddress().getCountry());
		}

		return customerDTO;
	}

}
