package org.roboapp.display;

public class ShowMessage implements DisplayEngine{

	private String msgType;
	private String message;

	/**
	 * @param msgType
	 * @param message
	 */
	public ShowMessage(String msgType, String message) {
		super();
		this.msgType = msgType;
		this.message = message;
	}

	
	public void showMessage() {
		System.out.println("------------------------------------------------------------");
		System.out.println("-- "+this.getMsgType()+" :"+this.getMessage());
		System.out.println("------------------------------------------------------------");
	}

	/**
	 * @return the msgType
	 */
	public String getMsgType() {
		return msgType;
	}

	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	
}
