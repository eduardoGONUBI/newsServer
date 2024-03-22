package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Noticia {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer id;
	 private String noticia;
	 private Integer idTopico;
	 private String topico;
	 @ManyToOne(fetch=FetchType.LAZY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNoticia() {
		return noticia;
	}
	public void setNoticia(String noticia) {
		this.noticia = noticia;
	}
	public String getTopico() {
		return topico;
	}
	public void setTopico(String topico) {
		this.topico = topico;
	}
	public Integer getIdTopico() {
		return idTopico;
	}
	public void setIdTopico(Integer idTopico) {
		this.idTopico = idTopico;
	}

	

	 
}
