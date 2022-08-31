package wiki.common_cat.tulpasSites.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wiki.common_cat.tulpasSites.service.ContentService;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    protected ContentService contentService;
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/search/{content}")
    public String search(){
        return "search";
    }
    @GetMapping("/content/{id}")
    @ResponseBody
    public String content(@PathVariable("id")String id){
        return contentService.getContentPage(id);
    }
}
