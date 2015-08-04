package eu.isdc.internship.exception;

public class UserAlreadyExistsException extends BattleshipsException{

	private static final long serialVersionUID = -672029056325702114L;

	public UserAlreadyExistsException(){
		super();
	}
	
	public UserAlreadyExistsException(String message){
		super(message);
	}
	
	public UserAlreadyExistsException(Throwable t){
		super(t);
	}
	
	public UserAlreadyExistsException(String message, Throwable t){
		super(message,t);
	}
	
}
