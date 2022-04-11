package com.example.demo.models;
import javax.persistence.*;

@Entity
@Table(name="countries")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="AD")
	private String ad;
	
	public Country() {
		
	}
	public Country(String name, String ad) {
		this.name = name;
		this.ad = ad;
	}
	
	public long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAd(String ad) {
		this.ad = ad;
	}
	
	public String getAd() {
		return ad;
	}
}
