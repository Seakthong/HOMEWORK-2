package com.mvc.article.service.articleService;

import com.mvc.article.repository.model.Article;
import com.mvc.article.repository.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> ViewAll();
    boolean Insert(Category category);
    boolean Update(int id, Category category);
    boolean Delete(int id);
    Category ViewOneRecord(int id);
    int listSize();
    int lastID();
    List<Category> ViewPagination(int limit, int page);
}
