package br.com.meatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.meatapp.domain.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

}
