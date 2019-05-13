package com.zversal.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class Users {

	  @Id
	  public String _id;

	  public String username;
	  public String password;

	  public Users() {}

	  public Users(String _id, String username, String password) {
	    this._id = _id;
	    this.username = username;
	    this.password = password;
	  }

	  public void set_id(String _id) { this._id = _id; }

	  public String get_id() { return this._id; }

	  public void setPassword(String password) { this.password = password; }

	  public String getPassword() { return password; }

	  public void setUsername(String username) { this.username = username; }

	  public String getUsername() { return username; }
}
