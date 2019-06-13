package com.mvc.article.repository.articleRepository;

import com.mvc.article.repository.model.Category;
import com.mvc.article.repository.provider.CategoryProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {
    @SelectProvider(method = "selectAll", type = CategoryProvider.class)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    public List<Category> ViewAll();

    @InsertProvider(method = "Insert", type = CategoryProvider.class)
    public boolean Insert(@Param("category") Category category);

    @UpdateProvider(method = "update", type = CategoryProvider.class)
    boolean Update(@Param("id") int id, @Param("category") Category category);

    @UpdateProvider(method = "delete", type = CategoryProvider.class)
    boolean Delete(@Param("id") int id);

    @SelectProvider(method = "selectOne", type = CategoryProvider.class)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "author", column = "author"),
            @Result(property = "description", column = "description"),
            @Result(property = "category_id", column = "category_id"),
    })
    Category ViewOneRecord(@Param("id") int id);

    @SelectProvider(method = "listSize", type = CategoryProvider.class)
    int listSize();

    @SelectProvider(method = "lastID", type = CategoryProvider.class)
    int lastID();

    @SelectProvider(method = "viewPagination", type = CategoryProvider.class)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    List<Category> ViewPagination(@Param("limit") int limit, @Param("page") int page);
}
