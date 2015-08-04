package eu.isdc.internship.web.response;

public class ErrorResponse extends Response{

	private String message;
	
	public ErrorResponse(){
		this.success=false;
	}
	
	public ErrorResponse(String message){
		this();
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
