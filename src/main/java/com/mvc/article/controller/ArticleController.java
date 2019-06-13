package com.mvc.article.controller;

import com.mvc.article.repository.model.Article;
import com.mvc.article.service.articleService.ArticleService;
import com.mvc.article.service.articleService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    private static int curPage = 1;
    private static int curLimit = 5;
    private static int totalPage = 1;
    private static String curImage;

    @GetMapping(value = {"/view", "/"})
    public String showAll(ModelMap modelMap){
        int size = 0;
        modelMap.addAttribute("totalLimit", size);
        size = articleService.listSize();
        System.out.println(articleService.listSize());
        System.out.println(size);
        if(size > 0) {
            System.out.println(articleService.ViewPagination(curLimit, curPage));
            totalPage = (size % curLimit != 0 ? size / curLimit + 1 : size / curLimit);
            modelMap.addAttribute("totalPage", totalPage);
            modelMap.addAttribute("curLimit", curLimit);
            modelMap.addAttribute("curPage", curPage);
            modelMap.addAttribute("totalLimit", size);
            modelMap.addAttribute("articles", articleService.ViewPagination(curLimit, curPage));
//            modelMap.addAttribute("categoryService", categoryService);
        }
        return "index";
    }

    @GetMapping(value = "/add")
    public String AddForm(ModelMap modelMap){
        int id = articleService.lastID();
        modelMap.addAttribute("article",new Article(id));
        modelMap.addAttribute("article_id", articleService.lastID());
        modelMap.addAttribute("status",0);
        modelMap.addAttribute("categories", categoryService.ViewAll());
        modelMap.addAttribute("sizeCategory", categoryService.ViewAll().size());
        return ("form");
    }

    @PostMapping(value = "/add")
    public String Add(@ModelAttribute Article article,@RequestParam("file") MultipartFile file){
        article = getImageFile(0, file, article);
        article.setStatus(1);
        article.setId(articleService.lastID());
//        System.out.println("Last ID : "+article.getId());
//        System.out.println("POSTING ADD IS : " + article.toString());
        articleService.Insert(article);

        return "redirect:/article/view";
    }

    @GetMapping("/init")
    public String init(@RequestParam int val){
        int last = articleService.lastID();
        System.out.println("Last ID : "+last);
        for (int i=last + 1; i< last + val + 1; i++){
            Article article = new Article(i,"why","why","why","/thumbnail/default.png",1,1);
            articleService.Insert(article);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/delete")
    public String Delete(@RequestParam int id){
        articleService.Delete(id);
        return "redirect:/article/view";
    }

    @GetMapping(value = "/edit/{id}")
    public String Edit(@PathVariable int id, ModelMap modelMap){
        modelMap.addAttribute("article",articleService.ViewOneRecord(id));
        modelMap.addAttribute("old_thumbnail",articleService.ViewOneRecord(id).getThumbnail());
        curImage = articleService.ViewOneRecord(id).getThumbnail();
        modelMap.addAttribute("status",1);
        return "form";
    }
    @PostMapping(value = "/update")
    public String EditUpdate(@RequestParam int id, @ModelAttribute Article article,@RequestParam("file") MultipartFile file){
        articleService.Update(id, getImageFile(1,file,article));
        return "redirect:/article/view";
    }
    @GetMapping(value = "/view/{id}")
    public String View(@PathVariable int id, ModelMap modelMap){
        modelMap.addAttribute("article",articleService.ViewOneRecord(id));
        return "view";
    }
    @GetMapping(value = "/pagination")
    public String Pagination(@RequestParam int page, ModelMap modelMap){
        curPage = page;
        return "redirect:/article/";
    }
    @GetMapping(value = "/returnPage")
    public String returnPage(@RequestParam int limited){
        curLimit = limited;
        return "redirect:/";
    }
    @GetMapping(value = "/curPage")
    public String curPagination(ModelMap modelMap){
        modelMap.addAttribute("articles",articleService.ViewPagination(curLimit, curPage));
        return "index";
    }

    private static Article getImageFile(int type,MultipartFile file,Article article){
        String curPic = "/thumbnail/default.png";
        UUID uuid = UUID.randomUUID();
        String serverPath="src/main/resources/static/thumbnail/";
        if(type == 1){
            curPic = curImage;
        }

        if(file.isEmpty()){
            article.setThumbnail(curPic);
        }else{
            try {
                Files.copy(file.getInputStream(), Paths.get(serverPath,uuid+"-"+file.getOriginalFilename()));
                article.setThumbnail("resources/thumbnail/"+uuid+"-"+file.getOriginalFilename());
            }
            catch(IOException ioe){
            }
        }
        return article;
    }


}






















