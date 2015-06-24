package eu.isdc.internship.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class MainClass {
	
	public static void main(final String[] args)
	{
		
	 Solver y = new Solver(11,11);
	 y.addNewPositionBattleShip(1,6, 2);
	 y.addNewPositionBattleShip(6, 6, 2);
	 y.addNewPositionBattleShip(6, 2, 2);
	 y.addNewPositionBattleShip(3, 6, 5);
	 y.addNewPositionBattleShip(9, 3, 5);
	 y.addNewPositionBattleShip(0, 0, 1);
	 y.addNewPositionBattleShip(3, 0, 3);
	 y.addNewPositionBattleShip(8, 0, 4);
	 
	 int Square2[][] = new int [11][11];
	 Square2 = y.getSquare();
	 for (int c = 0 ; c < 11 ; c++ )
     {
        for (int d = 0 ; d < 11 ; d++ )
           System.out.print(Square2[c][d]+"\t");
        System.out.println();
     }
	 
	
	System.out.println();
	
	}
}
