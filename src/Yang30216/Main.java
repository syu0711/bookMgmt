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
		
		Main.file = new fileFunc("30216_�缮��.txt");
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
		System.out.print("������ : ");
		bookName = scan.nextLine();
		
		System.out.print("���ڸ� : ");
		bookAuthor = scan.nextLine();
		
		if(Main.bkList.moreBook(bookName, bookAuthor) == -1) {
			System.out.println("�˼��մϴ�. ���� �Ͻ� å�� ��� �����ϴ�.");
			System.out.println("������ �̿� �� �ֽñ� �ٶ��ϴ�.");
			System.out.println("");
			
			return;
		}
		
		System.out.print("����� �̸� : ");
		userName = scan.nextLine();
		
		System.out.print("����� ������ : ");
		address = scan.nextLine();
		
		System.out.print("����� ����ó : ");
		phoneNum = scan.nextLine();
		
		Main.bkList.rentUser(userName, phoneNum, address, bookName, bookAuthor);
		System.out.println("");
		
		System.out.println("--- ���� ���� ��� ---");
		System.out.println(userName + "�Կ��� " + bookName + "(" + bookAuthor + ") ������ ������ �뿩 �Ǿ����ϴ�.");
		System.out.println("");
	}
	
	public static void returnBook(Scanner scan) {
		String userName, address, phoneNum, bookName, bookAuthor;
		int res = 0;
		
		System.out.println("");
		System.out.print("������ : ");
		bookName = scan.nextLine();
		
		System.out.print("���ڸ� : ");
		bookAuthor = scan.nextLine();
		
		System.out.print("����� �̸� : ");
		userName = scan.nextLine();
		
		System.out.print("����� ������ : ");
		address = scan.nextLine();
		
		System.out.print("����� ����ó : ");
		phoneNum = scan.nextLine();
		
		res = Main.bkList.returnUser(userName, phoneNum, address, bookName, bookAuthor);
	
		if(res == -1) {
			System.out.println("�ݳ� �Ͻ÷��� ������ �뿩 ������ �����ϴ�.");
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
		System.out.println("--- ��ü ���� ��� ---");
		
		tmpBookList = Main.bkList.searchBookObj("null", bookList.SEARCH_BOOK_ALL, bookList.SEARCH_NONE_OPT);
		
		if(tmpBookList == null) {
			System.out.println("��� ����� �������� �ʽ��ϴ�.");
			System.out.println("");
			return;
		}
		
		System.out.println("�� " + tmpBookList.size() + "���� ������ �˻� �Ǿ����ϴ�.");
		System.out.println("");
		
		itr = tmpBookList.iterator();
		
		while(itr.hasNext()) {
			bk = itr.next();
			
			System.out.println(current + "�� ������ �˻� ����Դϴ�.");
			
			System.out.println("������ : " + bk.get_bookName() + " / " + "���� : " + bk.get_author());
			System.out.println("�Ⱓ�� : " + bk.get_createDate() + " / " + "���� : " + bk.get_price() + "��");
			System.out.println("Ű���� : " + bk.get_keyWord() + " / " + "������ : " + bk.get_totalPage() + "��");
			System.out.println("");
			
			current++;
		}
		
		System.out.println("--- ����� �Ϸ� �Ǿ����ϴ� ---");
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
		System.out.print("�˻� �� ������ : ");
		bookName = scan.nextLine();
		
		System.out.println("");
		System.out.println("--- �˻� ��� ---");
		
		tmpBookList = Main.bkList.searchBookObj(bookName, type, bookList.SEARCH_SIM);
		
		if(tmpBookList == null) {
			System.out.println("�˻� ����� �������� �ʽ��ϴ�.");
			System.out.println("");
			return;
		}
		
		System.out.println("�� " + tmpBookList.size() + "���� ������ �˻� �Ǿ����ϴ�.");
		System.out.println("");
		
		itr = tmpBookList.iterator();

		
		while(itr.hasNext()) {
			bk = itr.next();
			
			System.out.println(current + "�� ������ �˻� ����Դϴ�.");
			
			System.out.println("������ : " + bk.get_bookName() + " / " + "���� : " + bk.get_author());
			System.out.println("�Ⱓ�� : " + bk.get_createDate() + " / " + "���� : " + bk.get_price() + "��");
			System.out.println("Ű���� : " + bk.get_keyWord() + " / " + "������ : " + bk.get_totalPage() + "��");
			System.out.println("");
			
			current++;
		}
		
		System.out.println("--- �˻��� �Ϸ� �Ǿ����ϴ� ---");
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
				System.out.print("���� �̸� : ");
				bookName = scan.nextLine();
				
				System.out.print("���� �̸� : ");
				author = scan.nextLine();
				
				System.out.print("Ű���� : ");
				keyWord = scan.nextLine();
				
				System.out.print("���� : ");
				
				try {
					price = Integer.valueOf(scan.nextLine());
					
					System.out.print("������ : ");
					totalPage = Integer.valueOf(scan.nextLine());

					
					Main.bkList.addBook(bookName, author, price, totalPage, keyWord);
				} catch (NumberFormatException e) {
					System.out.println("���ڸ� �Է����ּ���.");
					return;
				}
			break;
			
			case 2:
				System.out.println("");
				System.out.print("���� �̸� : ");
				bookName = scan.nextLine();
				
				System.out.print("���� �̸� : ");
				author = scan.nextLine();
				
				int tmp = Main.bkList.searchBookIndex(bookName, author);
				if(tmp == -1) {
					System.out.println("���� �� �����Ͱ� �����ϴ�.");
					return;
				}
				Main.bkList.delBook(tmp);
				
				System.out.println("���������� ������ ���� ó�� �Ǿ����ϴ�.");
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
				System.out.println("��� �����Ͱ� ���� �Ǿ����ϴ�.");
				System.exit(0);
				return;
				
			default:
				break;
		}
	}

}
