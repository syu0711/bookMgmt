package BookFunc;

import java.util.Scanner;

import Yang30216.Main;

public class bookContent {
	private static void printMenu() {
		System.out.println("----- 도서 관리 프로그램 -----");
		System.out.println("1. 도서 추가");
		System.out.println("2. 도서 삭제");
		System.out.println("3. 도서명 검색");
		System.out.println("4. 키워드 검색");
		System.out.println("5. 전체 도서 출력");
		System.out.println("6. 도서 대여");
		System.out.println("7. 도서 반납");
		System.out.println("8. 도서 통합 검색");
		System.out.println("9. 종료");
		System.out.print("===> 메뉴 번호 : ");
	}
	
	public static void setMenu() {
		Scanner scan = new Scanner(System.in);
		int selectNum = -1;
		
		while(true) {
			printMenu();
			
			selectNum = scan.nextInt();
			scan.nextLine();
			
			Main.runningContent(selectNum, scan);
		}
	}
}
