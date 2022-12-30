package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

public class MemberService {

	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn, m);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Member> selectAll(){
		// 1) Connection객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) 결과값을 담을변수 생성.
		ArrayList<Member> list = new MemberDao().selectAll(conn);
		
		// 3) Connection 객체 close
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public Member selectByUserId(String userId) {
		// 1)
		Connection conn = JDBCTemplate.getConnection();
		
		// 2)
		Member m = new MemberDao().selectByUserId(conn, userId);
		
		JDBCTemplate.close(conn);
		
		return m;
	}
	
	public ArrayList<Member> selectByUserName(String keyword){
		// 1)
		Connection conn = JDBCTemplate.getConnection();
		
		// 2)
		ArrayList<Member> list = new MemberDao().selectByUserName(conn, keyword);
		
		// 3)
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	
	
	public int updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int deleteMember(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
}
