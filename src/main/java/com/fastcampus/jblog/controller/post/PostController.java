package com.fastcampus.jblog.controller.post;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.CategoryService;
import com.fastcampus.jblog.biz.category.CategoryVO;
import com.fastcampus.jblog.biz.post.PostService;
import com.fastcampus.jblog.biz.post.PostVO;
import com.fastcampus.jblog.biz.user.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final BlogService blogService;

    @RequestMapping("/blog/admin/post")
    public String blogAdminPost(HttpSession session, Model model) {
        UserVO blogUser = (UserVO)session.getAttribute("user");
        BlogVO findBlog = blogService.getUserBlog(blogUser);
        List<CategoryVO> categorys = categoryService.getCategorys(findBlog);

        model.addAttribute("blog", findBlog);
        model.addAttribute("categorys", categorys);

        return "blogadmin_post";
    }

    @RequestMapping("/insert/post")
    public String insertPost(BlogVO blog, CategoryVO category, PostVO post) {
        blog.setUser(blogService.getBlog(blog).getUser());
        category.setBlog(blog);
        post.setCategory(category);
        postService.insertPost(post);

        return "redirect:/blog/main";
    }

    @RequestMapping("/update/post/view")
    public String updatePostView(BlogVO blog, PostVO post, Model model) {
        PostVO updatePost = postService.getPost(post);
        BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());
        List<CategoryVO> categorys = categoryService.getCategorys(findBlog);

        model.addAttribute("updatePost", updatePost);
        model.addAttribute("blog", findBlog);
        model.addAttribute("categorys", categorys);

        return "blogadmin_post";
    }

    @RequestMapping("/update/post")
    public String updatePost(BlogVO blog, CategoryVO category, PostVO post) {
        blog.setUser(blogService.getBlog(blog).getUser());
        category.setBlog(blog);
        post.setCategory(category);
        postService.updatePost(post);

        return "redirect:/blog/main";
    }

    @RequestMapping("/delete/post")
    public String deletePost(PostVO post) {
        postService.deletePost(post);

        return "redirect:/blog/main";
    }
}
