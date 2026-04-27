package service;

import model.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;   


public class CustomerService {

    private static final CustomerService instance = new CustomerService();
    private Map<String, Customer> ls = new HashMap<>();

    private CustomerService() {
    }

    public static CustomerService getInstance() {
        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        if (ls.containsKey(email)) {
            throw new IllegalArgumentException("Customer exists");
        }
        Customer customer = new Customer(firstName, lastName, email);
        ls.put(email, customer);
    }
    public Customer getCustomer(String email) {
        return ls.get(email);
    }
    public Collection<Customer> getAllCustomers() {
        return ls.values(); 
    }



    }
