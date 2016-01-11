package eu.isdc.internship.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FRIENDS")
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FRIENDSHIP_ID", nullable = false)
	private Long friendship_id;

	@ManyToOne
	@JoinColumn(name = "user1_id")
	private User user1;
	
	@ManyToOne
	@JoinColumn(name = "user2_id")
	private User user2;

	public Friend(){}
	public Friend(User user1, User user2){
		this.user1=user1;
		this.user2=user2;
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
	
	public Long getFriendship_id() {
		return friendship_id;
	}

	public void setFriendship_id(Long friendship_id) {
		this.friendship_id = friendship_id;
	}
	
}
