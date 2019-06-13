package com.mvc.article.service.articleService;

import com.mvc.article.repository.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> ViewAll();
    boolean Insert(Article article);
    boolean Update(int id, Article article);
    boolean Delete(int id);
    Article ViewOneRecord(int id);
    int listSize();
    int lastID();
    List<Article> ViewPagination(int limit, int page);
}
