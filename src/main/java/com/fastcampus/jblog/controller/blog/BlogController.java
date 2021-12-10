package com.fastcampus.jblog.controller.blog;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.blog.SearchVO;
import com.fastcampus.jblog.biz.category.CategoryService;
import com.fastcampus.jblog.biz.category.CategoryVO;
import com.fastcampus.jblog.biz.post.PostService;
import com.fastcampus.jblog.biz.post.PostVO;
import com.fastcampus.jblog.biz.user.UserService;
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
public class BlogController {

	private final BlogService blogService;

	private final CategoryService categoryService;

	private final PostService postService;

	@RequestMapping("/create/blog/view")
	public String createBlogView() {
		return "blogcreate";
	}

	@RequestMapping("/create/blog")
	public String createBlog(BlogVO blog, HttpSession session) {
		UserVO user =  (UserVO)session.getAttribute("user");
		blog.setUser(user);
		blogService.createDefaultBlog(blog);
		return "redirect:/";
	}

	@RequestMapping("/search/blog")
	public String searchBlog(SearchVO search, Model model, HttpSession session) {
		List<BlogVO> blogs = blogService.searchBlogs(search);
		BlogVO userBlog = blogService.getUserBlog((UserVO)session.getAttribute("user"));

		model.addAttribute("userBlog", userBlog);
		model.addAttribute("blogs", blogs);

		return "index";
	}

	@RequestMapping("/blog/main")
	public String blogMain(HttpSession session, Model model) {
		UserVO blogUser = (UserVO)session.getAttribute("user");
		BlogVO findBlog = blogService.getUserBlog(blogUser);
		List<CategoryVO> categorys = categoryService.getCategorys(findBlog);
		List<PostVO> posts = postService.getPosts(findBlog);

		model.addAttribute("blog", findBlog);
		model.addAttribute("blogUser", blogUser);
		model.addAttribute("categorys", categorys);
		model.addAttribute("posts", posts);

		return "blogmain";
	}

	@RequestMapping("/search/post")
	public String searchPostByCategory(HttpSession session, BlogVO blog, CategoryVO category, Model model) {
		UserVO blogUser = (UserVO)session.getAttribute("user");
		BlogVO findBlog = blogService.getBlog(blog);
		List<CategoryVO> categorys = categoryService.getCategorys(findBlog);
		List<PostVO> posts = postService.searchPostByCategory(findBlog, category);

		model.addAttribute("blog", findBlog);
		model.addAttribute("blogUser", blogUser);
		model.addAttribute("categoryId", category.getCategoryId());
		model.addAttribute("categorys", categorys);
		model.addAttribute("posts", posts);

		return "blogmain";
	}

	@RequestMapping("/blog/admin")
	public String blogAdmin(BlogVO blog, Model model) {
		BlogVO findBlog = blogService.getBlog(blog);

		model.addAttribute("blog", findBlog);

		return "blogadmin_basic";
	}

	@RequestMapping("/update/blog")
	public String updateBlog(BlogVO blog) {
		blog.setUser(blogService.getBlog(blog).getUser());
		blogService.updateBlog(blog);

		return "redirect:/blog/main";
	}

	@RequestMapping("/delete/blog")
	public String deleteBlog(BlogVO blog) {
		blogService.deleteBlog(blog);

		return "redirect:/";
	}

	@RequestMapping("blogDeleteRequest")
	public String blogDeleteRequest(BlogVO blog) {
		blog.setUser(blogService.getBlog(blog).getUser());
		blogService.deleteRequest(blog);

		return "redirect:/";
	}
}
