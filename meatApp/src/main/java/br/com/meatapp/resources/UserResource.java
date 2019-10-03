package br.com.meatapp.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.xml.ws.soap.AddressingFeature.Responses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meatapp.domain.User;
import br.com.meatapp.services.UserService;

@RestController
@RequestMapping(value="users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		List<User> users = userService.findAll();
		return ResponseEntity.ok().body(users);
	}
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<User> findById(@PathVariable Integer id){
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<User> findByEmail(@RequestParam(value="email") String email){
		User user = userService.findByEmail(email);
		return ResponseEntity.ok().body(user);
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<User> insert(@Valid @RequestBody User user){
		user = userService.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);//retorna o usuario
	}
	@RequestMapping(value="id/{id}", method=RequestMethod.PUT)      //PUT- para alterar alguma coisa
	public ResponseEntity<Void> update(@Valid @RequestBody User user, @PathVariable Integer id){
		user = userService.update(user, id);
		return ResponseEntity.noContent().build();
		
	}
	@RequestMapping(value="id/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<User> delete(@PathVariable Integer id){
		userService.delete(id);
		return ResponseEntity.noContent().build();//nao retorna nada
	}
}
