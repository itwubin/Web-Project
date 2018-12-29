package com.lwb.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.lwb.store.domain.Category;

public interface CategoryDao {

	List<Category> getAllCats() throws SQLException;

	void addCategory(Category c) throws SQLException;

}
