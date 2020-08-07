package com.mckesson.producer.utilities;

public interface ProducerConstants {

	public static final String TOPIC_DRG_PAYER = "drgpayer";
	public static final String TOPIC_DRG_PLAN = "drgplan";
	public static final String TOPIC_NCPDP = "ncpdp";
	public static final String ERROR_MESSAGE = "Error connecting to DB. Data persistance failed.";
	public static final String SUCCESS_MESSAGE = "Save Successful";
	public static final String REST_API_URL = "http://producerdataservice:9896/mckesson/saveincomingmessage";
	
	public static final String MESSAGE_SEND_TO_TOPIC_FAILED = "Failed to send the message to Topic ===> {}  & the  Message attempted was ===> {} ";
	public static final String MESSAGE_SEND_TO_TOPIC_SUCCESS = "Message Send Successfully to Topic ===> {}  & the  Message send is ===> {} ";
	public static final String MESSAGE_SEND_SUCCESS = "Message send successfully";
	public static final String FAILED_MSG_EMPTY = "Failed to send the message. Message cannot be empty";
	public static final String FAILED_APPL_NAME_EMPTY = "Failed to send the message. Application Name cannot be empty";
	public static final String RECEIVED_MSG_DETAILS ="Recieved Application Name ===> {}  & the  Recieved Message is ===> {} ";
	public static final String INCOMING_MSG_DETAILS_SAVED_IN_DB = "Message saved in db....";
	public static final String LOG_ERROR_AND_CONTINUE = "Log the error and continue with the flow........";
	

}
