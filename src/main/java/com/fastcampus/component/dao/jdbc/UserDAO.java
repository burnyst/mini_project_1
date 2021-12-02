package com.fastcampus.component.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fastcampus.component.vo.BlogVO;
import com.fastcampus.component.vo.UserVO;
import com.fastcampus.util.JDBCUtil;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private String USER_GET 		 = "SELECT * FROM BLOG_USER WHERE ID = ? AND PASSWORD = ?";
	private String USER_INSERT  	 = "INSERT INTO BLOG_USER VALUES ((SELECT NVL(MAX(USER_ID),0)+1 FROM BLOG_USER), ?, ?, 'USER', ?)";
	private String USER_UPDATE  	 = "UPDATE BLOG_USER SET USER_NAME = ?, PASSWORD = ?, ROLE=? WHERE USER_ID = ?";
	private String USER_DELETE		 = "DELETE BLOG_USER WHERE USER_ID = ?";
	private String USER_BLOG 		 = "SELECT BLOG.* FROM BLOG WHERE BLOG_ID = ?";

	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserVO();
				user.setUserId(rs.getInt("USER_ID"));
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setRole(rs.getString("ROLE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return user;
	}

	public void insertUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_INSERT);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getUserName());
			stmt.setString(3, vo.getPassword());
			int count = stmt.executeUpdate();
			System.out.println(count + "건 처리 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public void updateUser(UserVO vo){
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_UPDATE);
			stmt.setString(1, vo.getUserName());
			stmt.setString(2, vo.getPassword());
			stmt.setInt(3, vo.getUserId());
			int count = stmt.executeUpdate();
			System.out.println(count + "건 처리 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public void deleteUser(UserVO vo){
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_DELETE);
			stmt.setInt(1, vo.getUserId());
			int count = stmt.executeUpdate();
			System.out.println(count + "건 처리 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public BlogVO getUserBlog(UserVO vo){
		BlogVO blogVO = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_BLOG);
			stmt.setInt(1, vo.getUserId());
			rs = stmt.executeQuery();
			if (rs.next()){
				blogVO = new BlogVO();
				blogVO.setBlogId(rs.getInt("BLOG_ID"));
				blogVO.setUserName(rs.getString("USER_NAME"));
				blogVO.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				blogVO.setStatus(rs.getString("STATUS"));
				blogVO.setTag(rs.getString("TAG"));
				blogVO.setTitle(rs.getString("TITLE"));
			}
			return blogVO;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return blogVO;
	}

}
