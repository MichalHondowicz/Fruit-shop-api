package com.michon.fruitshopapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerList {

    List<Customer> customers;
}
