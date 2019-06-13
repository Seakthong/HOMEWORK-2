package com.mvc.article.repository.provider;

import com.mvc.article.repository.model.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ArticlesProvider {
    private String table = "tblarticles";
    private String innerTable ="tblcategories";

    public String Insert(@Param("article") Article article){
        return new SQL(){{
            INSERT_INTO(table);
            VALUES("id", "#{article.id}");
            VALUES("title","#{article.title}");
            VALUES("description","#{article.description}");
            VALUES("author","#{article.author}");
            VALUES("thumbnail","#{article.thumbnail}");
            VALUES("status","#{article.status}");
            VALUES("category_id","#{article.category_id}");
        }}.toString();
    }

    public String selectAll(){
        System.out.println(new SQL(){{
            SELECT("*");
            FROM(table+"AS a");
            INNER_JOIN(innerTable + "AS c ON a.category_id = c.id");
            WHERE("a.status = 1");
            ORDER_BY("a.id asc");
        }}.toString());

        return new SQL(){{
            SELECT("*");
            FROM(table+"AS a");
            INNER_JOIN(innerTable + "AS c ON a.category_id = c.id");
            WHERE("a.status = 1");
            ORDER_BY("a.id asc");
        }}.toString();
    }

    public String selectOne(@Param("id") Integer id){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("status = 1 AND id = #{id}");
            ORDER_BY("id asc");
        }}.toString();
    }

    public String listSize(){
        return new SQL(){{
            SELECT("COUNT(*)");
            FROM(table);
            WHERE("status = 1");
        }}.toString();
    }
    public String lastID(){
        return new SQL(){{
            SELECT("COUNT(*)+1");
            FROM(table);
        }}.toString();
    }

    public String viewPagination(@Param("limit") Integer limit, @Param("page") Integer page){
        return new SQL(){{
            SELECT("a.*, c.name");
            FROM(table+" AS a");
            INNER_JOIN(innerTable + " AS c ON a.category_id = c.id");
            WHERE("a.status = 1 AND c.status = 1");
            ORDER_BY("id ASC LIMIT #{limit} OFFSET #{page}*#{limit}-5");

        }}.toString();
    }

    public String delete(@Param("id") Integer id){
        return new SQL(){{
            UPDATE(table);
            SET("status = 0");
            WHERE("status = 1 AND id = #{id}");
        }}.toString();
    }

    public String update(@Param("id") Integer id, @Param("article") Article article){
        return new SQL(){{
            UPDATE(table);
            SET("" +
                    "title = #{article.title}, " +
                    "description = #{article.description}," +
                    "author = #{article.author}, " +
                    "thumbnail = #{article.thumbnail}," +
                    "category_id = #{article.category_id}");
            WHERE("id = #{id} AND status = 1");
        }}.toString();
    }
}
