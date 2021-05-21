package Model;

public class UserDao {
	public boolean checkUser(User u) {
		if (u != null) {
			if ("admin".equals(u.getUsername()) && "admin".equals(u.getPassword()))
				return true;
		}
		return false;
	}
}
