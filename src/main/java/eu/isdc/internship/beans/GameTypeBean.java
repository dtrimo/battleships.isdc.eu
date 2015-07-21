package eu.isdc.internship.beans;

import java.util.List;

public class GameTypeBean {

	private String name;
	private String shortDescription;
	private Integer n;
	private Integer m;
	private List<BattleshipModelBean> battleships;

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

	public List<BattleshipModelBean> getBattleships() {
		return battleships;
	}

	public void setBattleships(List<BattleshipModelBean> battleships) {
		this.battleships = battleships;
	}

}
