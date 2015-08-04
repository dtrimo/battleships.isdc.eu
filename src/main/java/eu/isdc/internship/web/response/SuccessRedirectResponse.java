package eu.isdc.internship.web.response;

public class SuccessRedirectResponse extends SuccessResponse {

	private String redirectURL;
	
	public SuccessRedirectResponse(String redirectURL){
		super();
		this.redirectURL=redirectURL;
	}
	
	public SuccessRedirectResponse(String message, String redirectURL){
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
