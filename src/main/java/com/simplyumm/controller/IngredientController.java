package com.technicalkeeda.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/get/Ingredients")
public class IngredientController {
	@RequestMapping(method = RequestMethod.GET, params = "default")
	public @ResponseBody List<String> getIngredients(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<String> ingredients = new ArrayList<String>();
		ingredients.add("oil");
		ingredients.add("herbs");
		ingredients.add("tomatoes");
		ingredients.add("onions");
		ingredients.add("salt");
		ingredients.add("sugar");
		ingredients.add("herbs");
		ingredients.add("grains");
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, params = "keyword")
	public @ResponseBody List<String> getIngredientsAuto(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String keyword = request.getParameter("keyword");
		List<String> results = MySQLConnect.getIngredientResults(keyword);

		/*
		 * MySQLConnect connect=MySQLConnect(); List<String>
		 * connect.getIngredientResults()
		 */
		return results;
	}
}
