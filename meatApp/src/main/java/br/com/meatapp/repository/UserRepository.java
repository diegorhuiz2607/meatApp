package br.com.meatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meatapp.domain.User;

//Tipo especial do Spring capaz de acessar os dados com base no tipo passado (User) e o tipo do atributo identificador (Integer)
//Um objeto deste tipo é capaz de realizar operações de acesso a dados (busca, inserção, alteração, exclusão)
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
	
}
