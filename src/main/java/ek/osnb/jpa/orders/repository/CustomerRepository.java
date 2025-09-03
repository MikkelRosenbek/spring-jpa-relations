package ek.osnb.jpa.orders.repository;

import ek.osnb.jpa.orders.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
