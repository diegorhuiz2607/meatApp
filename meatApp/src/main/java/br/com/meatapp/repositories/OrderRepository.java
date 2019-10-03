package br.com.meatapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meatapp.domain.Orders;

@Repository // para ele entender que ele e um repositorio 
public interface OrderRepository extends JpaRepository<Orders, Integer> {
	
	

}
