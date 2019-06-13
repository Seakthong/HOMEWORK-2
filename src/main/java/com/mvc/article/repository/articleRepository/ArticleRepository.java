package com.mvc.article.repository.articleRepository;

import com.mvc.article.repository.model.Article;
import com.mvc.article.repository.provider.ArticlesProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository {
//    @SelectProvider(method = "selectAll", type = ArticlesProvider.class)
    @Select("SELECT tba.*,tbc.name FROM tblarticles as tba INNER JOIN tblcategories as tbc ON tba.category_id = tbc.id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "author", column = "author"),
            @Result(property = "description", column = "description"),
            @Result(property = "category_id", column = "category_id"),
            @Result(property = "category.name",column = "name")
    })
    public List<Article> ViewAll();

    @InsertProvider(method = "Insert", type = ArticlesProvider.class)
    public boolean Insert(@Param("article") Article article);

    @UpdateProvider(method = "update", type = ArticlesProvider.class)
    boolean Update(@Param("id") int id, @Param("article") Article article);

    @UpdateProvider(method = "delete", type = ArticlesProvider.class)
    boolean Delete(@Param("id") int id);

    @SelectProvider(method = "selectOne", type = ArticlesProvider.class)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "author", column = "author"),
            @Result(property = "description", column = "description"),
            @Result(property = "category_id", column = "category_id"),
    })
    Article ViewOneRecord(@Param("id") int id);

    @SelectProvider(method = "listSize", type = ArticlesProvider.class)
    int listSize();

    @SelectProvider(method = "lastID", type = ArticlesProvider.class)
    int lastID();

//    @SelectProvider(method = "viewPagination", type = ArticlesProvider.class)
    @Select("SELECT * FROM tblarticles AS tbla INNER JOIN tblcategories AS tblc " +
            "ON tbla.category_id = tblc.id ORDER BY id ASC LIMIT 5 OFFSET #{page}*5")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "author", column = "author"),
            @Result(property = "description", column = "description"),
            @Result(property = "category_id", column = "category_id"),
            @Result(property = "category.name",column = "name")
    })
    List<Article> ViewPagination(@Param("limit") int limit, @Param("page") int page);
}
