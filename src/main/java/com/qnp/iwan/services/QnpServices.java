package com.qnp.iwan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.qnp.iwan.bean.MessageResponse;
import com.qnp.iwan.model.Contact;
import com.qnp.iwan.repository.ContactRepository;
import com.qnp.iwan.threads.TaskFetch;

import lombok.RequiredArgsConstructor;

@CacheConfig(cacheNames = {
        "qnpServices"
})
@Service("qnpServices")
@RequiredArgsConstructor
public class QnpServices {

	@Autowired
	private ContactRepository contactRepository;
	
	@Value("${json.org.user.fetch}")
	private String fetchUserUrl;
	
	@Value("${json.org.user.post}")
	private String postUrl;
	
	
	
	public ResponseEntity<?>  fetchJsonPlaceHolder(){
		 MessageResponse messageResponse =  new MessageResponse(MessageResponse.CODE_SUCCESS, MessageResponse.OK);
		try {
			
			Thread thread1 = new Thread(new TaskFetch("Fetch Users", fetchUserUrl));
			Thread thread2 = new Thread(new TaskFetch("Post Users", postUrl));
			
			
			thread1.start();
			thread2.start();
			
			 return new ResponseEntity(messageResponse, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			   return new ResponseEntity(new MessageResponse(MessageResponse.ERROR_CODE_INTERNAL_SERVER,
 	                   e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		
	}

	
	
	public ResponseEntity<?>  getAllContact(){
		 MessageResponse messageResponse =  new MessageResponse(MessageResponse.CODE_SUCCESS, MessageResponse.OK);
		try {
			 List<Contact> contacts = contactRepository.findAll();
			 messageResponse.setData(contacts);
			
			 return new ResponseEntity(messageResponse, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			   return new ResponseEntity(new MessageResponse(MessageResponse.ERROR_CODE_INTERNAL_SERVER,
  	                   e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		
	}
	
	public ResponseEntity<?>  createContact(Contact contact) {
		try {
			if(contact.getId()==null || contact.getId().isEmpty()) {
				contact.setGeneratedId();
			}
			contactRepository.saveAndFlush(contact);
			 return new ResponseEntity( new MessageResponse(MessageResponse.CODE_SUCCESS, MessageResponse.OK), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			 return new ResponseEntity(new MessageResponse(MessageResponse.ERROR_CODE_INTERNAL_SERVER,
	                   e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?>  updateContact(Contact contact) {
		MessageResponse messageResponse =  new MessageResponse(MessageResponse.CODE_SUCCESS, MessageResponse.OK);
		try {
			if(contact.getId()==null || contact.getId().isEmpty()) {
				messageResponse.setStatus(MessageResponse.ERROR_CODE_400);
				messageResponse.setMessage(MessageResponse.INVALID_PAYLOAD);
				return new ResponseEntity(messageResponse, HttpStatus.ACCEPTED);
			}
			
			Contact contactById = contactRepository.getContactById(contact.getId());
			if(contactById == null) {
				messageResponse.setStatus(MessageResponse.CODE_USER_NOT_FOUND);
				messageResponse.setMessage(MessageResponse.USER_NOT_FOUND);
				return new ResponseEntity(messageResponse, HttpStatus.ACCEPTED);
			}
			contactRepository.saveAndFlush(contact);
			 return new ResponseEntity( new MessageResponse(MessageResponse.CODE_SUCCESS, MessageResponse.OK), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			 return new ResponseEntity(new MessageResponse(MessageResponse.ERROR_CODE_INTERNAL_SERVER,
	                   e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?>  delContact(Contact contact) {
		 MessageResponse messageResponse =  new MessageResponse(MessageResponse.CODE_SUCCESS, MessageResponse.OK);
		try {
			if(contact.getId() == null || contact.getId().isEmpty()) {
				messageResponse.setStatus(MessageResponse.ERROR_CODE_400);
				messageResponse.setMessage(MessageResponse.INVALID_PAYLOAD);
				return new ResponseEntity(messageResponse, HttpStatus.ACCEPTED);
			}
			
			Contact contactById = contactRepository.getContactById(contact.getId());
			if(contactById == null) {
				messageResponse.setStatus(MessageResponse.CODE_USER_NOT_FOUND);
				messageResponse.setMessage(MessageResponse.USER_NOT_FOUND);
				return new ResponseEntity(messageResponse, HttpStatus.ACCEPTED);
			}
			contactRepository.delete(contact);
			return new ResponseEntity(messageResponse, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			 return new ResponseEntity(new MessageResponse(MessageResponse.ERROR_CODE_INTERNAL_SERVER,
	                   e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
