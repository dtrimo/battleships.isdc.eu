package eu.isdc.internship.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class Friendship models pairs of users who are friends.
 */
@Entity
@Table(name = "FRIENDS")
public class Friendship {

	/** The friendship id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "friendship_id", nullable = false)
	private Long friendshipId;

	/** The user1. */
	@ManyToOne
	@JoinColumn(name = "user1_id")
	private User user1;

	/** The user2. */
	@ManyToOne
	@JoinColumn(name = "user2_id")
	private User user2;

	/**
	 * Instantiates a new friendship.
	 */
	public Friendship() {
	}

	/**
	 * Instantiates a new friendship between two users.
	 *
	 * @param user1
	 *            the user1
	 * @param user2
	 *            the user2
	 */
	public Friendship(User user1, User user2) {
		this.user1 = user1;
		this.user2 = user2;
	}

	/**
	 * Gets the user1.
	 *
	 * @return the user1
	 */
	public User getUser1() {
		return user1;
	}

	/**
	 * Sets the user1.
	 *
	 * @param user1
	 *            the new user1
	 */
	public void setUser1(User user1) {
		this.user1 = user1;
	}

	/**
	 * Gets the user2.
	 *
	 * @return the user2
	 */
	public User getUser2() {
		return user2;
	}

	/**
	 * Sets the user2.
	 *
	 * @param user2
	 *            the new user2
	 */
	public void setUser2(User user2) {
		this.user2 = user2;
	}

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

}
