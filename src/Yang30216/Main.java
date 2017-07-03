package Yang30216;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import BookFunc.book;
import BookFunc.bookContent;
import BookFunc.bookList;
import Function.fileFunc;

public class Main {

	private static bookList bkList;
	private static fileFunc file;
	
	public static void main(String[] args) {

		ArrayList<book> tmpBkList = new ArrayList<book>();
		
		Main.file = new fileFunc("30216_양석준.txt");
		try {
			tmpBkList = Main.file.readContent();
			
			if(tmpBkList == null) {
				Main.bkList = new bookList();
			} else {
				Main.bkList = new bookList(tmpBkList);
			}
			
			Main.file.closeBrStream();
			Main.file.createBWStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bookContent.setMenu();
	}
	
	public static void rentBook(Scanner scan) {
		String userName, address, phoneNum, bookName, bookAuthor;
		
		System.out.println("");
		System.out.print("도서명 : ");
		bookName = scan.nextLine();
		
		System.out.print("저자명 : ");
		bookAuthor = scan.nextLine();
		
		if(Main.bkList.moreBook(bookName, bookAuthor) == -1) {
			System.out.println("죄송합니다. 대출 하실 책의 재고가 없습니다.");
			System.out.println("다음에 이용 해 주시기 바랍니다.");
			System.out.println("");
			
			return;
		}
		
		System.out.print("사용자 이름 : ");
		userName = scan.nextLine();
		
		System.out.print("사용자 거주지 : ");
		address = scan.nextLine();
		
		System.out.print("사용자 연락처 : ");
		phoneNum = scan.nextLine();
		
		Main.bkList.rentUser(userName, phoneNum, address, bookName, bookAuthor);
		System.out.println("");
		
		System.out.println("--- 도서 대출 결과 ---");
		System.out.println(userName + "님에게 " + bookName + "(" + bookAuthor + ") 저자의 도서가 대여 되었습니다.");
		System.out.println("");
	}
	
	public static void returnBook(Scanner scan) {
		String userName, address, phoneNum, bookName, bookAuthor;
		int res = 0;
		
		System.out.println("");
		System.out.print("도서명 : ");
		bookName = scan.nextLine();
		
		System.out.print("저자명 : ");
		bookAuthor = scan.nextLine();
		
		System.out.print("사용자 이름 : ");
		userName = scan.nextLine();
		
		System.out.print("사용자 거주지 : ");
		address = scan.nextLine();
		
		System.out.print("사용자 연락처 : ");
		phoneNum = scan.nextLine();
		
		res = Main.bkList.returnUser(userName, phoneNum, address, bookName, bookAuthor);
	
		if(res == -1) {
			System.out.println("반납 하시려는 도서의 대여 정보가 없습니다.");
			System.out.println("");
			return;
		}
		
		System.out.println("");
	}
	
	public static void allPrint() { 
		ArrayList<book> tmpBookList = null;
		Iterator<book> itr = null;
		int current = 1;
		
		book bk = new book();
		
		System.out.println("");
		System.out.println("--- 전체 도서 목록 ---");
		
		tmpBookList = Main.bkList.searchBookObj("null", bookList.SEARCH_BOOK_ALL, bookList.SEARCH_NONE_OPT);
		
		if(tmpBookList == null) {
			System.out.println("출력 결과가 존재하지 않습니다.");
			System.out.println("");
			return;
		}
		
		System.out.println("총 " + tmpBookList.size() + "개의 도서가 검색 되었습니다.");
		System.out.println("");
		
		itr = tmpBookList.iterator();
		
		while(itr.hasNext()) {
			bk = itr.next();
			
			System.out.println(current + "번 도서의 검색 결과입니다.");
			
			System.out.println("도서명 : " + bk.get_bookName() + " / " + "저자 : " + bk.get_author());
			System.out.println("출간일 : " + bk.get_createDate() + " / " + "가격 : " + bk.get_price() + "원");
			System.out.println("키워드 : " + bk.get_keyWord() + " / " + "페이지 : " + bk.get_totalPage() + "쪽");
			System.out.println("");
			
			current++;
		}
		
		System.out.println("--- 출력이 완료 되었습니다 ---");
		System.out.println("");
	}
	
	public static void saveData() {
		ArrayList<book> tmpBookList = null;
		Iterator<book> itr = null;
		
		book bk = new book();
		
		tmpBookList = Main.bkList.searchBookObj("null", bookList.SEARCH_BOOK_ALL, bookList.SEARCH_NONE_OPT);
		
		if(tmpBookList == null) {
			return;
		}
		
		itr = tmpBookList.iterator();
		
		while(itr.hasNext()) {
			bk = itr.next();

			try {
				
				Main.file.writeContent(bk.get_bookName(), bk.get_createDate(), bk.get_price(), bk.get_totalPage(), bk.get_author(), bk.get_keyWord());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			Main.file.closeBwStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void searchBook(Scanner scan, int type) {
		ArrayList<book> tmpBookList = null;
		Iterator<book> itr = null;
		
		book bk = new book();
		int current = 1;
		String bookName;
		
		System.out.println("");
		System.out.print("검색 할 데이터 : ");
		bookName = scan.nextLine();
		
		System.out.println("");
		System.out.println("--- 검색 결과 ---");
		
		tmpBookList = Main.bkList.searchBookObj(bookName, type, bookList.SEARCH_SIM);
		
		if(tmpBookList == null) {
			System.out.println("검색 결과가 존재하지 않습니다.");
			System.out.println("");
			return;
		}
		
		System.out.println("총 " + tmpBookList.size() + "개의 도서가 검색 되었습니다.");
		System.out.println("");
		
		itr = tmpBookList.iterator();

		
		while(itr.hasNext()) {
			bk = itr.next();
			
			System.out.println(current + "번 도서의 검색 결과입니다.");
			
			System.out.println("도서명 : " + bk.get_bookName() + " / " + "저자 : " + bk.get_author());
			System.out.println("출간일 : " + bk.get_createDate() + " / " + "가격 : " + bk.get_price() + "원");
			System.out.println("키워드 : " + bk.get_keyWord() + " / " + "페이지 : " + bk.get_totalPage() + "쪽");
			System.out.println("");
			
			current++;
		}
		
		System.out.println("--- 검색이 완료 되었습니다 ---");
		System.out.println("");
	}
	
	public static void runningContent(int index, Scanner scan) {
		String bookName;
		String author;
		String keyWord;
		int price = 0;
		int totalPage = 0;
		
		switch(index) {
			case 1:			
				System.out.println("");
				System.out.print("도서 이름 : ");
				bookName = scan.nextLine();
				
				System.out.print("저자 이름 : ");
				author = scan.nextLine();
				
				System.out.print("키워드 : ");
				keyWord = scan.nextLine();
				
				System.out.print("가격 : ");
				
				try {
					price = Integer.valueOf(scan.nextLine());
					
					System.out.print("페이지 : ");
					totalPage = Integer.valueOf(scan.nextLine());

					
					Main.bkList.addBook(bookName, author, price, totalPage, keyWord);
				} catch (NumberFormatException e) {
					System.out.println("숫자만 입력해주세요.");
					return;
				}
			break;
			
			case 2:
				System.out.println("");
				System.out.print("도서 이름 : ");
				bookName = scan.nextLine();
				
				System.out.print("저자 이름 : ");
				author = scan.nextLine();
				
				int tmp = Main.bkList.searchBookIndex(bookName, author);
				if(tmp == -1) {
					System.out.println("삭제 할 데이터가 없습니다.");
					return;
				}
				Main.bkList.delBook(tmp);
				
				System.out.println("정상적으로 도서가 삭제 처리 되었습니다.");
				System.out.println("");
			break;
			
			case 3:
				Main.searchBook(scan, bookList.SEARCH_BOOK_NAME);
			break;
			
			case 4:
				Main.searchBook(scan, bookList.SEARCH_BOOK_KEYWORD);
			break;
			
			case 5:
				Main.allPrint();
				break;
				
			case 6:
				Main.rentBook(scan);
				break;
				
			case 7:
				Main.returnBook(scan);
				break;
				
			case 8:
				Main.searchBook(scan, bookList.SEARCH_BOOK_DETAIL);
				break;
				
			case 9:
				Main.saveData();
				System.out.println("모든 데이터가 저장 되었습니다.");
				System.exit(0);
				return;
				
			default:
				break;
		}
	}

}
