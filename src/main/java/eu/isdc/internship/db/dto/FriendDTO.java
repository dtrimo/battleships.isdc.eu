package eu.isdc.internship.db.dto;
import eu.isdc.internship.db.model.User;

public class FriendDTO {
	private Long friendship_id;
	private User user1;
	private User user2;
		
	public Long getFriendship_id() {
		return friendship_id;
	}
	public void setFriendship_id(Long friendship_id) {
		this.friendship_id = friendship_id;
	}
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
}
