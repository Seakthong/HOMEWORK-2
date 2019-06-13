package com.mvc.article;

import com.mvc.article.repository.model.Article;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public Article getBean(){
        return new Article();
    }
}
