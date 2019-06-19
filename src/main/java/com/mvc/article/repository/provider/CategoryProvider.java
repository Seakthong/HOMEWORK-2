package com.mvc.article.repository.provider;

import com.mvc.article.repository.model.Article;
import com.mvc.article.repository.model.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class CategoryProvider {
    private String table = "tblcategories";
    private String innerTable ="tblarticles";

    public String Insert(@Param("category") Category category){
        return new SQL(){{
            INSERT_INTO(table);
            VALUES("id", "#{category.id}");
            VALUES("name","#{category.name}");
            VALUES("status","#{category.status}");
        }}.toString();
    }

    public String selectAll(){
        return new SQL(){{
            SELECT("id, name");
            FROM(table);
//            INNER_JOIN(innerTable);
            WHERE("status = 1");
            ORDER_BY("id asc");
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
            SELECT("COUNT(*)");
            FROM(table);
        }}.toString();
    }

    public String viewPagination(@Param("limit") Integer limit, @Param("page") Integer page){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("status=1");
            ORDER_BY("id ASC LIMIT #{limit} OFFSET #{page}*#{limit}-5");

        }}.toString();
    }

    public String delete(@Param("id") Integer id){
        Integer cate_id = id;
        System.out.println("UPDATE tblarticles SET status = 0 WHERE category_id = 1; UPDATE tblcategories SET status = 0 WHERE id = 1");
        return "UPDATE tblarticles SET status = 0 WHERE category_id = "+cate_id+"; UPDATE tblcategories SET status = 0 WHERE id = "+cate_id;
    }

    public String update(@Param("id") Integer id, @Param("category") Category category){
        return new SQL(){{
            UPDATE(table);
            SET("name = #{category.name}");
            WHERE("id = #{id} AND status = 1");
        }}.toString();
    }
    public String updateDefault(@Param("id") Integer id, @Param("category") Category category){
        return new SQL(){{
            UPDATE(table);
            SET("name = #{category.name}, status = #{category.status}");
            WHERE("id = #{id}");
        }}.toString();
    }


}
