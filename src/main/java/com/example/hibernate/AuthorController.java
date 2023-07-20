package com.example.hibernate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
	@Autowired

	private AuthorRepository authorRepository;

	@GetMapping("/getAllAuthor")
	public List<Author> getAllAuthor() {

		return authorRepository.findAll();
	}

	@PostMapping("/saveAuthor")
	public Author saveAuthor(@RequestBody Author author) {

		return authorRepository.save(author);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteAuthor(@PathVariable long id) {
		authorRepository.deleteById(id);
	}

	@PutMapping("/updateAuthor/{id}")
	public Author updateAuthor(@PathVariable long id, @RequestBody Author author) {
		Optional<Author> findById = authorRepository.findById(id);
		if (findById.isPresent()) {
			System.out.println("Testing update method");
			return authorRepository.save(author);

		}
		else {
			System.out.println("Testing update method else" );

			return null;
			
		}
	}

}
