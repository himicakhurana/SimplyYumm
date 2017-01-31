package com.technicalkeeda.bean;

import java.util.List;

public class Recipe {
	String title;
	String prep_time;
	String cook_time;
	String image_url;
	String core_ingr;
	String recipe_no;
	String veg;
	String cuisine;
	String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCore_ingr() {
		return core_ingr;
	}

	public void setCore_ingr(String core_ingr) {
		this.core_ingr = core_ingr;
	}

	public String getRecipe_no() {
		return recipe_no;
	}

	public void setRecipe_no(String recipe_no) {
		this.recipe_no = recipe_no;
	}

	public String getVeg() {
		return veg;
	}

	public void setVeg(String veg) {
		this.veg = veg;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	List<String> ingredients;
	List<String> instructions;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrep_time() {
		return prep_time;
	}

	public void setPrep_time(String prep_time) {
		this.prep_time = prep_time;
	}

	public String getCook_time() {
		return cook_time;
	}

	public void setCook_time(String cook_time) {
		this.cook_time = cook_time;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}
}
