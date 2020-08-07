/**
 * 
 */
package com.mckesson.consumer.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author jakeermahamad
 *
 */
@EntityScan
public class Message {

	public Message() {
		super();
	}
	private String appName;
    private String incomingMessage;
    private String topicName;
    
    
    
    
	public Message(String appName, String incomingMessage) {
		super();
		this.appName = appName;
		this.incomingMessage = incomingMessage;
	}
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getIncomingMessage() {
		return incomingMessage;
	}
	public void setIncomingMessage(String incomingMessage) {
		this.incomingMessage = incomingMessage;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	

}
