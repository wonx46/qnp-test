package com.qnp.iwan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qnp.iwan.model.Contact;
import com.qnp.iwan.services.QnpServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ContactController {
	

	private final QnpServices qnpServices;

	
	 @PostMapping("/v1/contact/create")
	 public ResponseEntity<?>  createContact(@RequestBody Contact contact) {
	      return  qnpServices.createContact(contact);
	  }
	 
	 @GetMapping("/v1/contact/list")
	 public ResponseEntity<?>  getAllContact() {
	      return  qnpServices.getAllContact();
	  }
	 

	 @PostMapping("/v1/contact/update")
	 public ResponseEntity<?>  updateContact(@RequestBody Contact contact) {
	      return  qnpServices.updateContact(contact);
	  }
	 
	 @PostMapping("/v1/contact/delete")
	 public ResponseEntity<?>  deleteContact(@RequestBody Contact contact) {
	      return  qnpServices.delContact(contact);
	  }
	 
	 
	 @GetMapping("/v1/jsonplaceholder/fetch")
	 public ResponseEntity<?>  getAllDataJson() {
	      return  qnpServices.fetchJsonPlaceHolder();
	  }
	    
}
