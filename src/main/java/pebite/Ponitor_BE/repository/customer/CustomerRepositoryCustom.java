package pebite.Ponitor_BE.repository.customer;

import pebite.Ponitor_BE.model.Customer;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<Customer> findByAtmId(String atmId);
}
