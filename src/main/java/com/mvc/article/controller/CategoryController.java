package com.mvc.article.controller;

import com.mvc.article.repository.model.Category;
import com.mvc.article.service.articleService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    private static int curPage = 1;
    private static int curLimit = 5;
    private static int totalPage = 1;

    @GetMapping(value = {"/view", "/"})
    public String showAll(ModelMap modelMap){

        int size = 0;
        modelMap.addAttribute("totalLimit", size);
        size = categoryService.listSize();
        if(size > 0) {
            totalPage = (size % curLimit != 0 ? size / curLimit + 1 : size / curLimit);
            modelMap.addAttribute("totalPage", totalPage);
            modelMap.addAttribute("curLimit", curLimit);
            modelMap.addAttribute("curPage", curPage);
            modelMap.addAttribute("totalLimit", size);
            modelMap.addAttribute("categories", categoryService.ViewPagination(curLimit, curPage));
        }
        return "CategoryIndex";
    }

    @GetMapping(value = "/add")
    public String AddForm(ModelMap modelMap){
        int id = categoryService.lastID();
        modelMap.addAttribute("category",new Category(id));
        // modelMap.addAttribute("article_id", articleService.lastID());
        modelMap.addAttribute("status",0);
        return ("CategoryInputForm");
    }

    @PostMapping(value = "/add")
    public String Add(@ModelAttribute Category category){
        category.setStatus(1);
        category.setId(categoryService.lastID());

        System.out.println("Last ID : "+category.getId());

        System.out.println("POSTING ADD IS : " + category.toString());
        categoryService.Insert(category);

        return "redirect:/category/view";
    }

    @GetMapping("/init")
    public String init(@RequestParam int val){
        int last = categoryService.lastID();
        System.out.println("Last ID : "+last);
        for (int i=last + 1; i< last + val + 1; i++){
            Category category = new Category(i,"why",1);
            categoryService.Insert(category);
        }
        return "redirect:/category/";
    }

    @GetMapping(value = "/delete")
    public String Delete(@RequestParam int id){
        categoryService.Delete(id);
        return "redirect:/category/view";
    }

    @GetMapping(value = "/edit/{id}")
    public String Edit(@PathVariable int id, ModelMap modelMap){
        modelMap.addAttribute("category", categoryService.ViewOneRecord(id));
        modelMap.addAttribute("status",1);
        return "CategoryInputForm";
    }
    @PostMapping(value = "/edit")
    public String EditUpdate(@RequestParam int id, @ModelAttribute Category category){
        categoryService.Update(id, category);
        return "redirect:/category/view";
    }
    @GetMapping(value = "/view/{id}")
    public String View(@PathVariable int id, ModelMap modelMap){
        modelMap.addAttribute("category",categoryService.ViewOneRecord(id));
        return "/category/view";
    }
    @GetMapping(value = "/pagination")
    public String Pagination(@RequestParam int page, ModelMap modelMap){
        curPage = page;
        return "redirect:/category/";
    }
    @GetMapping(value = "/returnPage")
    public String returnPage(@RequestParam int limited){
        curLimit = limited;
        return "redirect:/category/";
    }
    @GetMapping(value = "/curPage")
    public String curPagination(ModelMap modelMap){
        modelMap.addAttribute("categories", categoryService.ViewPagination(curLimit, curPage));
        return "CategoryIndex";
    }
}






















