package eu.isdc.internship.service.livegame.outbound.messages;

/**
 * The Class EndConfigMessage.
 */
public class EndConfigMessage {

	private String type = "END_CONFIG";
	private boolean configSuccess;

	/**
	 * Instantiates a new end config message.
	 */
	public EndConfigMessage() {
		this.configSuccess = true;
	}

	/**
	 * Instantiates a new end config message.
	 *
	 * @param configSuccess
	 *            the config success
	 */
	public EndConfigMessage(boolean configSuccess) {
		this.configSuccess = configSuccess;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Checks if is config success.
	 *
	 * @return true, if is config success
	 */
	public boolean isConfigSuccess() {
		return configSuccess;
	}

	/**
	 * Sets the config success.
	 *
	 * @param configSuccess
	 *            the new config success
	 */
	public void setConfigSuccess(boolean configSuccess) {
		this.configSuccess = configSuccess;
	}

}
