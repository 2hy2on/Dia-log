package model.dto.user;

public class User {
private String ID;
private String Password;
private String userName;
private String Gender;
private String Email;
private String Movie_interest;
private String Book_interest;
private String Music_interest;
private int UserID;

public int getUserID() {
	return UserID;
}
public void setUserID(int userID) {
	UserID = userID;
}
public String getID() {
    return ID;
}
public void setID(String id) {
    this.ID = id;
}
public String getPassword() {
    return Password;
}
public void setPassword(String password) {
    Password = password;
}
public String getUserName() {
    return userName;
}
public void setUserName(String userName) {
    this.userName = userName;
}
public String getGender() {
    return Gender;
}
public void setGender(String gender) {
    Gender = gender;
}
public String getEmail() {
    return Email;
}
public void setEmail(String email) {
    Email = email;
}
public String getMovie_interest() {
    return Movie_interest;
}
public void setMovie_interest(String movie_interest) {
    Movie_interest = movie_interest;
}
public String getBook_interest() {
    return Book_interest;
}
public void setBook_interest(String book_interest) {
    Book_interest = book_interest;
}
public String getMusic_interest() {
    return Music_interest;
}
public void setMusic_interest(String music_interest) {
    Music_interest = music_interest;
}
}

