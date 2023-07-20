package com.example.hibernate;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/getAllBuks")
	public List<Book> findAllBuks() {
		return bookRepository.findAll();
	}

	@PostMapping("/saveBuks")
	public Book createBuks(@RequestBody Book buk) {

		for (Author authors : buk.getAuthors()) {
			authors.setBooks(List.of(buk));

		}

		return bookRepository.save(buk);
	}
	
	@PutMapping("/updateObject/{id}")
	public Book updateBuks(@PathVariable long id, @RequestBody Book buk) {
		Optional<Book> findById = bookRepository.findById(buk.getIdBook());
		if(findById.isPresent()) {
			for (Author authors : buk.getAuthors()) {
				authors.setBooks(List.of(buk));

			}

			return bookRepository.save(buk);
		}
		else {
			System.out.println("Update is not working");
			return buk;
		}
	}
	
	@DeleteMapping("/deleteObject/{id}")
	public void deleteObject(@PathVariable long id) {
		Optional<Book> findById = bookRepository.findById(id);
		Book book = findById.get();
		for(Author authors : book.getAuthors()) {
			authors.getBooks().remove(book);//delete from 3rd table
			bookRepository.delete(book);
		}
	}

}
