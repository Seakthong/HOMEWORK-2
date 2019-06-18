package com.mvc.article.controller;

import com.mvc.article.repository.articleRepository.ArticleRepository;
import com.mvc.article.service.articleService.ArticleService;
import com.mvc.article.service.articleService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;

@Controller
@RequestMapping("/search")
public class SearchController {

    String curSearchTitle = "";
    int curCategory = -1;

    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;

//    @Autowired
//    ArticleRepository articleRepository;


    private static int curPage = 1;
    private static int curLimit = 5;
    private static int totalPage = 1;
    private static String curImage;
    private static int AddOrCancel = 0;

    @GetMapping(value = "/")
    public String showSearchForm(ModelMap modelMap){

        modelMap.addAttribute("categories", categoryService.ViewAll());
        modelMap.addAttribute("curSearchTitle", curSearchTitle);
        modelMap.addAttribute("curCategory", curCategory);

        int size = 0;
        modelMap.addAttribute("totalLimit", size);
        size = articleService.Search(curSearchTitle, curCategory).size();

        if(size > 0) {
//            System.out.println(articleService.ViewPagination(curLimit, curPage));
            totalPage = (size % curLimit != 0 ? size / curLimit + 1 : size / curLimit);
            modelMap.addAttribute("totalPage", totalPage);
            modelMap.addAttribute("curLimit", curLimit);
            modelMap.addAttribute("curPage", curPage);
            modelMap.addAttribute("totalLimit", size);
            modelMap.addAttribute("articles", articleService.searchPage(curSearchTitle, curCategory, curLimit, curPage));
        }
        System.out.println(curSearchTitle);
        if(curSearchTitle.equals("null♥")){
            System.out.println("work");
            modelMap.addAttribute("curSearchTitle", "");

        }

        System.out.println(articleService.Search(curSearchTitle, curCategory));
        return "Search/searchForm";
    }

    @GetMapping(value = "/find/{title}/{category}")
    public String getData(ModelMap modelMap, @PathVariable String title, @PathVariable int category){
        curSearchTitle = title;
        curCategory = category;
        System.out.println(title + category);
        return "redirect:/search/";
    }

    @GetMapping(value = "/pagination")
    public String setPage(@RequestParam int page){
//        curLimit = limit;
        curPage = page;
        return "redirect:/search/";
    }
}
