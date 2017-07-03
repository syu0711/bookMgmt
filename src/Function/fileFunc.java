package Function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import BookFunc.book;

public class fileFunc {
	private BufferedWriter bw = null;
	private BufferedReader br = null;
	private File file = null;
	
	public fileFunc(String pathName) {
		// TODO Auto-generated constructor stub
		file = new File(pathName);
		
		if(!(file.exists())) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*
		if(!(dstFile.exists())) {
			try {
				dstFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		*/
		try {
			br = new BufferedReader(new FileReader(pathName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void createBWStream() throws IOException {
		bw = new BufferedWriter(new FileWriter(file, false));
	}
	
	public void writeContent(String _bookName, String _createDate, int _price, int _totalPage, String _author,
			String _keyWord ) throws IOException {
		bw.write(_bookName + "/" + _createDate + "/" + _price + "/" + _totalPage + "/" + _author + "/" + _keyWord + "\r\n");
	}
	
	public void writeContent(String userName, String address, String phoneNum, String bookName, String author) throws IOException {
		bw.write(userName + "/" + address + "/" + phoneNum + "/" + bookName + "/" + author + "\r\n");
	}
	
	public void closeBwStream() throws IOException {
		bw.close();
	}
	
	public void closeBrStream() throws IOException {
		br.close();
	}
	
	public ArrayList<book> readContent() throws IOException {
		ArrayList<book> tmpBookList = new ArrayList<book>();
		StringTokenizer strToken = null;
		book bk = null;
		
		String strLine = null;
		int index = 0;
		
		while((strLine = br.readLine()) != null) {
			bk  = new book();
			//System.out.println(strLine);
			strToken = new StringTokenizer(strLine, "/");
			
			/*
			System.out.println(strToken.nextToken());
			System.out.println(strToken.nextToken());
			System.out.println(strToken.nextToken());
			System.out.println(strToken.nextToken());
			System.out.println(strToken.nextToken());
			System.out.println(strToken.nextToken());
			System.out.println("----");
*/
		
			bk.set_bookName(strToken.nextToken());
			bk.set_createDate(strToken.nextToken());
			bk.set_price(Integer.valueOf(strToken.nextToken()));
			bk.set_totalPage(Integer.valueOf(strToken.nextToken()));
			bk.set_author(strToken.nextToken());
			bk.set_keyWord(strToken.nextToken());
			bk.setIndex(index++);
			
			tmpBookList.add(bk);
		}
		
		if(tmpBookList.size() == 0) {
			return null;
		}
		
		return tmpBookList;
	}
}
