package eu.isdc.internship.application;

import java.util.ArrayList;
import java.util.List;

//the big class Solver
public class Solver {
	
	//the list with the all point which are on board
	public List< Pair > listBig = new ArrayList<Pair>();
	private int  n , m;
	public int Desen[][];
	
	//constructor
	public Solver(int n , int m )
	{
		this.n = n;
		this.m = m;
		Desen = new int[n][m];
	}
	
	//here we add initial point for plane figure (considering point (0,0) in position (0,0) in a matrix )
	/*
	 * 		|
	 * 	   ---
	 * 		|
	*/
	public List <Pair> getPointPlane() // initial point for plane figure
	{
		List <Pair> listPlane = new ArrayList<Pair>();
		Pair point1 = new Pair(0,1);
		Pair point2 = new Pair(1,0);
		Pair point3 = new Pair(1,1);
		Pair point4 = new Pair(1,2);
		Pair point5 = new Pair(2,1);
		listPlane.add(point1);
		listPlane.add(point2);
		listPlane.add(point3);
		listPlane.add(point4);
		listPlane.add(point5);
		return listPlane;
	}
	
	
	//here we add initial point for titanic figure (considering point (0,0) in position (0,0) in a matrix )
		/*
		 * 		|
		 * 	   ---
		*/
	public List <Pair> getPointTitanic() // initial point for titanic figure
	{
		List <Pair> listTitanic = new ArrayList<Pair>();
		Pair point1 = new Pair(0,1);
		Pair point2 = new Pair(1,0);
		Pair point3 = new Pair(1,1);
		Pair point4 = new Pair(1,2);
		listTitanic.add(point1);
		listTitanic.add(point2);
		listTitanic.add(point3);
		listTitanic.add(point4);
		return listTitanic;
	}
	
	//here we add initial point for L figure (considering point (0,0) in position (0,0) in a matrix )
			/*
			 * 		|
			 * 	   	|
			 * 		|_
			*/
	public List <Pair> getPointL() // initial point for L figure
	{
		List <Pair> listL = new ArrayList<Pair>();
		Pair point1 = new Pair(0,0);
		Pair point2 = new Pair(1,0);
		Pair point3 = new Pair(2,0);
		Pair point4 = new Pair(2,1);
		listL.add(point1);
		listL.add(point2);
		listL.add(point3);
		listL.add(point4);
		return listL;
	}
	
	//here we add initial point for simple1 figure (considering point (0,0) in position (0,0) in a matrix )
	/*
	 * ----
	*/
	public List <Pair> getPointSimple1() // initial point for simple figure 1
	{
		List <Pair> listSimple1 = new ArrayList<Pair>();
		Pair point1 = new Pair(0,0);
		Pair point2 = new Pair(0,1);
		Pair point3 = new Pair(0,2);
		Pair point4 = new Pair(0,3);
		listSimple1.add(point1);
		listSimple1.add(point2);
		listSimple1.add(point3);
		listSimple1.add(point4);
		return listSimple1;
	}
	
	//here we add initial point for simple2 figure (considering point (0,0) in position (0,0) in a matrix )
		/*
		 * ---
		*/
	public List <Pair> getPointSimple2() // initial point for simple figure 2
	{
		List <Pair> listSimple2 = new ArrayList<Pair>();
		Pair point1 = new Pair(0,0);
		Pair point2 = new Pair(0,1);
		Pair point3 = new Pair(0,2);
		listSimple2.add(point1);
		listSimple2.add(point2);
		listSimple2.add(point3);
		return listSimple2;
	}
	
	
	//adding new battleShip
	public boolean addNewPositionBattleShip(int X,int Y , int id)
	{
		//we receive a Position on the Board and an Id and we need to build figure based on id we receive
		
		if(id == 1)
		{
			// plane figure
		  List <Pair> listPlane = this.getPointPlane();
		  boolean ok = true;
		  for( int i = 0 ; i < listPlane.size() && ok == true ; i++ )
		  {
			  int x=0 , y=0;
			  x = listPlane.get(i).getX();
			  y = listPlane.get(i).getY();
			  x+=X;
			  y+=Y;
			  
			  //verify that a figure does not match another figure
			  if(listBig.contains(new Pair(x,y)))
				  ok = false;
		  }
		  	//if is ok , we cand draw this figure
		  if(ok == true)
		  {
			  for( int i = 0 ; i < listPlane.size() ; i++ )
			  {
				  int x=0 , y=0;
				  x = listPlane.get(i).getX();
				  y = listPlane.get(i).getY();
				  x+=X;
				  y+=Y;
				  Desen[x][y] = 1;
				  //tre sa fac draw aici
				  //we push the new point in the list with the all points
				  listBig.add(new Pair(x,y));
			  }
		  }
		  
		  if(ok ==true)
			  return true;
		  else
			  return false;
		  
		}
		//look for comments from the first id
		else
		if( id == 2)
		{ // titanic figure
			
			  List <Pair> listTitanic = this.getPointTitanic();
			  boolean ok = true;
			  for( int i = 0 ; i < listTitanic.size() && ok == true ; i++ )
			  {
				  int x=0 , y=0;
				  x = listTitanic.get(i).getX();
				  y = listTitanic.get(i).getY();
				  x+=X;
				  y+=Y;
				  
				  if(listBig.contains(new Pair(x,y)))
					  ok = false;
			  }
			  
			  if(ok == true)
			  {
				  for( int i = 0 ; i < listTitanic.size() ; i++ )
				  {
					  int x=0 , y=0;
					  x = listTitanic.get(i).getX();
					  y = listTitanic.get(i).getY();
					  x+=X;
					  y+=Y;
					  Desen[x][y] = 2;
					  //tre sa fac draw aici
					  listBig.add(new Pair(x,y));
				  }
			  }
			  
			  if(ok ==true)
				  return true;
			  else
				  return false;
			
		}
		//look for comments from the first id
		else
		if( id == 3)
		{ // L
			
			 List <Pair> listL = this.getPointL();
			  boolean ok = true;
			  for( int i = 0 ; i < listL.size() && ok == true ; i++ )
			  {
				  int x=0 , y=0;
				  x = listL.get(i).getX();
				  y = listL.get(i).getY();
				  x+=X;
				  y+=Y;
				  
				  if(listBig.contains(new Pair(x,y)))
					  ok = false;
			  }
			  
			  if(ok == true)
			  {
				  for( int i = 0 ; i < listL.size() ; i++ )
				  {
					  int x=0 , y=0;
					  x = listL.get(i).getX();
					  y = listL.get(i).getY();
					  x+=X;
					  y+=Y;
					  Desen[x][y] = 3;
					  //tre sa fac draw aici
					  listBig.add(new Pair(x,y));
				  }
			  }
			  
			  if(ok ==true)
				  return true;
			  else
				  return false;
		}
		//look for comments from the first id
		else
		if( id == 4)
		{//dreapta 1
			
			List <Pair> listDreapta1 = this.getPointSimple1();
			  boolean ok = true;
			  for( int i = 0 ; i < listDreapta1.size() && ok == true ; i++ )
			  {
				  int x=0 , y=0;
				  x = listDreapta1.get(i).getX();
				  y = listDreapta1.get(i).getY();
				  x+=X;
				  y+=Y;
				  
				  if(listBig.contains(new Pair(x,y)))
					  ok = false;
			  }
			  
			  if(ok == true)
			  {
				  for( int i = 0 ; i < listDreapta1.size() ; i++ )
				  {
					  int x=0 , y=0;
					  x = listDreapta1.get(i).getX();
					  y = listDreapta1.get(i).getY();
					  x+=X;
					  y+=Y;
					  Desen[x][y] = 4;
					  //tre sa fac draw aici
					  listBig.add(new Pair(x,y));
				  }
			  }
			  
			  if(ok ==true)
				  return true;
			  else
				  return false;
			
		}
		//look for comments from the first id
		else
		if(id == 5)
		{ // dreapta2
		  
			List <Pair> listDreapta2 = this.getPointSimple2();
			  boolean ok = true;
			  for( int i = 0 ; i < listDreapta2.size() && ok == true ; i++ )
			  {
				  int x=0 , y=0;
				  x = listDreapta2.get(i).getX();
				  y = listDreapta2.get(i).getY();
				  x+=X;
				  y+=Y;
				  
				  if(listBig.contains(new Pair(x,y)))
					  ok = false;
			  }
			  
			  if(ok == true)
			  {
				  for( int i = 0 ; i < listDreapta2.size() ; i++ )
				  {
					  int x=0 , y=0;
					  x = listDreapta2.get(i).getX();
					  y = listDreapta2.get(i).getY();
					  x+=X;
					  y+=Y;
					  Desen[x][y] = 5;
					  //tre sa fac draw aici
					  listBig.add(new Pair(x,y));
				  }
			  }
			  
			  if(ok ==true)
				  return true;
			  else
				  return false;
				
		}
		
		return false;
		
	}
	
	//Translate a figure
	public boolean Translate(int X , int Y , int id , int flagX , int flagY)
	{
		if(id == 1)
		{
			if(flagX == 1)
			{//translatie pe Ox
			 //calculez punctele in care imi este situata figura
				//figura plane
				List <Pair> listPlane = this.getPointPlane();
				List <Pair> listTransX = new ArrayList<Pair>();
				for( int i = 0 ; i < listPlane.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listPlane.get(i).getX();
					  y = listPlane.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTransX.add(new Pair(x,y));
				}
				
				
				
				boolean ok = true;
				for( int i = 0 ; i < listTransX.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTransX.get(i).getX();
					y = listTransX.get(i).getY();
					x+=3;
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false )
						  ok = false;
				//	Desen[x][y] = 1;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTransX.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTransX.get(i).getX();
						y = listTransX.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y)); // the old figure will be deleted
						//for this figure for translate to Ox we need to add 3 to our x
						x+=3;
						Desen[x][y] = 1;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
				
				
			}
			else
			if(flagY == 1)
			{//translatie pe Oy
			 //calculez punctele in care imi este situata figura	
				List <Pair> listPlane = this.getPointPlane();
				List <Pair> listTransY = new ArrayList<Pair>();
				for( int i = 0 ; i < listPlane.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listPlane.get(i).getX();
					  y = listPlane.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTransY.add(new Pair(x,y));
				}
				
				boolean ok = true;
				for( int i = 0 ; i < listTransY.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTransY.get(i).getX();
					y = listTransY.get(i).getY();
					y+=3;
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false )
						  ok = false;
				//	Desen[x][y] = 1;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTransY.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTransY.get(i).getX();
						y = listTransY.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y));
						//for this figure for translate to Oy we need to add 3 to our y
						y+=3;
						Desen[x][y] = 1;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
				
			}
		}
		else
		if( id == 2)
		{
			if(flagX == 1)
			{//translatie pe Ox
			 //calculez punctele in care imi este situata figura
				//figura plane
				List <Pair> listTitanic = this.getPointTitanic();
				List <Pair> listTransX = new ArrayList<Pair>();
				for( int i = 0 ; i < listTitanic.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listTitanic.get(i).getX();
					  y = listTitanic.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTransX.add(new Pair(x,y));
				}
				boolean ok = true;
				for( int i = 0 ; i < listTransX.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTransX.get(i).getX();
					y = listTransX.get(i).getY();
					//for this figure for translate to Ox we need to add 2 to our x
					x+=2;
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false  )
						  ok = false;
				//	Desen[x][y] = 1;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTransX.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTransX.get(i).getX();
						y = listTransX.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y));
						x+=2;
						Desen[x][y] = 2;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
					
			}
			else
			if(flagY == 1)
			{//translatie pe Oy
			 //calculez punctele in care imi este situata figura	
				List <Pair> listTitanic = this.getPointTitanic();
				List <Pair> listTransY = new ArrayList<Pair>();
				for( int i = 0 ; i < listTitanic.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listTitanic.get(i).getX();
					  y = listTitanic.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTransY.add(new Pair(x,y));
				}
				boolean ok = true;
				for( int i = 0 ; i < listTransY.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTransY.get(i).getX();
					y = listTransY.get(i).getY();
					//for this figure for translate to Oy we need to add 3 to our y
					y+=3;
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false  )
						  ok = false;
				//	Desen[x][y] = 1;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTransY.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTransY.get(i).getX();
						y = listTransY.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y));
						y+=3;
						Desen[x][y] = 2;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
			}
		}
		else
		if(id == 3)
		{
			
			if(flagX == 1)
			{//translatie pe Ox
			 //calculez punctele in care imi este situata figura
				List <Pair> listL = this.getPointL();
				List <Pair> listTransX = new ArrayList<Pair>();
				for( int i = 0 ; i < listL.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listL.get(i).getX();
					  y = listL.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTransX.add(new Pair(x,y));
				}
				
				boolean ok = true;
				for( int i = 0 ; i < listTransX.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTransX.get(i).getX();
					y = listTransX.get(i).getY();
					x+=3;
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false  )
						  ok = false;
				//	Desen[x][y] = 1;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTransX.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTransX.get(i).getX();
						y = listTransX.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y));
						//for this figure for translate to Ox we need to add 3 to our x
						x+=3;
						Desen[x][y] = 3;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
			}
		else
			if(flagY == 1)
			{//translatie pe Oy
			 //calculez punctele in care imi este situata figura	
				List <Pair> listL = this.getPointL();
				List <Pair> listTransY = new ArrayList<Pair>();
				for( int i = 0 ; i < listL.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listL.get(i).getX();
					  y = listL.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTransY.add(new Pair(x,y));
				}
				boolean ok = true;
				for( int i = 0 ; i < listTransY.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTransY.get(i).getX();
					y = listTransY.get(i).getY();
					y+=2;
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false  )
						  ok = false;
				//	Desen[x][y] = 1;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTransY.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTransY.get(i).getX();
						y = listTransY.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y));
						//for this figure for translate to Oy we need to add 2 to our y
						y+=2;
						Desen[x][y] = 3;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
			}
		
		}
		else
		if( id == 4)
		{
			if(flagX == 1)
			{//translatie pe Ox
			 //calculez punctele in care imi este situata figura
				List <Pair> listSimple1 = this.getPointSimple1();
				List <Pair> listTransX = new ArrayList<Pair>();
				for( int i = 0 ; i < listSimple1.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listSimple1.get(i).getX();
					  y = listSimple1.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTransX.add(new Pair(x,y));
				}
				
			
				boolean ok = true;
				for( int i = 0 ; i < listTransX.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTransX.get(i).getX();
					y = listTransX.get(i).getY();
					x+=1;
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false  )
						  ok = false;
				//	Desen[x][y] = 1;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTransX.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTransX.get(i).getX();
						y = listTransX.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y));
						//for this figure for translate to Ox we need to add 1 to our x
						x+=1;
						Desen[x][y] = 4;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
			}
		else
			if(flagY == 1)
			{//translatie pe Oy
			 //calculez punctele in care imi este situata figura	
				List <Pair> listSimple1 = this.getPointSimple1();
				List <Pair> listTransY = new ArrayList<Pair>();
				for( int i = 0 ; i < listSimple1.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listSimple1.get(i).getX();
					  y = listSimple1.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTransY.add(new Pair(x,y));
				}
				boolean ok = true;
				for( int i = 0 ; i < listTransY.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTransY.get(i).getX();
					y = listTransY.get(i).getY();
					y+=4;
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false  )
						  ok = false;
				//	Desen[x][y] = 1;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTransY.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTransY.get(i).getX();
						y = listTransY.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y));
						//for this figure for translate to Oy we need to add 4 to our y
						y+=4;
						Desen[x][y] = 4;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
					
				}	
			}
		else
			if( id == 5)
			{
				if(flagX == 1)
				{//translatie pe Ox
				 //calculez punctele in care imi este situata figura
					List <Pair> listSimple2 = this.getPointSimple2();
					List <Pair> listTransX = new ArrayList<Pair>();
					for( int i = 0 ; i < listSimple2.size(); i++ )
					{
						  int x=0 , y=0;
						  x = listSimple2.get(i).getX();
						  y = listSimple2.get(i).getY();
						  x+=X;
						  y+=Y;
						  listTransX.add(new Pair(x,y));
					}
					
				
					boolean ok = true;
					for( int i = 0 ; i < listTransX.size() && ok == true; i++)
					{
						int x = 0;
						int y = 0;
						x = listTransX.get(i).getX();
						y = listTransX.get(i).getY();
						x+=1;
						if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false  )
							  ok = false;
					//	Desen[x][y] = 1;
					}
					
					if(ok == true)
					{//e ok
						for( int i = 0 ; i < listTransX.size(); i++)
						{
							int x = 0;
							int y = 0;
							x = listTransX.get(i).getX();
							y = listTransX.get(i).getY();
							Desen[x][y] = 0;
							listBig.remove(new Pair(x,y));
							//for this figure for translate to Ox we need to add 3 to our 1
							x+=1;
							Desen[x][y] = 5;
							listBig.add(new Pair(x,y));
						}
						
					}
					
					if(ok == true)
						return true;
					else
						return false;
				}
			else
				if(flagY == 1)
				{//translatie pe Oy
				 //calculez punctele in care imi este situata figura	
					List <Pair> listSimple2 = this.getPointSimple2();
					List <Pair> listTransY = new ArrayList<Pair>();
					for( int i = 0 ; i < listSimple2.size(); i++ )
					{
						  int x=0 , y=0;
						  x = listSimple2.get(i).getX();
						  y = listSimple2.get(i).getY();
						  x+=X;
						  y+=Y;
						  listTransY.add(new Pair(x,y));
					}
					boolean ok = true;
					for( int i = 0 ; i < listTransY.size() && ok == true; i++)
					{
						int x = 0;
						int y = 0;
						x = listTransY.get(i).getX();
						y = listTransY.get(i).getY();
						y+=4;
						if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false  )
							  ok = false;
					//	Desen[x][y] = 1;
					}
					
					if(ok == true)
					{//e ok
						for( int i = 0 ; i < listTransY.size(); i++)
						{
							int x = 0;
							int y = 0;
							x = listTransY.get(i).getX();
							y = listTransY.get(i).getY();
							Desen[x][y] = 0;
							listBig.remove(new Pair(x,y));
							//for this figure for translate to Oy we need to add 3 to our y
							y+=3;
							Desen[x][y] = 5;
							listBig.add(new Pair(x,y));
						}
						
					}
					
					if(ok == true)
						return true;
					else
						return false;
						
				}
			}
		
		return false;
		
	}
	
	public boolean Reflection(int X , int Y , int id , int flagX , int flagY)
	{
		if(id == 1)
		{//plane form
		//for this figure Reflections is exactly like Translation
			return this.Translate(X, Y, id, flagX, flagY);
		}
		else
		if(id == 2)
		{//titanic form
			
			if(flagX == 1)
			{//reflexie pe Ox
			 //calculez punctele in care imi este situata figura
				//figura plane
				List <Pair> listTitanic = this.getPointTitanic();
				List <Pair> listTransX = new ArrayList<Pair>();
				for( int i = 0 ; i < listTitanic.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listTitanic.get(i).getX();
					  y = listTitanic.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTransX.add(new Pair(x,y));
				}
				boolean ok = true;
				for( int i = 0 ; i < listTransX.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTransX.get(i).getX();
					y = listTransX.get(i).getY();
					if(i == 0)
						x+=3;
					else
						x+=1;
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false )
						  ok = false;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTransX.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTransX.get(i).getX();
						y = listTransX.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y));
						//for this figure the reflextion will be (x+3 for the first point and x+1 for the other)
						if(i == 0)
							x+=3;
						else
							x+=1;
						Desen[x][y] = 2;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
					
			}
			else
			if(flagY == 1)
			{//translatie pe Oy
			 //calculez punctele in care imi este situata figura	
				List <Pair> listTitanic = this.getPointTitanic();
				List <Pair> listTransY = new ArrayList<Pair>();
				for( int i = 0 ; i < listTitanic.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listTitanic.get(i).getX();
					  y = listTitanic.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTransY.add(new Pair(x,y));
				}
				boolean ok = true;
				for( int i = 0 ; i < listTransY.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTransY.get(i).getX();
					y = listTransY.get(i).getY();
					y+=3;
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false )
						  ok = false;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTransY.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTransY.get(i).getX();
						y = listTransY.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y));
						//for this figure the reflextion will be y+3
						y+=3;
						Desen[x][y] = 2;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
			}	
		}
		else
		if(id == 3)
		{ // L form
			if(flagX == 1)
			{//reflexie pe Ox
			 //calculez punctele in care imi este situata figura
				List <Pair> listL = this.getPointL();
				List <Pair> listTransX = new ArrayList<Pair>();
				for( int i = 0 ; i < listL.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listL.get(i).getX();
					  y = listL.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTransX.add(new Pair(x,y));
				}
				
				boolean ok = true;
				for( int i = 0 ; i < listTransX.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTransX.get(i).getX();
					y = listTransX.get(i).getY();
					if(i != listTransX.size() - 1)
						x+=3;
					else
						x+=1;
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false )
						  ok = false;
				//	Desen[x][y] = 1;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTransX.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTransX.get(i).getX();
						y = listTransX.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y));
						//for this figure the reflextion will be (x+3 for the first point and x+1 for the other)
						if(i != listTransX.size() - 1)
							x+=3;
						else
							x+=1;
						Desen[x][y] = 3;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
			}
		else
			if(flagY == 1)
			{//translatie pe Oy
			 //calculez punctele in care imi este situata figura	
				List <Pair> listL = this.getPointL();
				List <Pair> listTransY = new ArrayList<Pair>();
				for( int i = 0 ; i < listL.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listL.get(i).getX();
					  y = listL.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTransY.add(new Pair(x,y));
				}
				boolean ok = true;
				for( int i = 0 ; i < listTransY.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTransY.get(i).getX();
					y = listTransY.get(i).getY();
					if(i != listTransY.size() - 1)
						y+=3;
					else
						y+=1;
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false )
						  ok = false;
				//	Desen[x][y] = 1;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTransY.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTransY.get(i).getX();
						y = listTransY.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y));
						//for this figure the reflextion will be (y+3 for the first point and y+1 for the other)
						if(i != listTransY.size() - 1)
							y+=3;
						else
							y+=1;
						Desen[x][y] = 3;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
			}
		}
		else
		if(id == 4)
		{// simple form1
			//for this figure Reflections  is exactly like Translation
			return this.Translate(X, Y, id, flagX, flagY);
		}
		else
		if(id == 5)
		{// simple form2
			//for this figure Reflections is exactly like Translation
			return this.Translate(X, Y, id, flagX, flagY);
		}
			
		return true;
	}
	
	public boolean Rotate(int X , int Y , int id)
	{
		//Rotate function for a figure with position (X,Y)
		if(id == 1)
		{ // plane figure
			List <Pair> listPlane = this.getPointPlane();
			List <Pair> listTrans = new ArrayList<Pair>();
			for( int i = 0 ; i < listPlane.size(); i++ )
			{
				  int x=0 , y=0;
				  x = listPlane.get(i).getX();
				  y = listPlane.get(i).getY();
				  x+=X;
				  y+=Y;
				  listTrans.add(new Pair(x,y));
			}
			boolean ok = true;
			for( int i = 0 ; i < listTrans.size() && ok == true; i++)
			{
				int x = 0;
				int y = 0;
				x = listTrans.get(i).getX();
				y = listTrans.get(i).getY();
				if(i == 0)
				{
					x+=1;
					y-=4;
				}
				else
				if(i == 1)
				{
					x+=1;
					y-=2;
				}
				else
				if( i == 2)
				{
					y-=3;
				}
				else
				if( i == 3)
				{
					x-=1;
					y-=4;
				}
				else
				if( i == 4)
				{
					x-=1;
					y-=2;
				}
					
				if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false )
					  ok = false;
			//	Desen[x][y] = 1;
			}
			
			if(ok == true)
			{//e ok
				for( int i = 0 ; i < listTrans.size(); i++)
				{
					int x = 0;
					int y = 0;
					x = listTrans.get(i).getX();
					y = listTrans.get(i).getY();
					Desen[x][y] = 0;
					listBig.remove(new Pair(x,y));
					if(i == 0)
					{
						x+=1;
						y-=4;
					}
					else
					if(i == 1)
					{
						x+=1;
						y-=2;
					}
					else
					if( i == 2)
					{
						y-=3;
					}
					else
					if( i == 3)
					{
						x-=1;
						y-=4;
					}
					else
					if( i == 4)
					{
						x-=1;
						y-=2;
					}
					Desen[x][y] = 1;
					listBig.add(new Pair(x,y));
				}
				
			}
			
			if(ok == true)
				return true;
			else
				return false;
		}
		else
			if(id == 2)
			{
				List <Pair> listTitanic = this.getPointTitanic();
				List <Pair> listTrans = new ArrayList<Pair>();
				for( int i = 0 ; i < listTitanic.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listTitanic.get(i).getX();
					  y = listTitanic.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTrans.add(new Pair(x,y));
				}
				
				boolean ok = true;
				for( int i = 0 ; i < listTrans.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTrans.get(i).getX();
					y = listTrans.get(i).getY();
					if(i == 0)
					{
						x+=1;
						y-=3;
					}
					else
					if(i == 1)
					{
						x+=1;
						y-=1;
					}
					else
					if( i == 2)
					{
						y-=2;
					}
					else
					if( i == 3)
					{
						x-=1;
						y-=3;
					}					
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false )
						  ok = false;
				//	Desen[x][y] = 1;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTrans.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTrans.get(i).getX();
						y = listTrans.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y));
						if(i == 0)
						{
							x+=1;
							y-=3;
						}
						else
						if(i == 1)
						{
							x+=1;
							y-=1;
						}
						else
						if( i == 2)
						{
							y-=2;
						}
						else
						if( i == 3)
						{
							x-=1;
							y-=3;
						}	
						Desen[x][y] = 2;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
			}
		else
		if( id == 3)
		{//L figure
			List <Pair> listL = this.getPointL();
			List <Pair> listTrans = new ArrayList<Pair>();
			for( int i = 0 ; i < listL.size(); i++ )
			{
				  int x=0 , y=0;
				  x = listL.get(i).getX();
				  y = listL.get(i).getY();
				  x+=X;
				  y+=Y;
				  listTrans.add(new Pair(x,y));
			}
			boolean ok = true;
			for( int i = 0 ; i < listTrans.size() && ok == true; i++)
			{
				int x = 0;
				int y = 0;
				x = listTrans.get(i).getX();
				y = listTrans.get(i).getY();
				if(i == 0)
				{
					x+=2;
					y-=3;
				}
				else
				if(i == 1)
				{
					x+=1;
					y-=2;
				}
				else
				if( i == 2)
				{
					y-=1;
				}
				else
				if( i == 3)
				{
					x-=1;
					y-=2;
				}
				if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false )
					  ok = false;
			//	Desen[x][y] = 1;
			}
			
			if(ok == true)
			{//e ok
				for( int i = 0 ; i < listTrans.size(); i++)
				{
					int x = 0;
					int y = 0;
					x = listTrans.get(i).getX();
					y = listTrans.get(i).getY();
					
				//	System.out.println("Initial : " + x + " " + y + " ");
					
					Desen[x][y] = 0;
					listBig.remove(new Pair(x,y));
					if(i == 0)
					{
						x+=2;
						y-=3;
					}
					else
					if(i == 1)
					{
						x+=1;
						y-=2;
					}
					else
					if( i == 2)
					{
						y-=1;
					}
					else
					if( i == 3)
					{
						x-=1;
						y-=2;
					}
				//	System.out.println(" Final " + " " + x + " " + y + " " + '\n');
					Desen[x][y] = 3;
					listBig.add(new Pair(x,y));
				}
				
			}
			
			if(ok == true)
				return true;
			else
				return false;
		}
		else
			if(id == 4)
			{
				List <Pair> listSimple1 = this.getPointSimple1();
				List <Pair> listTrans = new ArrayList<Pair>();
				for( int i = 0 ; i < listSimple1.size(); i++ )
				{
					  int x=0 , y=0;
					  x = listSimple1.get(i).getX();
					  y = listSimple1.get(i).getY();
					  x+=X;
					  y+=Y;
					  listTrans.add(new Pair(x,y));
				}
				
				boolean ok = true;
				for( int i = 0 ; i < listTrans.size() && ok == true; i++)
				{
					int x = 0;
					int y = 0;
					x = listTrans.get(i).getX();
					y = listTrans.get(i).getY();
					if(i == 0)
					{
						y-=1;
					}
					else
					if(i == 1)
					{
						x-=1;
						y-=2;
					}
					else
					if( i == 2)
					{
						x-=2;
						y-=3;
					}
					else
					if( i == 3)
					{
						x-=3;
						y-=4;
					}					
					if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false )
						  ok = false;
				//	Desen[x][y] = 1;
				}
				
				if(ok == true)
				{//e ok
					for( int i = 0 ; i < listTrans.size(); i++)
					{
						int x = 0;
						int y = 0;
						x = listTrans.get(i).getX();
						y = listTrans.get(i).getY();
						Desen[x][y] = 0;
						listBig.remove(new Pair(x,y));
						if(i == 0)
						{
							y-=1;
						}
						else
						if(i == 1)
						{
							x-=1;
							y-=2;
						}
						else
						if( i == 2)
						{
							x-=2;
							y-=3;
						}
						else
						if( i == 3)
						{
							x-=3;
							y-=4;
						}
						Desen[x][y] = 4;
						listBig.add(new Pair(x,y));
					}
					
				}
				
				if(ok == true)
					return true;
				else
					return false;
			}
			else
				if(id == 5)
				{
					List <Pair> listSimple2 = this.getPointSimple2();
					List <Pair> listTrans = new ArrayList<Pair>();
					for( int i = 0 ; i < listSimple2.size(); i++ )
					{
						  int x=0 , y=0;
						  x = listSimple2.get(i).getX();
						  y = listSimple2.get(i).getY();
						  x+=X;
						  y+=Y;
						  listTrans.add(new Pair(x,y));
					}
					
					boolean ok = true;
					for( int i = 0 ; i < listTrans.size() && ok == true; i++)
					{
						int x = 0;
						int y = 0;
						x = listTrans.get(i).getX();
						y = listTrans.get(i).getY();
						if(i == 0)
						{
							y-=1;
						}
						else
						if(i == 1)
						{
							x-=1;
							y-=2;
						}
						else
						if( i == 2)
						{
							x-=2;
							y-=3;
						}			
						if(listBig.contains(new Pair(x,y)) || Validate(x,y) == false )
							  ok = false;
					//	Desen[x][y] = 1;
					}
					
					if(ok == true)
					{//e ok
						for( int i = 0 ; i < listTrans.size(); i++)
						{
							int x = 0;
							int y = 0;
							x = listTrans.get(i).getX();
							y = listTrans.get(i).getY();
							Desen[x][y] = 0;
							listBig.remove(new Pair(x,y));
							if(i == 0)
							{
								y-=1;
							}
							else
							if(i == 1)
							{
								x-=1;
								y-=2;
							}
							else
							if( i == 2)
							{
								x-=2;
								y-=3;
							}		
							Desen[x][y] = 5;
							listBig.add(new Pair(x,y));
						}
						
					}
					
					if(ok == true)
						return true;
					else
						return false;
				}
		return true;
	}
	
	public int Shoot(int X , int Y)
	{
		//this function is to verify if a position was shot
		if(this.Desen[X][Y] != 0 )
		{//am o pozitie valida in care am tras
		 //verific daca am distrus toata barca sau doar o parte
		int indice = -1;
		List<List<Pair>> list =  this.Build();
		for( int i = 0 ; i < list.size() ; i++)
		{
			for(int j = 0 ; j < list.get(i).size() ; j++)
			//	System.out.println(i + " " + list.get(i).get(j).getX() + " " + list.get(i).get(j).getY());
				if( list.get(i).get(j).getX() == X &&  list.get(i).get(j).getY() == Y )
				{
					indice = i;
				}
		}
		//System.out.println(indice);
		list.get(indice).remove(new Pair(X,Y));
		this.Desen[X][Y] = 0;
		
		if(list.get(indice).size() != 0 )
			return 1; //au mai ramas puncte pe acesta barca
		else
			return 2; // barca cu indicele indice a fost distrusa complet
		 
		}
		else
		{ // nu am pozitie valida
			return 0;
		}
		
	}
	
	public List<List<Pair>>  Build()
	{
		List<List<Pair>> list = new ArrayList<List<Pair>>();
		for(int i = 0 ; i < this.listBig.size() ; i++)
		{
			List<Pair> innerList = new ArrayList<Pair>();
		//	System.out.println("I-ul cu care intru : " + " " + i);
			int j = i + 1;
			int color = Desen[this.listBig.get(i).getX()][this.listBig.get(i).getY()];
			while(this.Desen[this.listBig.get(j).getX()][this.listBig.get(j).getY()] == color && j < this.listBig.size())
			{
				//aici fac operatia cu i si j
		//		System.out.println(i + " " + j + '\n');
				innerList.add(new Pair(this.listBig.get(i).getX(),this.listBig.get(i).getY()));
				i = j;
				j++;
				if( j >= this.listBig.size() )
					break;
				
			}
			innerList.add(new Pair(this.listBig.get(j-1).getX(),this.listBig.get(j-1).getY()));
			i = j-1;
			list.add(innerList);
			//System.out.println(i + " " +  j + "------------------>\n");
		}
		
		return list;
	}
	
	public int[][] getSquare()
	{
		//get the square
		return Desen;
	}
	
	public List<Pair> getBigList()
	{
		//get the bigList
		return listBig;
	}
	
	public int GetSize()
	{
		//get the size of the bigList
		return listBig.size();
	}
	
	public void Restart()
	{
		//memset(desen,0,sizeof(desen)) :))
		for (int c = 0 ; c < n ; c++ )
	     {
	        for (int d = 0 ; d < m ; d++ )
	           Desen[c][d] = 0;
	     }
	}
	
	public int getN() {
		//return n
		return n;
	}

	public void setN(int N) {
		//setter for n
		n = N;
	}

	public int getM() {
		//return m
		return m;
	}

	public void setY(int M) {
		//setter for m
		m = M;
	}
	
	public boolean Validate(int X ,int Y)
	{
		//validate a position to see if she is ok(he is not outside the square, etc..)
		if(X >= 0 && X < n && Y>=0 && Y< m )
			return true;
		return false;
	}

}
	