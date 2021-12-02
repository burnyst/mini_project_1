package com.fastcampus.component.dao.jdbc;

import com.fastcampus.component.vo.CategoryVO;
import com.fastcampus.util.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private final String CATEGORY_ADD        = "INSERT INTO CATEGORY(BLOG_ID, CATEGORY_ID, DISPLAY_TYPE, CNT_DISPLAY_POST, CATEGORY_NAME, DESCRIPTION, DELETION)" +
                                               "VALUES(?, (SELECT NVL(MAX(CATEGORY_ID),0)+1 FROM CATEGORY WHERE BLOG_ID=?), ?, ?, ?, ?, '삭제')";
    private final String CATEGORY_LIST       = "SELECT * FROM CATEGORY WHERE BLOG_ID=?";
    private final String CATEGORY_UPDATE     = "UPDATE CATEGORY SET CATEGORY_NAME=?, DISPLAY_TYPE=?, CNT_DISPLAY_POST=?, DESCRIPTION=? WHERE CATEGORY_ID=?";
    private final String CATEGORY_DELETE     = "DELETE CATEGORY WHERE CATEGORY_ID=?";
    private final String CATEGORY_DEFAULT    = "INSERT INTO CATEGORY(BLOG_ID, CATEGORY_ID, CATEGORY_NAME, DESCRIPTION, DELETION)" +
                                               "VALUES(?, (SELECT NVL(MAX(CATEGORY_ID), 0) +1 FROM CATEGORY), '미분류', '모든 글을 등록할 수 있는 기본 카테고리입니다.', '삭제불가')";
    private final String CATEGORY_ID_BY_NAME = "SELECT CATEGORY_ID FROM CATEGORY WHERE CATEGORY_NAME=?";
    private final String CATEGORY_GET        = "SELECT * FROM CATEGORY WHERE CATEGORY_ID = ?";

    // 미분류 카테고리 추가
    public void addDefaultCategory(CategoryVO vo){
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(CATEGORY_DEFAULT);
            stmt.setInt(1, vo.getBlogId());
            int count = stmt.executeUpdate();
            System.out.println(count + "건 처리 완료 (addDefaultCategory)");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 카테고리 추가
    public void addCategory(CategoryVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(CATEGORY_ADD);
            stmt.setInt(1, vo.getBlogId());
            stmt.setInt(2, vo.getBlogId());
            stmt.setString(3, vo.getDisplayType());
            stmt.setInt(4, vo.getCntDisplayPost());
            stmt.setString(5, vo.getCategoryName());
            stmt.setString(6, vo.getDescription());
            int count = stmt.executeUpdate();
            System.out.println(count + "건 처리 완료");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 설정 창에서 카테고리 보여주기
    public List<CategoryVO> getCategory(CategoryVO vo) {
        List<CategoryVO> categoryList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(CATEGORY_LIST);
            stmt.setInt(1, vo.getBlogId());
            rs = stmt.executeQuery();
            while (rs.next()){
                CategoryVO categoryVO = new CategoryVO();
                categoryVO.setBlogId(rs.getInt("BLOG_ID"));
                categoryVO.setCategoryId(rs.getInt("CATEGORY_ID"));
                categoryVO.setCategoryName(rs.getString("CATEGORY_NAME"));
                categoryVO.setDisplayType(rs.getString("DISPLAY_TYPE"));
                categoryVO.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
                categoryVO.setDescription(rs.getString("DESCRIPTION"));
                categoryVO.setCreatedDate(rs.getDate("CREATED_DATE"));
                categoryVO.setModifiedDate(rs.getDate("MODIFIED_DATE"));
                categoryList.add(categoryVO);
            }
            if (categoryList.size()==0){
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return categoryList;
    }

    // 카테고리 수정
    public void updateCategory(CategoryVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(CATEGORY_UPDATE);
            stmt.setString(1, vo.getCategoryName());
            stmt.setString(2, vo.getDisplayType());
            stmt.setInt(3, vo.getCntDisplayPost());
            stmt.setString(4, vo.getDescription());
            stmt.setInt(5, vo.getCategoryId());
            int count = stmt.executeUpdate();
            System.out.println(count + "건 처리 완료");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 카테고리 삭제
    public void deleteCategory(CategoryVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(CATEGORY_DELETE);
            stmt.setInt(1, vo.getCategoryId());
            int count = stmt.executeUpdate();
            System.out.println(count + "건 처리 완료");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public CategoryVO getCategoryName(CategoryVO vo){
        CategoryVO categoryVO = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(CATEGORY_ID_BY_NAME);
            stmt.setString(1, vo.getCategoryName());
            rs = stmt.executeQuery();
            if (rs.next()){
                categoryVO = new CategoryVO();
                categoryVO.setCategoryId(rs.getInt("CATEGORY_ID"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return categoryVO;
    }

    public CategoryVO selectCategory(CategoryVO vo){
        CategoryVO categoryVO = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(CATEGORY_GET);
            stmt.setInt(1, vo.getCategoryId());
            rs = stmt.executeQuery();
            if (rs.next()){
                categoryVO = new CategoryVO();
                categoryVO.setCategoryId(rs.getInt("CATEGORY_ID"));
                categoryVO.setBlogId(rs.getInt("BLOG_ID"));
                categoryVO.setCategoryName(rs.getString("CATEGORY_NAME"));
                categoryVO.setDescription(rs.getString("DESCRIPTION"));
                categoryVO.setDisplayType(rs.getString("DISPLAY_TYPE"));
                categoryVO.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return categoryVO;
    }

}
