package userList;
import android.widget.Toast;

import java.util.LinkedList;
import user.User;

public class UserList {

	private LinkedList<User> userList = new LinkedList<User>();
	private static UserList singleton = new UserList();


	/**
	 * Get instance of a singleton
	 * @return
	 */
	public static UserList getInstance() {
		return singleton;
	}

	/**
	 * Old Constructor
	 */
	/*
	public UserList() {
		this.userList = new LinkedList<User>();
	}
	*/

	/**
	 * Add user contains at least three main arguments: username, password, email
	 * username and email
	 * @param username
	 * @param password
	 * @param email
	 */
	public boolean addUser(String username, String password, String email) {

		for (int i = 0; i < userList.size(); i++) {
			if (username.equals(((User) userList.get(i)).getUsername())) {
                System.err.println("Sorry, this username is already taken.");
				return false;
			}

		}
		User user = new User(username, password, email, "RandomID");
		userList.add(user);
        return true;
	}

	public String displayUsernames() {
		String buffer = "";
		for (int i = 0; i < userList.size(); i++) {
			buffer += "\nUsername: " + ((User) userList.get(i)).getUsername();
		}
		return buffer;
	}

	public int getNumOfUsers() {
		return userList.size();
	}

	public User getUser(String username) {
		for (int i = 0; i < userList.size(); i++) {
			if (username.equals(((User) userList.get(i)).getUsername())) {
				return ((User) userList.get(i));
			}
		}
		return null;
	}

	public boolean login(String username, String password) {
		for (int i = 0; i <userList.size(); i++) {
			if (username.equals(((User) userList.get(i)).getUsername())) {
				if (password.equals(((User) userList.get(i)).getPassword())) {
					System.out.println("Login successful!");
					return true;
				}
				else {
					System.err.println("Login failed.");
					return false;
				}
			}
		}
		System.err.println("Login failed."); //Make sure to add this one too
		return false;
	}

}
