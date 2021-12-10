package com.fastcampus.jblog.controller.category;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.CategoryService;
import com.fastcampus.jblog.biz.category.CategoryVO;
import com.fastcampus.jblog.biz.user.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final BlogService blogService;

    @RequestMapping("/blog/admin/category")
    public String blogAdminCategory(HttpSession session, Model model) {
        UserVO blogUser = (UserVO)session.getAttribute("user");
        BlogVO findBlog = blogService.getUserBlog(blogUser);
        List<CategoryVO> categorys = categoryService.getCategorys(findBlog);

        model.addAttribute("blog", findBlog);
        model.addAttribute("categorys", categorys);

        return "blogadmin_category";
    }

    @RequestMapping("/insert/category")
    public String insertCategory(BlogVO blog, CategoryVO category) {
        BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());
        categoryService.insertCategory(category, findBlog);
  
        return "redirect:/blog/admin/category";
    }

    @RequestMapping("/delete/category")
    public String deleteCategory(BlogVO blog, CategoryVO category) {
        BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());
        category.setBlog(findBlog);
        categoryService.deleteCategory(category);

        return "redirect:/blog/admin/category";
    }

    @RequestMapping("/update/category/view")
    public String updateCategoryView(BlogVO blog, CategoryVO category, Model model) {
        BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());
        List<CategoryVO> categorys = categoryService.getCategorys(findBlog);
        CategoryVO updateCategory = categoryService.getCategory(category);

        model.addAttribute("blog", findBlog);
        model.addAttribute("categorys", categorys);
        model.addAttribute("updateCategory", updateCategory);

        return "blogadmin_category";
    }

    @RequestMapping("/update/category")
    public String updateCategory(BlogVO blog, CategoryVO category) {
        BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());
        category.setBlog(findBlog);
        categoryService.updateCategory(category);

        return "redirect:/blog/admin/category";
    }

}
