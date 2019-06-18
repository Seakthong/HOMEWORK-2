package com.mvc.article.service;

import com.mvc.article.repository.articleRepository.CategoryRepository;
import com.mvc.article.repository.model.Article;
import com.mvc.article.repository.model.Category;
import com.mvc.article.service.articleService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> ViewAll() {
        return categoryRepository.ViewAll();
    }

    @Override
    public boolean Insert(Category category) {
        return categoryRepository.Insert(category);
    }

    @Override
    public boolean Update(int id, Category category) {
        return categoryRepository.Update(id, category);
    }

    @Override
    public boolean UpdateDefault(int id, Category category) {
        return categoryRepository.UpdateDefault(id, category);
    }

    @Override
    public boolean Delete(int id) {
        return categoryRepository.Delete(id);
    }

    @Override
    public Category ViewOneRecord(int id) {
        return categoryRepository.ViewOneRecord(id);
    }

    @Override
    public int listSize() {
        return categoryRepository.listSize();
    }

    @Override
    public int lastID() {
        return categoryRepository.lastID();
    }

    @Override
    public List<Category> ViewPagination(int limit, int page) {
        return categoryRepository.ViewPagination(limit, page);
    }
}
