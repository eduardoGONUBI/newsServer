package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
     private Integer id;
	 private String nome;
	 private String email;
	 private String password;
	 private Boolean Publisher=false;
	
	 public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getPublisher() {
		return Publisher;
	}
	public void setPublisher(Boolean publisher) {
		Publisher = publisher;
	}

	 
}
