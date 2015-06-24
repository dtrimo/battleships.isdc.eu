package eu.isdc.internship.application;

//Pair class
public class Pair  {
	
	private int X;
	private int Y;
	
	public Pair(int X , int Y)
	{
		
		this.X = X;
		this.Y = Y;
	}
	
	public Pair()
	{
		
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}
	
	//override this operator for comparing two pairs of points
	@Override
	 public boolean equals(Object o) {
	    if (!(o instanceof Pair)) return false;
	    Pair pairo = (Pair) o;
	    return this.X == pairo.getX() && this.Y == pairo.getY();
	  }
	
	
}
