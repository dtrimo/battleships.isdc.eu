package eu.isdc.internship.db.model;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", nullable = false)
	private Long user_id;

	@Column(name = "USERNAME",unique=true, nullable = false)
	private String name;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE", nullable = false, length = 10)
	private Date birthDate;
	
	@OneToOne(mappedBy = "user",fetch=FetchType.LAZY)
	// 1-1 pentru Statistics
	@Cascade(value = CascadeType.ALL)
	private Statistics statistic;
	
	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY, orphanRemoval = true)
	// 1-n StartConfig
	@Cascade(value = CascadeType.ALL)	
	private List<StartConfig> startConfig;

	@OneToMany(mappedBy = "user",fetch=FetchType.LAZY, orphanRemoval = true)
	// 1-n Moves
	@Cascade(value = CascadeType.ALL)
	private List<Move> move;
	
	public User(){}
	public User(String usern, String pass, Date d){
		this.name=usern;
		this.password=pass;
		this.birthDate=d;		
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<StartConfig> getStartConfig() {
		return startConfig;
	}

	public void setStartConfig(List<StartConfig> startConfig) {
		this.startConfig = startConfig;
	}
	
	public Statistics getStatistic() {
		return statistic;
	}

	public void setStatistic(Statistics statistic) {
		this.statistic = statistic;
	}

	public List<Move> getMove() {
		return move;
	}

	public void setMove(List<Move> move) {
		this.move = move;
	}
}
