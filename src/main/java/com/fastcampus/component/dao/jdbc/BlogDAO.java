package com.fastcampus.component.dao.jdbc;

import com.fastcampus.component.vo.BlogVO;
import com.fastcampus.component.vo.PostVO;
import com.fastcampus.util.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private final String BLOG_GET          = "SELECT * FROM BLOG WHERE BLOG_ID = ?";
    private final String BLOG_LIST         = "SELECT * FROM BLOG";
    private final String BLOG_LIST_TITLE   = "SELECT * FROM BLOG WHERE TITLE LIKE ?";
    private final String BLOG_LIST_TAG     = "SELECT * FROM BLOG WHERE TAG LIKE ?";
    private final String BLOG_LIST_BLOGGER = "SELECT * FROM BLOG WHERE USER_NAME LIKE ?";
    private final String BLOG_REGISTER     = "INSERT INTO BLOG(BLOG_ID, TITLE, USER_NAME, TAG, CNT_DISPLAY_POST, STATUS) VALUES(?, ?, ?, '', 5, '운영') ";
    private final String BLOG_UPDATE       = "UPDATE BLOG SET TITLE=?, TAG=?, CNT_DISPLAY_POST=? WHERE BLOG_ID = ?";
    private final String BLOG_DELETE       = "DELETE BLOG WHERE BLOG_ID = ?";
    private final String BLOG_POSTS        = "SELECT POST.* FROM POST, BLOG WHERE BLOG.BLOG_ID = ? ORDER BY CREATED_DATE DESC";

    // 블로그 조회
    public BlogVO getBlog(BlogVO vo){
        BlogVO blogVO = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BLOG_GET);
            stmt.setInt(1, vo.getBlogId());
            rs = stmt.executeQuery();
            if (rs.next()){
                blogVO = new BlogVO();
                blogVO.setBlogId(rs.getInt("BLOG_ID"));
                blogVO.setTitle(rs.getString("TITLE"));
                blogVO.setUserName(rs.getString("USER_NAME"));
                blogVO.setTag(rs.getString("TAG"));
                blogVO.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
                blogVO.setStatus(rs.getString("STATUS"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return blogVO;
    }
    // 블로그 리스트
    public List<BlogVO> getBlogList(BlogVO vo){
        List<BlogVO> blogList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            if (vo.getSearchCondition() != null) {
                if (vo.getSearchCondition().equals("TITLE")){
                    stmt = conn.prepareStatement(BLOG_LIST_TITLE);
                } else if (vo.getSearchCondition().equals("TAG")){
                    stmt = conn.prepareStatement(BLOG_LIST_TAG);
                } else if (vo.getSearchCondition().equals("BLOGGER")){
                    stmt = conn.prepareStatement(BLOG_LIST_BLOGGER);
                }
                stmt.setString(1, "%"+vo.getSearchKeyword()+"%");
            } else {
                stmt = conn.prepareStatement(BLOG_LIST);
            }

            rs = stmt.executeQuery();

            while (rs.next()){
                BlogVO blogVO = new BlogVO();
                blogVO.setBlogId(rs.getInt("BLOG_ID"));
                blogVO.setUserName(rs.getString("USER_NAME"));
                blogVO.setTitle(rs.getString("TITLE"));
                blogVO.setTag(rs.getString("TAG"));
                blogVO.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
                blogVO.setStatus(rs.getString("STATUS"));
                blogList.add(blogVO);
            }
            if (blogList.size() == 0) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return blogList;
    }
    // 블로그 등록
    public void registerBlog(BlogVO vo){
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BLOG_REGISTER);
            stmt.setInt(1, vo.getBlogId());
            stmt.setString(2, vo.getTitle());
            stmt.setString(3, vo.getUserName());
            int count = stmt.executeUpdate();
            System.out.println(count + "건 처리 완료 (registerBlog)");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }
    // 블로그 수정
    public void updateBlog(BlogVO vo){
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BLOG_UPDATE);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getTag());
            stmt.setInt(3, vo.getCntDisplayPost());
            stmt.setInt(4, vo.getBlogId());
            int count = stmt.executeUpdate();
            System.out.println(count + "건 처리 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(stmt, conn);
        }
    }
    // 블로그 삭제 요청
    public void deleteBlog(BlogVO vo){
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BLOG_DELETE);
            stmt.setInt(1, vo.getBlogId());
            int count = stmt.executeUpdate();
            System.out.println(count + "건 처리 완료");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public List<PostVO> getBlogPosts(BlogVO vo){
        List<PostVO> postList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BLOG_POSTS);
            stmt.setInt(1, vo.getBlogId());
            rs = stmt.executeQuery();
            while (rs.next()){
                PostVO post = new PostVO();
                post.setPostId(rs.getInt("POST_ID"));
                post.setTitle(rs.getString("TITLE"));
                post.setCategoryId(rs.getInt("CATEGORY_ID"));
                post.setContent(rs.getString("CONTENT"));
                post.setCreatedDate(rs.getDate("CREATED_DATE"));
                postList.add(post);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return postList;
    }

}
