package com.sdc.onboarding.springbootrestservices.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdc.onboarding.springbootrestservices.entity.Post;
import com.sdc.onboarding.springbootrestservices.entity.PostRepository;
import com.sdc.onboarding.springbootrestservices.entity.User;
import com.sdc.onboarding.springbootrestservices.entity.UserRepository;

@RestController
public class JpaResource {
	
	@Autowired
	private UserRepository repository;
	@Autowired
	private PostRepository postRepository;
	
//	<--- REST Users method start here --->
	@CrossOrigin
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUsers(@PathVariable Long id){
		Optional<User> optional = repository.findById(id);
		
		if (!optional.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		
		return optional.get();
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUsers(@RequestBody User user){
		User savedUser = repository.save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUsers(@PathVariable Long id){
		Iterator<User> iterator = repository.findAll().iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				repository.deleteById(id);
				return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
			}
		}
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}
	
//	<--- REST Post method start here --->
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveAllPosts(@PathVariable Long id){
		Optional<User> optional = repository.findById(id);
		
		if (!optional.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		
		return optional.get().getPosts();
	}
	
	@GetMapping("/users/{user_id}/posts/{post_id}")
	public Post retrieveOnePosts(@PathVariable Long user_id, @PathVariable Long post_id){
		Optional<User> optionalUser = repository.findById(user_id);
		
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		
		Optional<Post> optionalPost = postRepository.findById(post_id);
		if (!optionalPost.isPresent()) {
			throw new PostNotFoundException("Post not found");
		}
		
		return optionalPost.get();
	}
	
}
