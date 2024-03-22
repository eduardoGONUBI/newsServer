package com.example.demo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Topico {
	 @Id //depId is the primary key
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 
	 private Integer id;
	 private String Topico;
	 
	 @OneToMany(cascade=CascadeType.ALL)
	 @JoinColumn(name="id",referencedColumnName="Topico")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTopico() {
		return Topico;
	}
	public void setTopico(String topico) {
		Topico = topico;
	}
	 
	 
}
