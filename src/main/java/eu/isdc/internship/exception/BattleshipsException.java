package eu.isdc.internship.exception;

public class BattleshipsException extends Exception{

	private static final long serialVersionUID = -7146109464831847186L;

	public BattleshipsException(){
		super();
	}
	
	public BattleshipsException(String message){
		super(message);
	}
	
	public BattleshipsException(Throwable t){
		super(t);
	}
	
	public BattleshipsException(String message, Throwable t){
		super(message,t);
	}
	
}
