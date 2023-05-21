package pebite.Ponitor_BE.repository.customer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import pebite.Ponitor_BE.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom, QuerydslPredicateExecutor<Customer> {
}

