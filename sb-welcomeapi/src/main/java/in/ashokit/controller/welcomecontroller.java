package in.ashokit.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.book;

@RestController
@CrossOrigin
public class welcomecontroller {
	@GetMapping("/books")
public List<book> get() {
	
		book b1=new book(101,"Java",350.00);
		book b2=new book(102,"Python",450.00);
		book b3=new book(103,"Angular",550.00);
		List<book> li=Arrays.asList(b1,b2,b3);
		return li;
		
}
@PostMapping("/addbook")
public String addbook(@RequestBody book book) {
	System.out.println(book);
	return "success";
}
}
