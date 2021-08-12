package org.jpereda.attach.sms.impl;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jpereda.attach.sms.Message;
import org.jpereda.attach.sms.SmsService;

/**
 * Android implementation of the sms service provider
 *
 * @author edgar
 */
public class AndroidSmsService implements SmsService {

    private static final ObservableList<Message> messages = FXCollections.observableArrayList();
    static {
        System.loadLibrary("sms");
    }

    /**
     * default constructor
     */
    public AndroidSmsService() {
    }

    
    @Override
    public ObservableList<Message> getMessages() {
        return messages;
        
    }

    //callback 
    /**
     * used as an sms call back 
     * @param phone receiver contact
     * @param sms message body
     */
    public static void receiveSms(String phone, String sms) {
        Platform.runLater(() -> messages.add(new Message(phone, sms)));
    }
}
