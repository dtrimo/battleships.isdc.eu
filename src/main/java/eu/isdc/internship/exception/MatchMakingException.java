package eu.isdc.internship.exception;

public class MatchMakingException extends BattleshipsException{

	private static final long serialVersionUID = 1L;

	public MatchMakingException(){
		super();
	}
	
	public MatchMakingException(String message){
		super(message);
	}
	
	public MatchMakingException(Throwable t){
		super(t);
	}
	
	public MatchMakingException(String message, Throwable t){
		super(message,t);
	}
	
}
