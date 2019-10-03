package br.com.meatapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.meatapp.domain.MenuItem;
import br.com.meatapp.repositories.MenuItemRepository;
import br.com.meatapp.services.exception.DataIntegrityException;
import br.com.meatapp.services.exception.ObjectNotFoundException;

@Service
public class MenuItemService {

			@Autowired
			private MenuItemRepository menuItemRepository;	
			
			public List<MenuItem> findAll(){
				return menuItemRepository.findAll(); 
						
			}
			
			public MenuItem findById(Integer id) {
				Optional<MenuItem> menuItem = menuItemRepository.findById(id);
				return menuItem.orElseThrow(() -> new ObjectNotFoundException("Item não encontardo! ID: " + id));
			}
			
					
			public MenuItem insert(MenuItem menuItem) {
				menuItem.setId(null);
				return menuItemRepository.save(menuItem);
			}
			public MenuItem update(MenuItem menuItem, Integer id) {
				menuItem.setId(id);
				return menuItemRepository.save(menuItem);
			}
			public void delete(Integer id) {
				this.findById(id);//para caso informar um usuario que não existe ele da um erro
				try {
					menuItemRepository.deleteById(id);
				} catch (DataIntegrityViolationException e) {
					throw new DataIntegrityException("Ocorreu um erro de integridade.");
				}
				
			}
		}


