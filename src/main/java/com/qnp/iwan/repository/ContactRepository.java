package com.qnp.iwan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qnp.iwan.model.Contact;



@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
	
	@Query(value= "select o from Contact o where o.id =:id ")
	public Contact getContactById(@Param(value = "id") String id);
	
	

}
