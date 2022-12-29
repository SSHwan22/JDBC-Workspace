package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberView;

/*
 * Controller : View를 통해서 요청한 기능을 담당
 * 				해당 메서드로 전달된 데이터들을 가공처리(vo객체 담아주기)한 후 Dao메서드 호출 시 vo객체를 전달해준다.
 * 				Dao로부터 반환받은 결과에 따라 사용자가 보게될 화면을 지정해준다.
 */

public class MemberController {

	public void insertMember(String userId, String userPwd, String userName, String gender, int age,
							 String email, String phone, String address, String hobby) {
		
		// 1. 전달된 데이터들을 Member객체에 담기 => 가공처리
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
		// 2. Dao의 insertMember 메서드 호출
		int result = new MemberDao().insertMember(m);
		
		// 3. result 결과값에 따라서 사용자가 보게될 화면 지정
		if(result > 0) { // 삽입된 행의 갯수가 1개 이상. -> 성공
			// 성공메시지 출력
			System.out.println("회원가입 성공");
		}else { // 삽입된 행의 갯수가 0개 -> 실패
			// 실패메시지 출력
			System.out.println("회원가입 실패");
		}
		
	}
	
	/**
	 * 사용자의 회원 전체 조회 요청을 처리해주는 메소드
	 */
	public void selectAll() {
		// 결과값을 담을 변수
		// SELECT -> ResultSet -> ArrayList<Member>
		
		// ArrayList<Member> list = new ArrayList<>();
		// list = new MemberDao().selectAll();
		ArrayList<Member> list = new MemberDao().selectAll();
		
		// 조회결과가 있는지 없는지 판단 후 사용자가 보게될 view 화면지정.
		if(list.isEmpty()) { // 텅빈 리스트반환 -> 조회결과가 없다.
			// 조회결과가 없을경우 보게될 화면
			new MemberView().displayNodata("전체 조회결과가 없습니다.");
		}
		else {
			// 조회결과가 있을경우
			new MemberView().displayList(list);
		}
	}
	
	/**
	 * 사용자의 아이디로 검색요청을 하는 메소드
	 * @param userId : 사용자가 입력했던 검색하고자하는 아이디
	 */
	public void selectByUserId(String userId) {
		
		// 결과값을 담을 변수
		// SELECT -> ResultSet
		Member m = new MemberDao().selectByUserId(userId);
		
		// 조회결과 검색된 데이터가 있는지 없는지 판단 후 사용자가 보게 될 화면 지정
		if(m ==  null) { 
			// 조회결과가 없을경우 보게될 화면
			new MemberView().displayNodata(userId+"가 조회결과가 없습니다.");
		}
		else {
			// 조회결과가 있을경우
			System.out.println(m+"\n");
		}
	}
	
	public void selectByUserName(String keyword) {
		ArrayList<Member> list =  new MemberDao().selectByUserName(keyword);
		
		if(list.isEmpty()) { 
			// 조회결과가 없을경우 보게될 화면
			new MemberView().displayNodata(keyword+"조회결과가 없습니다.");
		}
		else {
			// 조회결과가 있을경우
			new MemberView().displayList(list);
		}
	}
	
	/**
	 * 사용자의 회원정보 변경요청을 처리해주는 메소드
	 * @param userId : 변경하고자하는 회원의 아이디(구분자)
	 * @param newPwd : new~ 변경할 정보들
	 * @param newEmail
	 * @param newPhone
	 * @param newAddress
	 */
	public void updateMember(String userId, String newPwd, String newEmail, String newPhone, String newAddress) {
		
		// Vo객체로 입력받은 값을 가공처리해주기
		Member m = new Member();
		
		m.setUserId(userId);
		m.setUserPwd(newPwd);
		m.setEmail(newEmail);
		m.setPhone(newPhone);
		m.setAddress(newAddress);
		
		// 가공한 VO객체를 Dao단으로 넘기기
		int result = new MemberDao().updateMember(m);
		
		// 결과에 따른 화면지정
		if(result > 0) {
			System.out.println("회원정보 변경 성공");
		}else {
			System.out.println("회원정보 변경 실패");
		}
	}
	
	public void deleteMember(String userId) {
		int result = new MemberDao().deleteMember(userId);
		if(result > 0) {
			System.out.println("회원 삭제 성공");
		}else {
			System.out.println("회원 삭제 실패");
		}
	}
}
