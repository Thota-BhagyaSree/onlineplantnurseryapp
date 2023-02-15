package com.cg.onlineplantnursery.service;

import java.time.LocalDate;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineplantnursery.entity.Order;
import com.cg.onlineplantnursery.exceptions.CustomerIdNotFoundException;
import com.cg.onlineplantnursery.exceptions.InSufficientQuantity;
import com.cg.onlineplantnursery.exceptions.OrderIdNotFoundException;
import com.cg.onlineplantnursery.exceptions.PlantIdNotFoundException;
import com.cg.onlineplantnursery.exceptions.PlanterIdNotFoundException;
import com.cg.onlineplantnursery.exceptions.SeedIdNotFoundException;
import com.cg.onlineplantnursery.repository.IOrderRepository;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepository orderRepo;

	@Override
	@Transactional
	public Order addOrder(Order order) {
		// TODO Auto-generated method stub
		order.setOrderDate(LocalDate.now());
		Order savedOrder = orderRepo.save(order);
	
		return savedOrder;
		
	}

	@Override
	@Transactional
	public Order updateOrder(int orderId) throws OrderIdNotFoundException {

		Order updatedOrder = orderRepo.getReferenceById(orderId);
		orderRepo.save(updatedOrder);
		return updatedOrder;

	}

	@Override
	@Transactional
	public Order deleteOrder(int orderId) throws OrderIdNotFoundException {
		// TODO Auto-generated method stub
		Order exsistingOrder = orderRepo.findById(orderId)
				.orElseThrow(() -> new OrderIdNotFoundException("Order is not listed" + orderId));

		orderRepo.delete(exsistingOrder);
		return exsistingOrder;

	}

	

	@Override

	public List<Order> viewAllOrders() {
		// TODO Auto-generated method stub
		List<Order> orders = orderRepo.findAll();
		// if(orders.size()>0) {
		return orders;
		/*
		 * } else { throw new OrderIdNotFoundException("No order found"); }
		 */

	}

	@Override
	public List<Order> getOrderByCategory(String category) {
		// TODO Auto-generated method stub
		return orderRepo.getOrderByCategory(category);
	}

	@Override
	public List<Order> getOrderByCustomerId(int customerId) throws CustomerIdNotFoundException {
		if (orderRepo.existsById(customerId)) {
			return orderRepo.getOrderByCustomerId(customerId);
		} else {
			throw new CustomerIdNotFoundException("not found");
		}
	}

	@Override
	public Order viewOrder(int orderId) throws OrderIdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Order> vieworder =orderRepo.findById(orderId);
		if(vieworder.isPresent()) {
			return vieworder.get();
		}
		else {
			throw new OrderIdNotFoundException("No order is found");
		}
	}
		
		


}
