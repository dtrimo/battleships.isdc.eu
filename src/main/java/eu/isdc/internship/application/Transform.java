package eu.isdc.internship.application;

import java.util.ArrayList;

public class Transform {	
		public static BattleshipTransform reflectX(BattleshipTransform bt){
			
			ArrayList<Integer> xCoords = bt.getxCoords();
			ArrayList<Integer> yCoords = bt.getyCoords();
			
			for (int i = 0; i < yCoords.size(); i++)
				yCoords.set(i, -yCoords.get(i) - 1);
			
			return new BattleshipTransform(xCoords, yCoords);
		}
		
		public static BattleshipTransform reflectY(BattleshipTransform bt) {
			
			ArrayList<Integer> xCoords = bt.getxCoords();
			ArrayList<Integer> yCoords = bt.getyCoords();
			
			for (int i = 0; i < xCoords.size(); i++)
				xCoords.set(i, -xCoords.get(i) - 1);
			
			return new BattleshipTransform(xCoords, yCoords);
		}
		
		public static BattleshipTransform rotateClockwise(BattleshipTransform bt) {
			
			ArrayList<Integer> xCoords = bt.getxCoords();
			ArrayList<Integer> yCoords = bt.getyCoords();
			
			for (int i = 0; i < yCoords.size(); i++) {
				int aux = xCoords.get(i);
				xCoords.set(i, yCoords.get(i));
				yCoords.set(i,  -aux - 1);
			}
			return new BattleshipTransform(xCoords, yCoords);
		}
		
		public static BattleshipTransform rotateCounterClockwise(BattleshipTransform bt) {
		
			ArrayList<Integer> xCoords = bt.getxCoords();
			ArrayList<Integer> yCoords = bt.getyCoords();
			
			for (int i = 0; i < yCoords.size(); i++) {
				int aux = xCoords.get(i);
				xCoords.set(i, -yCoords.get(i) - 1);
				yCoords.set(i, aux);
			}
			
			return new BattleshipTransform(xCoords, yCoords);
		}
}
