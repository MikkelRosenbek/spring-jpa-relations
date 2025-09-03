package ek.osnb.jpa.common.data;

import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderLine;
import ek.osnb.jpa.orders.model.OrderStatus;
import ek.osnb.jpa.orders.model.Product;
import ek.osnb.jpa.orders.repository.CategoryRepository;
import ek.osnb.jpa.orders.repository.OrderLineRepository;
import ek.osnb.jpa.orders.repository.OrderRepository;
import ek.osnb.jpa.orders.repository.ProductRepository;
import ek.osnb.jpa.orders.model.Category;
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


    public InitData(OrderRepository orderRepository, OrderLineRepository orderLineRepository, CategoryRepository categoryRepository, ProductRepository productRepositroy) {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
        this.productRepository = productRepositroy;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Create categories
        Category electronics = new Category();
        electronics.setName("Electronics");

        Category books = new Category();
        books.setName("Books");

        categoryRepository.saveAll(List.of(electronics,books));

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

        // 4️⃣ Create order lines using actual Product entities
        OrderLine ol1 = new OrderLine(novel, 2);    // 2 novels
        OrderLine ol2 = new OrderLine(phone, 1);    // 1 phone
        OrderLine ol3 = new OrderLine(laptop, 1);   // 1 laptop

        // 5️⃣ Add order lines to orders
        order1.addOrderLine(ol1);
        order1.addOrderLine(ol2);
        order2.addOrderLine(ol3);

        // 6️⃣ Save order lines
        orderLineRepository.saveAll(List.of(ol1, ol2, ol3));


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
