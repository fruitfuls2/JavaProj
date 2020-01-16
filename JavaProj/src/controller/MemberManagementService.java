package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Book;
import model.BookDAO;

import model.Member;
import model.MemberDAO;

public class MemberManagementService {
	// 데이터 베이스에 직접 접근: DAO
	private MemberDAO dao = new MemberDAO();
	private BookDAO bdao = new BookDAO();

	public boolean memberJoin(Member m) { // 회원가입
		int row = dao.insert(m);
		if (row == 0) {
			return false;
		} else {
			return true;
		}
	}

	public Member memberLogin(Member m) { // 로그인
		Member loginUser = dao.selectOne(m);
		return loginUser;
	}

	public ArrayList<Member> memberLookup(String id) { // 회원 정보 확인

		return dao.selectAll(id);
	}

	public ArrayList<Member> findName(String name) { // 회원 이름 검색

		return dao.selectName(name);
	}

	public ArrayList<Member> findAddress(String address) { // 회원 주소 검색

		return dao.selectAddress(address);
	}

	public ArrayList<Member> findPhone(String phone) { // 회원 전화번호 검색

		return dao.selectPhone(phone);
	}

	public ArrayList<Member> findId(String id) { // 회원 이름 검색(정보 수정시 아이디클릭)

		return dao.selectName(id);
	}

	public boolean editMember(Member seletUser) { // 회원 정보 수정

		if (dao.updateMember(seletUser) == 0) {
			return false;
		} else {
			return true;
		}

	}

	public ArrayList<Book> bookLookup() {

		return bdao.selectSome();
	}

	public ArrayList<Book> mainLookup() {

		return bdao.selectMain();
	}

	public ArrayList<Book> CLookup() {

		return bdao.selectAll();
	}

	public boolean editBook(Book selectBook) { // 회원 정보 수정

		if (bdao.updateLoan(selectBook) == 0) {
			return false;
		} else {
			return true;
		}

	}

}
