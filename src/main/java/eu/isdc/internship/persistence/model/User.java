package eu.isdc.internship.persistence.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * The Class User.
 */
@Entity
@Table(name = "USERS")
public class User {

	/** The user id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", nullable = false)
	private Long userId;

	/** The name. */
	@Column(name = "user_name", unique = true, nullable = false)
	private String name;

	/** The password. */
	@Column(name = "user_password", nullable = false)
	private String password;

	/** The birth date. */
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date", nullable = false, length = 10)
	private Date birthDate;

	/** The statistic. */
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	@Cascade(value = CascadeType.ALL)
	private Statistics statistic;

	/** The start config. */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
	@Cascade(value = CascadeType.ALL)
	private List<StartConfig> startConfig;

	/** The move. */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
	@Cascade(value = CascadeType.ALL)
	private List<Move> move;

	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param usern
	 *            the usern
	 * @param pass
	 *            the pass
	 * @param date
	 *            the date
	 */
	public User(String usern, String pass, Date date) {
		this.name = usern;
		this.password = pass;
		this.birthDate = date;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the birth date.
	 *
	 * @return the birth date
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the birth date.
	 *
	 * @param birthDate
	 *            the new birth date
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the start config.
	 *
	 * @return the start config
	 */
	public List<StartConfig> getStartConfig() {
		return startConfig;
	}

	/**
	 * Sets the start config.
	 *
	 * @param startConfig
	 *            the new start config
	 */
	public void setStartConfig(List<StartConfig> startConfig) {
		this.startConfig = startConfig;
	}

	/**
	 * Gets the statistic.
	 *
	 * @return the statistic
	 */
	public Statistics getStatistic() {
		return statistic;
	}

	/**
	 * Sets the statistic.
	 *
	 * @param statistic
	 *            the new statistic
	 */
	public void setStatistic(Statistics statistic) {
		this.statistic = statistic;
	}

	/**
	 * Gets the move.
	 *
	 * @return the move
	 */
	public List<Move> getMove() {
		return move;
	}

	/**
	 * Sets the move.
	 *
	 * @param move
	 *            the new move
	 */
	public void setMove(List<Move> move) {
		this.move = move;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		if (!(other instanceof User)) {
			return false;
		}
		User user = (User) other;
		return user.userId == this.userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(this.userId).toHashCode();
	}
}
