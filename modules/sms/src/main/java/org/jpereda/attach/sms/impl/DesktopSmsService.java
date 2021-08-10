package org.jpereda.attach.sms.impl;

import java.util.logging.Logger;
import javafx.collections.ObservableList;
import org.jpereda.attach.sms.Message;
import org.jpereda.attach.sms.SmsService;

public class DesktopSmsService implements SmsService {

    private static final Logger LOG = Logger.getLogger(DesktopSmsService.class.getName());

    static void receiveSms(String phone, String sms) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Message> getMessages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
