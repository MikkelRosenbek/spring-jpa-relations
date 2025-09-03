package ek.osnb.jpa.common.data;

import ek.osnb.jpa.orders.model.*;
import ek.osnb.jpa.orders.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public class InitData implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final ProfileRepository profileRepository;


    public InitData(OrderRepository orderRepository, OrderLineRepository orderLineRepository, CategoryRepository categoryRepository,
                    ProductRepository productRepositroy, CustomerRepository customerRepository, ProfileRepository profileRepository) {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
        this.productRepository = productRepositroy;
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Create categories
        Category electronics = new Category();
        electronics.setName("Electronics");

        Category books = new Category();
        books.setName("Books");

        categoryRepository.saveAll(List.of(electronics, books));

        //Create products
        Product novel = new Product();
        novel.setName("Novel");
        novel.setPrice(120);
        novel.addCategory(books);

        Product phone = new Product();
        phone.setName("Smartphone");
        phone.setPrice(1400);
        phone.addCategory(electronics);

        Product laptop = new Product();
        laptop.setName("Laptop");
        laptop.setPrice(1200);
        laptop.addCategory(electronics);

        productRepository.saveAll(List.of(novel, phone, laptop));

        //Create orders
        Order order1 = new Order(LocalDate.now(), OrderStatus.SHIPPED);
        Order order2 = new Order(LocalDate.now(), OrderStatus.PENDING);
        orderRepository.saveAll(List.of(order1, order2));

        //Create order lines
        OrderLine ol1 = new OrderLine(novel, 2);    // 2 novels
        OrderLine ol2 = new OrderLine(phone, 1);    // 1 phone
        OrderLine ol3 = new OrderLine(laptop, 1);   // 1 laptop

        order1.addOrderLine(ol1);
        order1.addOrderLine(ol2);
        order2.addOrderLine(ol3);

        orderLineRepository.saveAll(List.of(ol1, ol2, ol3));

        // ✅ Create customers
        Customer customer1 = new Customer("Mikkel Rosenbek");
        Customer customer2 = new Customer("Anna Jensen");
        customerRepository.saveAll(List.of(customer1, customer2));

        // ✅ Create profiles for customers
        Profile profile1 = new Profile("I love coding", customer1);
        Profile profile2 = new Profile("Book enthusiast", customer2);
        profileRepository.saveAll(List.of(profile1, profile2));

        System.out.println("Initialized customers and profiles!");


//        Order order1 = new Order(LocalDate.now(), OrderStatus.SHIPPED);
//        Order order2 = new Order(LocalDate.now(), OrderStatus.PENDING);
//        orderRepository.save(order1);
//        orderRepository.save(order2);
//        // Or use orderRepository.saveAll(List.of(order1, order2));
//
//        OrderLine orderLine1 = new OrderLine("Product A", 50.0, 2);
//        OrderLine orderLine2 = new OrderLine("Product B", 30.0, 1);
//
//        OrderLine orderLine3 = new OrderLine("Product C", 20.0, 5);
//        OrderLine orderLine4 = new OrderLine("Product D", 15.0, 3);
//        OrderLine orderLine5 = new OrderLine("Product E", 25.0, 4);
//
//        // Add the relationship
//        order1.addOrderLine(orderLine1);
//        order1.addOrderLine(orderLine2);
//        order2.addOrderLine(orderLine3);
//        order2.addOrderLine(orderLine4);
//        order2.addOrderLine(orderLine5);
//
//        orderLineRepository.saveAll(List.of(orderLine1, orderLine2, orderLine3, orderLine4, orderLine5));
//
//
//
//        Category electronics = new Category();
//        electronics.setName("Electronics");
//
//        Category books = new Category();
//        books.setName("Books");
//
//        // save the categories
//        categoryRepository.saveAll(List.of(electronics, books));
//
//        Product novel = new Product();
//        novel.setName("Novel");
//        novel.setPrice(120);
//
//        Product phone = new Product();
//        phone.setName("Smartphone");
//        phone.setPrice(1400);
//
//       novel.addCategory(books);
//        phone.addCategory(electronics);
//
//        // save the products
//        productRepository.saveAll(List.of(novel, phone));
    }


}
