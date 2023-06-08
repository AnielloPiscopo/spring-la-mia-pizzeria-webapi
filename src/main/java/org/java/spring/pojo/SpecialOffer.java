package org.java.spring.pojo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class SpecialOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonBackReference
	private Pizza pizza;
	
	@Size(min = 3 , max = 100)
	private String title;
	
	@NotNull
	@PastOrPresent
	private LocalDate start;
	
	@NotNull
	@Future
	private LocalDate end;
	
	@NotNull
	@Min(5)
	@Max(95)
	private int discount;
	
	private boolean deleted = false;
	
	public SpecialOffer() {}

	public SpecialOffer(Pizza pizza, String title, LocalDate start,
			LocalDate end , int discount) {
		setPizza(pizza);
		setTitle(title);
		setStart(start);
		setEnd(end);
		setDiscount(discount);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = (getPizza().isDeleted()) ? true : deleted;
	}
	
	private String getInfo() {
		return "Id: " + getId() + ";"
			+ "\n" + "Title: " + getTitle() + ";"
			+ "\n" + "Start Date: " + getStart() +";"
			+ "\n" + "End Date: " + getEnd() +";"
			+ "\n" + "Discount: " + getDiscount() + "%;"
			+ "\n" + "Pizza: " + getPizza() + ";";
	}
	
	@Override
	public String toString() {
		return getInfo();
	}
}
