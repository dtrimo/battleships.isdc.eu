package eu.isdc.internship.db.dto;

public class FriendDTO {
	private Long friendship_id;
	private UserDTO user1;
	private UserDTO user2;
		
	public Long getFriendship_id() {
		return friendship_id;
	}
	public void setFriendship_id(Long friendship_id) {
		this.friendship_id = friendship_id;
	}
	public UserDTO getUser1() {
		return user1;
	}
	public void setUser1(UserDTO user1) {
		this.user1 = user1;
	}
	public UserDTO getUser2() {
		return user2;
	}
	public void setUser2(UserDTO user2) {
		this.user2 = user2;
	}
}
