package com.mvc.article.controller;

import com.mvc.article.service.articleService.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControlller {
    @Autowired
    ArticleService articleService;
    private static int curPage = 1;
    private static int curLimit = 5;
    private static int totalPage = 1;
    private static String curImage;
    private static int AddOrCancel = 0;
    @GetMapping(value = {"/view", "/"})
    public String showAll(ModelMap modelMap){
//        if(AddOrCancel == 1){
//            articleService.roolIdBack();
//        }
        int size = 0;
        modelMap.addAttribute("totalLimit", size);
        size = articleService.listSize();
        System.out.println(articleService.listSize());
        System.out.println(size);
        if(size > 0) {
            System.out.println(articleService.ViewPagination(curLimit, curPage));
            totalPage = (size % curLimit != 0 ? size / curLimit + 1 : size / curLimit);
            modelMap.addAttribute("totalRecords", size);
            modelMap.addAttribute("totalPage", totalPage);
            modelMap.addAttribute("curLimit", curLimit);
            modelMap.addAttribute("curPage", curPage);
            modelMap.addAttribute("totalLimit", size);
            modelMap.addAttribute("articles", articleService.ViewPagination(curLimit, curPage));
        }
        return "index";
    }
}
