package com.technicalkeeda.controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.technicalkeeda.bean.Recipe;

public class MySQLConnect {
	static MySQLConnect instance = new MySQLConnect();

	public MySQLConnect() {
		super();
	}

	public static MySQLConnect getInstance() {

		return instance;
	}

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost:3306/iiw";

	// Database credentials
	final String USER = "username";
	final String PASS = "password";

	public static List<Recipe> getRecipeResults(String sql) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		ResultSet rs = null;
		String result = "{}";
		java.sql.Connection conn = null;

		java.sql.Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			sql = "SELECT id, first, last, age FROM Employees";
			rs = stmt.executeQuery(sql);
			System.out.println(rs);
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
		return recipes;
	}

	public static List<String> getIngredientResults(String sql) {
		List<String> ingredients = new ArrayList<String>();
		ResultSet rs = null;
		String result = "{}";
		java.sql.Connection conn = null;
		java.sql.Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, "root", "");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			sql = "select * from ingr_list where name_ingr like '%" + sql + "%'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {

				String ingr = rs.getString(1);
				ingredients.add(ingr);

			}
			System.out.println(rs);
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
		return ingredients;
	}

	public static Recipe getRecipe(String sql) {
		Recipe recipe = new Recipe();
		ResultSet rs = null;
		java.sql.Connection conn = null;
		java.sql.Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, "root", "");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			System.out.println(sql);
			sql = "select title,prep_time,cook_time,image_url,ingr,inst from recipie where recipe_no = " + sql;
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String title = rs.getString(1);
				System.out.println(title);
				if (title != null && !title.isEmpty()) {
					recipe.setTitle(title);
					System.out.println(title);

				}
				String prep_time = rs.getString(2);
				if (prep_time != null && !prep_time.isEmpty()) {
					recipe.setPrep_time(prep_time);
				}
				String cook_time = rs.getString(3);
				if (cook_time != null && !cook_time.isEmpty()) {
					recipe.setCook_time(cook_time);
				}
				String image_url = rs.getString(4);
				if (image_url != null && !image_url.isEmpty()) {
					recipe.setImage_url(image_url);
				}
				String ingr = rs.getString(5);
				if (ingr != null && !ingr.isEmpty()) {

					recipe.setIngredients(Arrays.asList(ingr.split("[||||]")));
				}
				String instr = rs.getString(6);
				if (instr != null && !instr.isEmpty()) {
					recipe.setInstructions(Arrays.asList(instr.split("[||||]")));
				}
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!" + recipe.getTitle());
		return recipe;
	}

	public static String convertString(List<String> myParams) {
		StringBuilder jobTypeInClauseBuilder = new StringBuilder();
		int i = 0;
		for (String s : myParams) {
			if (i != 0) {
				jobTypeInClauseBuilder.append(',');
			}
			i++;
			jobTypeInClauseBuilder.append("'" + s + "'");
		}
		return jobTypeInClauseBuilder.toString();
	}

	public static List<Recipe> getMatchedRecipes(List<String> myParams) {
		boolean none = false;
		if (myParams.size() == 1) {
			if (myParams.get(0).equals("none"))

			none = true;
		}
		String ss = myParams.toString();
		ss = convertString(myParams);
		List<Recipe> recipes = new ArrayList<Recipe>();
		ResultSet rs = null;
		java.sql.Connection conn = null;
		java.sql.Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, "root", "");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql = "select i.recipie_number , count(*) as num ,r.title,r.image_url, r.category , r.core_ingr , r.veg , r.cusine from ingr as i join recipie as r on i.recipie_number = r.recipe_no where i.ingredients in ("
					+ ss + ") group by i.recipie_number order by num desc limit 5";
			if (none) {
				sql = "select i.recipie_number , count(*) as num ,r.title,r.image_url, r.category , r.core_ingr , r.veg , r.cusine from ingr as i join recipie as r on i.recipie_number = r.recipe_no group by i.recipie_number order by num desc limit 5";
			}
			System.out.println(sql);

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Recipe recipe = new Recipe();
				recipe.setTitle(rs.getString("r.title"));
				recipe.setImage_url(rs.getString("r.image_url"));
				recipe.setVeg(rs.getString("r.veg"));
				recipe.setCategory(rs.getString("r.category"));
				recipe.setRecipe_no(rs.getString("i.recipie_number"));
				/* recipe.setCuisine(rs.getString("r.cuisine")); */
				recipe.setCore_ingr(rs.getString("r.core_ingr"));
				recipes.add(recipe);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		return recipes;
	}

}
