package com.example.water.services;

import com.example.water.model.Order;
import com.example.water.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        // Calculate total price based on crate quantity
        double pricePerCrate = 4500; // Assume 4500 TZS per crate
        order.setTotalPrice(order.getCrateQuantity() * pricePerCrate);

        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setCustomerName(orderDetails.getCustomerName());
        order.setCustomerLocation(orderDetails.getCustomerLocation());
        order.setCustomerPhone(orderDetails.getCustomerPhone());
        order.setCustomerEmail(orderDetails.getCustomerEmail());
        order.setWaterName(orderDetails.getWaterName());
        order.setCrateQuantity(orderDetails.getCrateQuantity());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setPassword(orderDetails.getPassword());
        order.setTotalPrice(orderDetails.getCrateQuantity() * 4500); // Recalculate total price

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
