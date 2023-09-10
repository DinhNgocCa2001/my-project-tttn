package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.services.IBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")

@Controller
//@CrossOrigin(
//		origins = "*",
//		allowedHeaders = "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With",
//		allowCredentials = "*",
//		exposedHeaders = "*",
//		maxAge = 3600)
	@RequestMapping("bookservice")
public class BookStoreController {
	
	@Autowired
	private IBookStoreService service;
	
	@GetMapping("books")
	public ResponseEntity<List<Book>> getBooks(){
		
		List<Book> books = service.getBooks();
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	@GetMapping("bookssssss")
	public String getBooksssss(){

//		List<Book> books = service.getBooks();
//		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		System.out.println("hhhhhhhhhhhhhhhhhh");
		return "rjjejjeej";
	}
	
	@GetMapping("books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") Integer id){
		Book book = service.getBook(id);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@PostMapping("books")
	public ResponseEntity<Book> createBook(@RequestBody Book book){
		Book b = service.createBook(book);
		return new ResponseEntity<Book>(b, HttpStatus.OK);
		
	}
	
	@PutMapping("books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book){
		
		Book b = service.updateBook(id, book);
		return new ResponseEntity<Book>(b, HttpStatus.OK);
	}
	
	@DeleteMapping("books/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") int id){
		boolean isDeleted = service.deleteBook(id);
		if(isDeleted){
			String responseContent = "Book has been deleted successfully";
			return new ResponseEntity<String>(responseContent,HttpStatus.OK);
		}
		String error = "Error while deleting book from database";
		return new ResponseEntity<String>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
