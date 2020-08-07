package com.mckesson.producer.entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author anoopunnikrishnan
 *
 */

@EntityScan
public class Message {

    private String appName;
    private String incomingMessage;



    public String getIncomingMessage() {
        return incomingMessage;
    }

    public void setIncomingMessage(String incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}