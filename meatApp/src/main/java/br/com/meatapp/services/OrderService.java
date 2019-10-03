package br.com.meatapp.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meatapp.domain.OrderItem;
import br.com.meatapp.domain.OrderItemPK;
import br.com.meatapp.domain.Orders;
import br.com.meatapp.repositories.OrderItemRepository;
import br.com.meatapp.repositories.OrderRepository;
import br.com.meatapp.services.exception.ObjectNotFoundException;

@Service
public class OrderService  {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private MenuItemService menuItemService;

	public List<Orders> findAll(){
		return orderRepository.findAll();
		
	}
	
	public Orders findById (Integer id){
		Optional<Orders> order = orderRepository.findById(id);
		return order.orElseThrow(() -> 
				new ObjectNotFoundException("Pedido não encontardo! ID: " +id));
	}
	
	public Orders insert(Orders order) {
		order.setId(null);
		order.setData(LocalDateTime.now());
		order.setUser(userService.findById(order.getUser().getId()));
		order.setRestaurant(restaurantService.findById(order.getRestaurant().getId()));
		order = orderRepository.save(order);
		
		List<OrderItem> items = order.getOrderItems();
		int item = 1;
		for (OrderItem it : items) {
			it.setOrders(order);
			it.setOrderItemId(item);
			it.setMenuItem(menuItemService.findById(it.getMenuItem().getId()));
			
			orderItemRepository.save(it);
			item++;
		}
		
		return order;
	}
	
	 
	
}
