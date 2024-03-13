package com.qnp.iwan.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@NamedQuery(name="Contact.findAll", query="SELECT v FROM Contact v")
@Table(name="contact")
@Data
public class Contact {

	@Id
	private String id;
	private String name;
	private String email;
	private String phone;
	private String address;
	
	public Contact() {
		this.id = generateId();
	}
	
	private String generateId() {
		long now = Calendar.getInstance().getTimeInMillis();
		return String.valueOf(now);
	}
	
	public void setGeneratedId() {
		this.id = generateId();
	}

}
