package com.fastcampus.controller;

import com.fastcampus.component.service.BlogService;
import com.fastcampus.component.service.UserService;
import com.fastcampus.component.vo.BlogVO;
import com.fastcampus.component.service.CategoryService;
import com.fastcampus.component.vo.CategoryVO;
import com.fastcampus.component.service.PostService;
import com.fastcampus.component.vo.PostVO;
import com.fastcampus.component.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class BlogController {

	final BlogService blogService;
	final CategoryService categoryService;
	final PostService postService;
	final UserService userService;

	public BlogController(BlogService blogService, CategoryService categoryService, PostService postService, UserService userService) {
		this.blogService = blogService;
		this.categoryService = categoryService;
		this.postService = postService;
		this.userService = userService;
	}

	@RequestMapping("/")
	public String main(){
		return "forward:/index.jsp";
	}

	@RequestMapping("/blog")
	public String index(BlogVO vo, Model model) {
		if (!blogService.hasSearchWord(vo)) {
			return "redirect:/";
		}
		model.addAttribute("blogList", blogService.getBlogList(vo));
		model.addAttribute("search", vo);
		return "forward:/index.jsp";
	}

	@RequestMapping("/blog/create_view")
	public String blogCreateView(){
		return "blogcreate";
	}

	// 개인 블로그 생성
	@RequestMapping("/blog/create")
	public String blogCreate(BlogVO blogVO, CategoryVO categoryVO, HttpSession session)	{
		UserVO userVO = (UserVO) session.getAttribute("user");

		if (userService.getUserBlog(userVO) == null) {
			blogVO.setBlogId(userVO.getUserId());
			blogVO.setUserName(userVO.getUserName());
			blogService.registerBlog(blogVO);
			session.setAttribute("user_blog", blogVO);

			categoryVO.setBlogId(blogVO.getBlogId());
			categoryService.addDefaultCategory(categoryVO);
		}

		return "forward:/blog";
	}

	@RequestMapping("/blog/user")
	public String getBlog(BlogVO vo, CategoryVO categoryVO, HttpSession session, Model model){
		session.setAttribute("blog", blogService.getBlog(vo));

		categoryVO.setBlogId(vo.getBlogId());

		// blogId가 일치하는 카테고리들 가져오기
		List<CategoryVO> categoryVOList = new ArrayList<>();
		if (categoryService.getCategory(categoryVO) != null) {
			categoryVOList.addAll(categoryService.getCategory(categoryVO));
		}
		model.addAttribute("categories", categoryVOList);

		// 세션을 통해 카테고리별 분류인지 아닌지 확인
		if (session.getAttribute("byCategory") == null){
			List<PostVO> postOfBlog = blogService.getBlogPosts(vo);
			model.addAttribute("posts", postOfBlog);

		} else {
			PostVO postVO = (PostVO) session.getAttribute("byCategory");
			List<PostVO> postList = postService.getPosts(postVO);
			model.addAttribute("posts", postList);
			session.setAttribute("byCategory", null);
		}
		return "blogmain";
	}

	@RequestMapping("/blog/delete")
	public String blogDelete(BlogVO vo, HttpSession session){
		blogService.deleteBlog(vo);
		session.setAttribute("user_blog", null);
		return "forward:/blog";
	}

	@RequestMapping("/blog/updateView")
	public String blogUpdateView(){
		return "blogmanagement";
	}

	@RequestMapping("/blog/update")
	public String blogUpdate(BlogVO vo, HttpSession session){
		blogService.updateBlog(vo);
		BlogVO blogVO = blogService.getBlog(vo);
		session.setAttribute("blog", blogVO);
		return "forward:/blog/setting";
	}

	@RequestMapping("/blog/setting")
	public String basicSetting(BlogVO vo){
		return "forward:/blog/updateView?blogId=" + vo.getBlogId();
	}
}
