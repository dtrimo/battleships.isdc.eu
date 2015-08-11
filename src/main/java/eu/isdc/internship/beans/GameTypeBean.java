package eu.isdc.internship.beans;

import java.util.List;

public class GameTypeBean {

	private String name;
	private String shortDescription;
	private Integer n;
	private Integer m;
	private List<AvailableBattleshipModelBean> battleships;
	private Long id;

	public Long getId(){
		return id;
	}
	
	public void setId(Long newId){
		id = newId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public Integer getM() {
		return m;
	}

	public void setM(Integer m) {
		this.m = m;
	}

	public List<AvailableBattleshipModelBean> getBattleships() {
		return battleships;
	}

	public void setBattleships(List<AvailableBattleshipModelBean> battleships) {
		this.battleships = battleships;
	}

}
