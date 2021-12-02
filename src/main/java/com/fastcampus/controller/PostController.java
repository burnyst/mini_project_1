package com.fastcampus.controller;

import com.fastcampus.component.vo.BlogVO;
import com.fastcampus.component.service.CategoryService;
import com.fastcampus.component.vo.CategoryVO;
import com.fastcampus.component.service.PostService;
import com.fastcampus.component.vo.PostVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;

    public PostController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }
    // 관리  페이지를 리턴
    @RequestMapping("/post/updateView")
    public String updatePostView(PostVO vo, HttpSession session, Model model){
        PostVO postVO = postService.getPost(vo);
        session.setAttribute("updatingPost", postVO);

        BlogVO blogVO = (BlogVO) session.getAttribute("blog");
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setBlogId(blogVO.getBlogId());
        model.addAttribute("categories", categoryService.getCategory(categoryVO));
        return "updatepost";
    }

    @RequestMapping("/post/update")
    public String updatePost(PostVO vo, HttpSession session){
        postService.updatePost(vo);
        BlogVO blog = (BlogVO) session.getAttribute("blog");
        return "redirect:/blog/user?blogId="+blog.getBlogId();
    }

    @RequestMapping("/post/delete")
    public String deletePost(PostVO vo, HttpSession session){
        postService.deletePost(vo);
        BlogVO blogVO = (BlogVO) session.getAttribute("blog");
        System.out.println(blogVO.toString());
        return "redirect:/blog/user?blogId=" + blogVO.getBlogId();
    }

    @RequestMapping("/post/category")
    public String getPostsByCategory(PostVO vo, HttpSession session){
        session.setAttribute("byCategory", vo);
        BlogVO blogVO = (BlogVO) session.getAttribute("blog");
        return "redirect:/blog/user?blogId="+blogVO.getBlogId();
    }

    @RequestMapping("/post/createView")
    public String createView(HttpSession session, Model model){
        PostVO postVO = new PostVO();
        session.setAttribute("post", postVO);
        BlogVO blogVO = (BlogVO) session.getAttribute("blog");
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setBlogId(blogVO.getBlogId());

        model.addAttribute("categories", categoryService.getCategory(categoryVO));
        return "addpost";
    }

    @RequestMapping("/post/create")
    public String createPost(PostVO vo, CategoryVO categoryVO, HttpSession session){
        CategoryVO categoryId = categoryService.getCategoryName(categoryVO);
        vo.setCategoryId(categoryId.getCategoryId());
        postService.createPost(vo);
        BlogVO blogVO = (BlogVO) session.getAttribute("blog");
        return "redirect:/blog/user?blogId=" + blogVO.getBlogId();
    }

}
