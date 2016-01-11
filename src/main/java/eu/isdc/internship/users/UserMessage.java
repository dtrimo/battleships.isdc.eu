package eu.isdc.internship.users;

import java.util.Date;

/**
 * The Class UserMessage.
 */
public class UserMessage {

	/** The sender name. */
	private String senderName;
	
	/** The receiver name. */
	private String receiverName;
	
	/** The message. */
	private String message;
	
	/** The timestamp. */
	private Date timestamp;
	
	/**
	 * Gets the sender name.
	 *
	 * @return the sender name
	 */
	public String getSenderName() {
		return senderName;
	}
	
	/**
	 * Sets the sender name.
	 *
	 * @param senderName the new sender name
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	/**
	 * Gets the receiver name.
	 *
	 * @return the receiver name
	 */
	public String getReceiverName() {
		return receiverName;
	}
	
	/**
	 * Sets the receiver name.
	 *
	 * @param receiverName the new receiver name
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
