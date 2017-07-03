package BookFunc;

import java.util.ArrayList;
import java.util.Iterator;

public class book {
	private int index;
	private String _bookName;
	private String _createDate;
	private int _price;
	private String _author;
	private int _totalPage;
	private String _keyWord;
	
	private int _totalNumber = 1;
	private ArrayList<bookUser> _bkUser = null;
	
	public book() {
		// TODO Auto-generated constructor stub
		init();
	}

	public book(int index, String _bookName, String _createDate, int _price, int _totalPage, String _author,
			String _keyWord) {
		this.index = index;
		this._bookName = _bookName;
		this._createDate = _createDate;
		this._price = _price;
		this._totalPage = _totalPage;
		this._author = _author;
		this._keyWord = _keyWord;
		
		init();
	}
	
	private void init() {
		this._bkUser = new ArrayList<bookUser>();
	}
	
	public void returnUser(String userName, String address, String phoneNum, String bookName, String bookAuthor) {
		int index = searchUserIndex(userName, address, phoneNum);
		
		if(index == -1) {
			System.out.println(bookName + "(" + bookAuthor + ") 저자의 도서 대여 정보가 없습니다.");
			System.out.println("");
			
			return;
		}
		
		this._bkUser.remove(index);
		System.out.println(userName + "님 " + bookName + "(" + bookAuthor + ") 저자의 도서를 반납 처리 하였습니다.");
	}
	
	public int searchUserIndex(String userName, String address, String phoneNum) {
		Iterator<bookUser> itr = this._bkUser.iterator();
		bookUser bkUser = null;
		int currentIndex = 0;
		
		while(itr.hasNext()) {
			bkUser = itr.next();
			
			if(bkUser.get_name().equals(userName)
					&& bkUser.get_address().equals(address)
					&& bkUser.get_phone().equals(phoneNum)
			) {
				return currentIndex;
			}
			
			currentIndex++;
		}
		
		return -1;
	}
	
	public void rentUser(bookUser bkUser) {
		this._bkUser.add(bkUser);
		this._totalNumber--;
	}
	
	public int get_totalNumber() {
		return _totalNumber;
	}

	public void set_totalNumber(int _totalNumber) {
		this._totalNumber = _totalNumber;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String get_bookName() {
		return _bookName;
	}

	public void set_bookName(String _bookName) {
		this._bookName = _bookName;
	}

	public String get_createDate() {
		return _createDate;
	}

	public void set_createDate(String _createDate) {
		this._createDate = _createDate;
	}

	public int get_price() {
		return _price;
	}

	public void set_price(int _price) {
		this._price = _price;
	}

	public String get_author() {
		return _author;
	}

	public void set_author(String _author) {
		this._author = _author;
	}

	public int get_totalPage() {
		return _totalPage;
	}

	public void set_totalPage(int _totalPage) {
		this._totalPage = _totalPage;
	}

	public String get_keyWord() {
		return _keyWord;
	}

	public void set_keyWord(String _keyWord) {
		this._keyWord = _keyWord;
	}
	

}
