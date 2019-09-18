package br.com.meatapp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.meatapp.domain.MenuItem;
import br.com.meatapp.domain.Restaurant;
import br.com.meatapp.domain.User;
import br.com.meatapp.repository.MenuItemRepository;
import br.com.meatapp.repository.RestaurantRepository;
import br.com.meatapp.repository.UserRepository;

@SpringBootApplication
public class MeatAppApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MeatAppApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		User user1 = new User(1, "Diego", "diegorhuiz2607@gmail.com", "123");
		User user2 = new User(2, "Dayana", "dayana@gmail.com", "456");
		userRepository.saveAll(Arrays.asList(user1, user2));
		
		Restaurant restaurant = new Restaurant(1, "Hot Dog", "Podrão", "10 minutos", 
				10.0, "ExampleImage", "17h às 00h", "Lanche aqui é do bão");
		restaurantRepository.saveAll(Arrays.asList(restaurant));
		
		MenuItem menuItem = new MenuItem(1, "Vira Lata", "Vem só salsicha", 5.0, "ImageExample", restaurant);
		menuItemRepository.saveAll(Arrays.asList(menuItem));
	}
	
	

}
