package com.technicalkeeda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.technicalkeeda.bean.Recipe;

@Controller
@RequestMapping("/get/Recipes")
public class RecipeController {
	@RequestMapping(method = RequestMethod.GET)
	
	public @ResponseBody List<Recipe> getRecipes(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="ingredients") List<String> myParams)
			throws Exception {
		/*request.getParameter("ingredients");*/

		List<Recipe> re = MySQLConnect.getMatchedRecipes(myParams);
		
		return re;
	}

	@RequestMapping(method = RequestMethod.GET, params = "recipe_id")
	public @ResponseBody Recipe getRecipe(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Recipe recipe = MySQLConnect.getRecipe(request.getParameter("recipe_id"));
		System.out.println(recipe.getTitle());
		return recipe;
	}

}
