package eu.isdc.internship.persistence.dto;

/**
 * The Class FriendDTO.
 */
public class FriendDTO {

	private Long friendshipId;
	private UserDTO user1;
	private UserDTO user2;

	/**
	 * Gets the friendship id.
	 *
	 * @return the friendship id
	 */
	public Long getFriendshipId() {
		return friendshipId;
	}

	/**
	 * Sets the friendship id.
	 *
	 * @param friendshipId
	 *            the new friendship id
	 */
	public void setFriendshipId(Long friendshipId) {
		this.friendshipId = friendshipId;
	}

	/**
	 * Gets the user1.
	 *
	 * @return the user1
	 */
	public UserDTO getUser1() {
		return user1;
	}

	/**
	 * Sets the user1.
	 *
	 * @param user1
	 *            the new user1
	 */
	public void setUser1(UserDTO user1) {
		this.user1 = user1;
	}

	/**
	 * Gets the user2.
	 *
	 * @return the user2
	 */
	public UserDTO getUser2() {
		return user2;
	}

	/**
	 * Sets the user2.
	 *
	 * @param user2
	 *            the new user2
	 */
	public void setUser2(UserDTO user2) {
		this.user2 = user2;
	}
}
