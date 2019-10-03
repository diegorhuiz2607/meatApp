package br.com.meatapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.meatapp.domain.Restaurant;
import br.com.meatapp.domain.User;
import br.com.meatapp.repositories.RestaurantRepository;
import br.com.meatapp.repositories.UserRepository;
import br.com.meatapp.services.exception.DataIntegrityException;
import br.com.meatapp.services.exception.ObjectNotFoundException;

@Service
public class RestaurantService {

		
		@Autowired
		private RestaurantRepository restaurantRepository;	
		
		public List<Restaurant> findAll(){
			return restaurantRepository.findAll(); 
					
		}
		
		public Restaurant findById(Integer id) {
			Optional<Restaurant> restaurant = restaurantRepository.findById(id);
			return restaurant.orElseThrow(() -> new ObjectNotFoundException("Restaurante não encontardo! ID: " + id));
		}
		
				
		public Restaurant insert(Restaurant restaurant) {
			restaurant.setId(null);
			return restaurantRepository.save(restaurant);
		}
		public Restaurant update(Restaurant restaurant, Integer id) {
			restaurant.setId(id);
			return restaurantRepository.save(restaurant);
		}
		public void delete(Integer id) {
			this.findById(id);//para caso informar um usuario que não existe ele da um erro
			try {
				restaurantRepository.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Ocorreu um erro de integridade.");
			}
			
		}
	}

