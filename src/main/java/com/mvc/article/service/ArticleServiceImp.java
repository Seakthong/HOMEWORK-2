package com.mvc.article.service;

import com.mvc.article.repository.articleRepository.ArticleRepository;
import com.mvc.article.repository.model.Article;
import com.mvc.article.service.articleService.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleServiceImp implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Override
    public List<Article> ViewAll() {
        return articleRepository.ViewAll();
    }

    @Override
    public boolean Insert(Article article) {
        articleRepository.Insert(article);
        return false;
    }

    @Override
    public boolean Update(int id, Article article) {
        articleRepository.Update(id, article);
        return true;
    }

    @Override
    public boolean Delete(int id) {
        if(articleRepository.Delete(id) == true){return true;}
        else return false;
    }

    @Override
    public Article ViewOneRecord(int id) {
        return articleRepository.ViewOneRecord(id);
    }

    @Override
    public int listSize() {
        return articleRepository.listSize();
    }

    @Override
    public int lastID() {
        return articleRepository.lastID();
    }

    @Override
    public List<Article> ViewPagination(int limit, int page) {
        return articleRepository.ViewPagination(limit, page);
    }

    @Override
    public Integer roolIdBack() {
        if(articleRepository.rollIdBack() == null) {
            return 1;
        }
        return articleRepository.rollIdBack()+1;
    }

    @Override
    public List<Article> Search(String title, int cateId) {
        return articleRepository.Search(title, cateId);
    }

    @Override
    public List<Article> searchPage(String title, int cateId, int limit, int page) {
        return articleRepository.searchPage(title, cateId, limit, page);
    }

}
