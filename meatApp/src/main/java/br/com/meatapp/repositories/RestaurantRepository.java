package br.com.meatapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meatapp.domain.Restaurant;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{

	
	
}
