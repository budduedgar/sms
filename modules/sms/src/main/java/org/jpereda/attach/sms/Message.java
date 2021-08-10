package org.jpereda.attach.sms;

import java.time.ZonedDateTime;

public class Message {

    private final String phone;
    private final String sms;

    public Message(String phone, String sms) {
        this.phone = phone;
        this.sms = sms;
    }

    public final String getPhone() {
        return phone;
    }

    public final String getSms() {
        return sms;
    }

    @Override
    public String toString() {
        return "Message{"
                + "phone='" + phone + '\''
                + ", sms='" + sms
                + '}';
    }

}
