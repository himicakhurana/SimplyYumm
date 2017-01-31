package com.technicalkeeda.controller;

import java.util.Arrays;
import java.util.List;

public class TestString {
	public static void main(String[] args) {
		String s = "Cucumber Salsa', '', '', '', '2 medium cucumbers, peeled,seeded,chopped||||2 medium tomatoes, chopped||||1/2 cup green bell peppers, chopped||||1 jalapeño pepper, seeded,minced||||1 small Onion, chopped||||1 clove garlic, minced||||2 tablespoons fresh lime juice||||1 teaspoon fresh parsley, minced||||2 teaspoons fresh cilantro, minced||||1/2 teaspoon dried dill weed||||1/2 teaspoon salt', 'In a medium bowl stir together all ingredients.||||Cover and refrigerate at least one hour.||||Serve with tortilla chips";
	List<String> sl=	Arrays.asList(s.split("[||||]"));

		System.out.println(sl);
		String ss=sl.toString();
		ss=ss.replace('[', '(');
		ss=ss.replace(']', ')');
		System.out.println(ss);

	}
}
