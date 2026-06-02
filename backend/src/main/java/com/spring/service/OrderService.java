package com.spring.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.model.Order;
import com.spring.model.OrderItem;
import com.spring.model.Product;
import com.spring.model.dto.OrderItemRequest;
import com.spring.model.dto.OrderItemResponse;
import com.spring.model.dto.OrderRequest;
import com.spring.model.dto.OrderResponse;
import com.spring.repository.OrderRepo;
import com.spring.repository.ProductRepo;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	public OrderResponse placeOrder(OrderRequest request)
	{
		
		Order order = new Order();
        String orderId = "ORD" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        System.out.println("Generated Order ID: " + orderId);
        order.setOrderId(orderId);
        order.setCustomerName(request.customerName());
        order.setEmail(request.email());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemReq : request.items()) {

            Product product = productRepo.findById(itemReq.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            product.setStockQuantity(product.getStockQuantity() - itemReq.quantity());
            productRepo.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemReq.quantity())
                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(itemReq.quantity())))
                    .order(order)
                    .build();
            orderItems.add(orderItem);

        }

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepo.save(order);

        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for (OrderItem item : order.getOrderItems()) {
            OrderItemResponse orderItemResponse = new OrderItemResponse(
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getTotalPrice()
            );
            itemResponses.add(orderItemResponse);
        }

        OrderResponse orderResponse = new OrderResponse(
                savedOrder.getOrderId(),
                savedOrder.getCustomerName(),
                savedOrder.getEmail(),
                savedOrder.getStatus(),
                savedOrder.getOrderDate(),
                itemResponses
        );

        return orderResponse;
	}
	
		@Transactional
	    public List<OrderResponse> getAllOrderResponses() {

	        List<Order> orders = orderRepo.findAll();
	        List<OrderResponse> orderResponses = new ArrayList<>();

	        for (Order order : orders) {


	            List<OrderItemResponse> itemResponses = new ArrayList<>();

	            for(OrderItem item : order.getOrderItems()) {
	                OrderItemResponse orderItemResponse = new OrderItemResponse(
	                        item.getProduct().getName(),
	                        item.getQuantity(),
	                        item.getTotalPrice()
	                );
	                itemResponses.add(orderItemResponse);

	            }
	            OrderResponse orderResponse = new OrderResponse(
	                    order.getOrderId(),
	                    order.getCustomerName(),
	                    order.getEmail(),
	                    order.getStatus(),
	                    order.getOrderDate(),
	                    itemResponses
	            );
	            orderResponses.add(orderResponse);
	        }

	        return orderResponses;
	    }
	
}
