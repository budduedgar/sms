package org.jpereda.attach.sms.impl;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jpereda.attach.sms.Message;
import org.jpereda.attach.sms.SmsService;

public class AndroidSmsService implements SmsService {

    static {
        System.loadLibrary("sms");
    }
    private final ObservableList<Message> messages = FXCollections.observableArrayList();
    
    @Override
    public ObservableList<Message> getMessages() {
        return messages;
    }

    //callback 
    private void receiveSms(String phone, String sms) {
        Platform.runLater(() -> messages.add(new Message(phone, sms)));
    }
}
