package BookFunc;

import java.util.ArrayList;
import java.util.Iterator;

import Function.dateFunc;

public class bookList {
	private Iterator<book> _itr = null;
	private ArrayList<book> _bookList = null;
	private int _currentBookIndex = 0;
	
	public static final int SEARCH_BOOK_NAME = 0x01;
	public static final int SEARCH_BOOK_KEYWORD = 0x02;
	public static final int SEARCH_BOOK_ALL = 0x03;
	public static final int SEARCH_BOOK_DETAIL = 0x04;
	
	public static final int SEARCH_SIM = 0x01;
	public static final int SEARCH_ACC = 0x02;
	public static final int SEARCH_NONE_OPT = -1;
	
	
	public bookList() {
		// TODO Auto-generated constructor stub
		this._bookList = new ArrayList<book>();
	}
	
	public bookList(ArrayList<book> bkList) {
		// TODO Auto-generated constructor stub
		this._bookList = bkList;
	}
	
	public int moreBook(String bookName, String Author) {
		int bookIndex = searchBookIndex(bookName, Author);
		
		try {	
			if(this._bookList.get(bookIndex).get_totalNumber() == 0)
				return -1;
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}

		return bookIndex;
	}

	public int rentUser(String userName, String phoneNum, String address, String bookName, String Author) {
		
		int index = moreBook(bookName, Author);
		
		if(index == -1)
			return -1;
		
		bookUser bkUser = new bookUser(userName, address, phoneNum);
		this._bookList.get(index).rentUser(bkUser);
		
		return 0;
	}
	
	public int returnUser(String userName, String phoneNum, String address, String bookName, String Author) {
		book bk = searchBookObj(bookName, Author);
		
		if(bk == null) return -1;
		
		bk.returnUser(userName, address, phoneNum, bookName, Author);
		
		return 0;
	}
	
	public void addBook(String bookName, String author, int price, int totalPage, String keyword) {
		book bk = new book(this._currentBookIndex++, bookName, dateFunc.getToday(), price, totalPage, author, keyword);
		this._bookList.add(bk);
	}
	
	public void delBook(int index) {
		this._bookList.remove(index);
	}
	
	public int searchBookIndex(String bookName, String author) {
		book bk = new book();
		this._itr = this._bookList.iterator();
		
		while(this._itr.hasNext()) {
			bk = this._itr.next();
			
			if(compareString(bk.get_bookName(), bookName) && compareString(bk.get_author(), author)) {
				return bk.getIndex();
			}
		}
		
		return -1;
	}

	public ArrayList<book> searchBookObj(String data, int type, int similar) {
		book bk = new book();
		this._itr = this._bookList.iterator();
		ArrayList<book> tmpBookList = new ArrayList<book>();
		
		while(this._itr.hasNext()) {
			bk = this._itr.next();
			
			switch(type) {
				case bookList.SEARCH_BOOK_NAME:
					if(chkSearchData(bk.get_bookName(), data, similar)) {
						tmpBookList.add(bk);
					}
				break;
				
				case bookList.SEARCH_BOOK_KEYWORD:
					if(chkSearchData(bk.get_keyWord(), data, similar)) {
						tmpBookList.add(bk);
					}
				break;
				
				case bookList.SEARCH_BOOK_ALL:
					tmpBookList.add(bk);
				break;
				
				case bookList.SEARCH_BOOK_DETAIL:
						if(bk.get_keyWord().contains(data) || bk.get_author().contains(data) || bk.get_bookName().contains(data))
							tmpBookList.add(bk);
				break;
				
			}
			
		}
		
		if(tmpBookList.size() == 0)
			return null;
		
		return tmpBookList;
	}
	
	private boolean chkSearchData(String src, String dst, int type) {
		switch(type) {
			case bookList.SEARCH_SIM:
				if(src.contains(dst)) {
					return true;
				}
			break;
			
			case bookList.SEARCH_ACC:
				if(compareString(src, dst)) {
					return true;
				}
			break;
		}
		
		return false;
	}
	
	public book searchBookObj(String bookName, String author) {
		book bk = new book();
		this._itr = this._bookList.iterator();
		
		while(this._itr.hasNext()) {
			bk = this._itr.next();
			
			if(compareString(bk.get_bookName(), bookName) && compareString(bk.get_author(), author)) {
				return bk;
			}
		}
		
		return null;
	}
	
	private boolean compareString(String src, String dst) {
		if(src.equals(dst)) {
			return true;
		}
		
		return false;
	}
}
