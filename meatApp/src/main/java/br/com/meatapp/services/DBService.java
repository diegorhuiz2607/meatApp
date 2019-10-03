package br.com.meatapp.services;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meatapp.domain.MenuItem;
import br.com.meatapp.domain.OrderItem;
import br.com.meatapp.domain.OrderItemPK;
import br.com.meatapp.domain.Orders;
import br.com.meatapp.domain.Restaurant;
import br.com.meatapp.domain.User;
import br.com.meatapp.repositories.MenuItemRepository;
import br.com.meatapp.repositories.OrderItemRepository;
import br.com.meatapp.repositories.OrderRepository;
import br.com.meatapp.repositories.RestaurantRepository;
import br.com.meatapp.repositories.UserRepository;

@Service
public class DBService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RestaurantRepository restauranRepository;
	@Autowired
	private MenuItemRepository munuItemRepository;
	
	
	public void instanciaDatabase() {
		User user1 = new User(1, "Dayane", "dayanelopeslee@gmail.com", "123");
		User user2 = new User(2, "Mateus", "mateuspereira@gmail.com", "123");
		User user3 = new User(3, "Eliane", "eliane@gmail.com", "123");
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
		
		
		Restaurant r1  = new Restaurant(1, "Gloria" ,"Padaria","40-65m", 10.0 , "assets/img/restaurante/gloria.png","a","sim");
		restauranRepository.saveAll(Arrays.asList(r1));
		
		MenuItem m1 = new MenuItem(1, "Cup Cake", "Cup Cake recheado de nutela", 5.00,"null",r1);
		MenuItem m2 = new MenuItem(2, "Sorvete", "Creme", 6.00,"null",r1);
	    munuItemRepository.saveAll(Arrays.asList(m1,m2));
		
		Orders o1  = new Orders(1,LocalDateTime.now(),user1, r1,"Rua A","123",null,"Dinheiro");
		Orders o2  = new Orders(2,LocalDateTime.now(),user2, r1,"Rua B","123",null,"Cartão");
		orderRepository.saveAll(Arrays.asList(o1,o2));
		
		OrderItem oi1 = new OrderItem(new OrderItemPK(o1,1),1,4.5,m2);
		OrderItem oi2 = new OrderItem(new OrderItemPK(o1,2),2,10.0,m1);
		OrderItem oi3 = new OrderItem(new OrderItemPK(o1,1),1,6.0,m2);
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3));
	}

}
