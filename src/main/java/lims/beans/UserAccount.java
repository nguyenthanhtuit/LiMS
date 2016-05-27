package lims.beans;

public class UserAccount {

	private String userName;
	private String password;
	private String mail;
	private String firstName;
	private String lastName;
	

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserAccount(String userName, String password, String mail, String firstName, String lastName) {
		super();
		this.userName = userName;
		this.password = password;
		this.mail = mail;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UserAccount() {
		super();
	}

	@Override
	public String toString() {
		return "UserAccount [userName=" + userName + ", password=" + password + ", mail=" + mail + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}
	
}
