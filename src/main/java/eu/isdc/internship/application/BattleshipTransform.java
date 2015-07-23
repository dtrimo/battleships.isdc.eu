package eu.isdc.internship.application;

import java.util.ArrayList;

public class BattleshipTransform {
	public ArrayList<Integer> xCoords;
	public ArrayList<Integer> yCoords;
	
	public BattleshipTransform(ArrayList<Integer >xCoords, ArrayList<Integer> yCoords) {
		this.xCoords = new ArrayList<Integer>();
		this.yCoords = new ArrayList<Integer>();
	}
	public ArrayList<Integer> getxCoords() {
		return xCoords;
	}
	public void setxCoords(ArrayList<Integer> xCoords) {
		this.xCoords = xCoords;
	}
	public ArrayList<Integer> getyCoords() {
		return yCoords;
	}
	public void setyCoords(ArrayList<Integer> yCoords) {
		this.yCoords = yCoords;
	}
	
}
