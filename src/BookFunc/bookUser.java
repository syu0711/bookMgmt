package BookFunc;


public class bookUser {
	private String _name;
	private String _address;
	private String _phone;
	
	public bookUser() {
		// TODO Auto-generated constructor stub
	}
	
	public bookUser(String _name, String _address, String _phone) {
		this._name = _name;
		this._address = _address;
		this._phone = _phone;
	}
	
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public String get_address() {
		return _address;
	}
	public void set_address(String _address) {
		this._address = _address;
	}
	public String get_phone() {
		return _phone;
	}
	public void set_phone(String _phone) {
		this._phone = _phone;
	}
}
