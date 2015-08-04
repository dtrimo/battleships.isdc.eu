package eu.isdc.internship.web.response;

public class ErrorRedirectResponse extends ErrorResponse {

	private String redirectURL;
	
	public ErrorRedirectResponse(String redirectURL){
		super();
		this.redirectURL=redirectURL;
	}
	
	public ErrorRedirectResponse(String message, String redirectURL){
		super(message);
		this.redirectURL=redirectURL;
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
	
}
