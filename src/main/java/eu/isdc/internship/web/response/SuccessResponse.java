package eu.isdc.internship.web.response;

public class SuccessResponse extends Response{

	private String message;
	
	public SuccessResponse(){
		this.success=true;
	}
	
	public SuccessResponse(String message){
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
