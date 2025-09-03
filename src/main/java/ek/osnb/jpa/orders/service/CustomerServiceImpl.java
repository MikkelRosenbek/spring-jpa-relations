package ek.osnb.jpa.orders.service;

import ek.osnb.jpa.orders.model.Customer;
import ek.osnb.jpa.orders.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        // If a profile exists, ensure bidirectional link
        if (customer.getProfile() != null) {
            customer.getProfile().setCustomer(customer);
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existing.setName(customer.getName());

        // Update profile if present
        if (customer.getProfile() != null) {
            customer.getProfile().setCustomer(existing);
            existing.setProfile(customer.getProfile());
        }

        return customerRepository.save(existing);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customerRepository.delete(existing);
    }

}
