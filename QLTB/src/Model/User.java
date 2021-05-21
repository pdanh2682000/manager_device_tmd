package Model;

public class User {
	private String username, password;
	
	public User() {
		username = new String("");
		password = new String("");
	}
	public User(String username, String password) {
		this.username = new String(username);
		this.password = new String(password);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
