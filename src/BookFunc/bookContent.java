package BookFunc;

import java.util.Scanner;

import Yang30216.Main;

public class bookContent {
	private static void printMenu() {
		System.out.println("----- ���� ���� ���α׷� -----");
		System.out.println("1. ���� �߰�");
		System.out.println("2. ���� ����");
		System.out.println("3. ������ �˻�");
		System.out.println("4. Ű���� �˻�");
		System.out.println("5. ��ü ���� ���");
		System.out.println("6. ���� �뿩");
		System.out.println("7. ���� �ݳ�");
		System.out.println("8. ���� ���� �˻�");
		System.out.println("9. ����");
		System.out.print("===> �޴� ��ȣ : ");
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
