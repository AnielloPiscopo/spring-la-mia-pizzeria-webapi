package org.java.spring.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.URL;
import org.java.spring.helper.Helper;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "pizza" , cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<SpecialOffer> specialOffers;
	
	@ManyToMany
	@JoinTable(
	        name = "pizza_ingredient",
	        joinColumns = @JoinColumn(name = "pizza_id"),
	        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
	    )
	private List<Ingredient> ingredients;
	
	@NotBlank
	@Size(min = 3 , max = 100)
	private String name;

	@Size(min = 10 , max = 255)
	private String description;
	
	@URL
	private String imgUrl;
	
	@NotNull
	@Min(1)
	private float price;
	
	private boolean deleted = false;
	
	public Pizza(){}

	public Pizza(String name, String description, String imgUrl, float price , Ingredient... ingredients) {
		setName(name);
		setDescription(description);
		setImgUrl(imgUrl);
		setPrice(price);
		
		setIngredients(ingredients);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<SpecialOffer> getSpecialOffers() {
		return specialOffers.stream().filter(so -> !so.isDeleted()).toList();
	}

	public void setSpecialOffers(List<SpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	@JsonIgnore
	public void setIngredients(Ingredient[] ingredients) {
		setIngredients(Arrays.asList(ingredients));
	}
	
	public void addIngredient(Ingredient ingredient) {
		getIngredients().add(ingredient);
	}
	
	public void removeIngredient(Ingredient ingredient) {
		getIngredients().remove(ingredient);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		
		this.price = Helper.getRoundedNum(price, 2);
	}
	
	public float getDiscountedPrice() {
//		int maxDiscount = 0;
//		
//		for(SpecialOffer so : getSpecialOffers()) {
//			maxDiscount += so.getDiscount();
//		}
		
		int maxDiscount = getSpecialOffers().stream().map(so -> so.getDiscount()).reduce(0 , (sum , el) -> sum+el);
		
		float discountedPrice = getPrice() - (getPrice()/100 * maxDiscount);
		
		return Helper.getRoundedNum(discountedPrice, 2);
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	private String getInfo() {
		return "Id: " + getId() + ";"
			+ "\n" + "Name: " + getName() + ";"
			+ "\n" + "Description: " + getDescription() +";"
			+ "\n" + "ImgUrl: " + getImgUrl() + ";"
			+ "\n" + "Price: " + getPrice() + "$;"
			+ ((getDiscountedPrice() != getPrice()) ? "\n" + "Discounted Price: " + getDiscountedPrice() + "$;" : "");
	}
	
	@Override
	public String toString() {
		return getInfo();
	}
}
