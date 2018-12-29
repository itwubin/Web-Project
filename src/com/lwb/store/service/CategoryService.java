package com.lwb.store.service;

import java.sql.SQLException;
import java.util.List;

import com.lwb.store.domain.Category;


public interface CategoryService {

	List<Category> getAllCats() throws SQLException;

	void addCategory(Category c) throws SQLException;

}
