package com.fastcampus.controller;

import com.fastcampus.component.vo.BlogVO;
import com.fastcampus.component.service.CategoryService;
import com.fastcampus.component.vo.CategoryVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/category/categorysetting")
    public String categorySetting(HttpSession session,Model model){
        getDisplay(session, model);
        return "categorysetting";
    }

    @RequestMapping("/category/add")
    public String addCategory(CategoryVO vo, Model model){
        categoryService.addCategory(vo);
        model.addAttribute("categories", categoryService.getCategory(vo));
        return "redirect:/category/categorysetting";
    }

    @RequestMapping("/category/delete")
    public String deleteCategory(CategoryVO vo){
        categoryService.deleteCategory(vo);
        return "redirect:/category/categorysetting";
    }

    @RequestMapping("/category/updateView")
    public String updateCategoryView(CategoryVO inputCategory, HttpSession session, Model model){
        getDisplay(session, model);
        CategoryVO selected = categoryService.selectCategory(inputCategory);
        session.setAttribute("selected", selected);
        return "categoryupdate";
    }

    @RequestMapping("/category/update")
    public String updateCategory(CategoryVO vo){
        categoryService.updateCategory(vo);
        return "redirect:/category/categorysetting";
    }

    public void getDisplay(HttpSession session, Model model){
        BlogVO vo = (BlogVO)session.getAttribute("blog");
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setBlogId(vo.getBlogId());
        model.addAttribute("categories", categoryService.getCategory(categoryVO));
    }

}
