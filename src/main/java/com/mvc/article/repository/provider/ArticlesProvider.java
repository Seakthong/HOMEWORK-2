package com.mvc.article.repository.provider;

import com.mvc.article.repository.model.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
        return (new SQL(){{
            SELECT("tbla.*, tblc.name");
            FROM(table+"AS tbla");
            INNER_JOIN(innerTable + " AS tblc ON tbla.category_id = tblc.id");
            WHERE("tbla.status = 1 AND tbla.category_id = tblc.id");
            ORDER_BY("tbla.id asc");
        }}.toString());
    }

    public String selectOne(@Param("id") Integer id){
        return new SQL(){{
            SELECT("a.*, c.name");
            FROM(table+" AS a");
            INNER_JOIN(innerTable + " AS c ON a.category_id = c.id");
            WHERE("a.status = 1 AND c.status = 1 AND a.id = #{id} ");
            ORDER_BY("a.id ASC");
            }}.toString();
    }

    public String search(@Param("title") String title, @Param("cateId") Integer cateId){


        return new SQL(){{
            SELECT("a.*, c.name");
            FROM(table+" AS a");
            INNER_JOIN(innerTable + " AS c ON a.category_id = c.id");
            if(cateId == -1)
                WHERE("a.status = 1 AND c.status = 1 AND a.title iLIKE '%"+title+"%'");
            else
                WHERE("a.status = 1 AND c.status = 1 AND a.title iLIKE '%"+title+"%'" + " AND "+"category_id IN ( " + cateId +" )");
            ORDER_BY("a.id ASC");
        }}.toString();
    }

    public String searchPage(@Param("title") String title, @Param("cateId") Integer cateId, @Param("page") Integer page, @Param("limit") Integer limit){
//        if(title == "" || title == "null♥")
//            return "SELECT a.*, c.name FROM tblarticles AS a INNER JOIN tblcategories as c ON a.category_id = c.id WHERE title = '' AND a.status = 1";

        return new SQL(){{
            SELECT("a.*, c.name");
            FROM(table+" AS a");
            INNER_JOIN(innerTable + " AS c ON a.category_id = c.id");
            if(cateId == -1) {
                System.out.println("Cate Wok");
                WHERE("a.status = 1 AND c.status = 1 AND a.title iLIKE '%"+title+"%'");
            }
            else
                WHERE("a.status = 1 AND c.status = 1 AND a.title iLIKE '%"+title+"%'" + " AND "+"category_id IN ( "+cateId+" )");
            ORDER_BY("a.id ASC LIMIT #{limit} OFFSET (#{page}-1)*#{limit}");
        }}.toString();
    }

    public String listSize(){
        return new SQL(){{
            SELECT("COUNT(*)");
            FROM(table);
            WHERE("status = 1");
        }}.toString();
    }

    public String viewPagination(@Param("limit") Integer limit, @Param("page") Integer page){
        return new SQL(){{
            SELECT("a.*, c.name");
            FROM(table+" AS a");
            INNER_JOIN(innerTable + " AS c ON a.category_id = c.id");
            WHERE("a.status = 1 AND c.status = 1");
            ORDER_BY("a.id ASC LIMIT #{limit} OFFSET (#{page}-1)*#{limit}");
    }}.toString();
    }


    public String delete(@Param("id") Integer id){
        return new SQL(){{
            UPDATE(table);
            SET("status = 0");
            WHERE("id = #{id}");
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
