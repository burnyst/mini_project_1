package com.fastcampus.component.dao.jdbc;

import com.fastcampus.component.vo.PostVO;
import com.fastcampus.util.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private final String POST_GET             = "SELECT * FROM POST WHERE POST_ID = ? ORDER BY CREATED_DATE DESC";
    private final String POST_CRATE           = "INSERT INTO POST(POST_ID, CATEGORY_ID, TITLE, CONTENT) VALUES((SELECT NVL(MAX(POST_ID),0)+1 FROM POST), ?, ?, ?)";
    private final String POST_UPDATE          = "UPDATE POST SET CATEGORY_ID=?, TITLE=?, CONTENT=? WHERE Post_ID=? ";
    private final String DELETE_POST          = "DELETE POST WHERE POST_ID=?";
    private final String BLOG_POST            = "SELECT * FROM POST WHERE CATEGORY_ID = ? ORDER BY CREATED_DATE DESC";


    // 게시글 생성
    public void createPost(PostVO vo){
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(POST_CRATE);
            stmt.setInt(1, vo.getCategoryId());
            stmt.setString(2, vo.getTitle());
            stmt.setString(3, vo.getContent());
            int count = stmt.executeUpdate();
            System.out.println(count + "건 처리 완료");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 게시글 조회
    public PostVO getPost(PostVO vo) {
        PostVO post = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(POST_GET);
            stmt.setInt(1, vo.getPostId());
            rs = stmt.executeQuery();

            if (rs.next()){
                post = new PostVO();
                post.setPostId(rs.getInt("POST_ID"));
                post.setCategoryId(rs.getInt("CATEGORY_ID"));
                post.setTitle(rs.getString("TITLE"));
                post.setContent(rs.getString("CONTENT"));
                post.setCreatedDate(rs.getDate("CREATED_DATE"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return post;
    }

    // 해당 블로그 포스트 불러오기
    public List<PostVO> getPosts(PostVO vo){
        List<PostVO> postList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BLOG_POST);
            stmt.setInt(1, vo.getCategoryId());
            rs = stmt.executeQuery();
            while (rs.next()){
                PostVO post = new PostVO();
                post.setPostId(rs.getInt("POST_ID"));
                post.setCategoryId(rs.getInt("CATEGORY_ID"));
                post.setTitle(rs.getString("TITLE"));
                post.setContent(rs.getString("CONTENT"));
                post.setCreatedDate(rs.getDate("CREATED_DATE"));
                postList.add(post);
            }
            if (postList.size()==0){
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return postList;
    }

    // 게시글 수정
    public void updatePost(PostVO vo){
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(POST_UPDATE);
            stmt.setInt(1, vo.getCategoryId());
            stmt.setString(2, vo.getTitle());
            stmt.setString(3, vo.getContent());
            stmt.setInt(4, vo.getPostId());
            int count = stmt.executeUpdate();
            System.out.println(count + "건 처리 완료");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 게시글 삭제
    public void deletePost(PostVO vo){
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(DELETE_POST);
            stmt.setInt(1, vo.getPostId());
            int count = stmt.executeUpdate();
            System.out.println(count + "건 처리 완료");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }


}
