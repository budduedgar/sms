package org.jpereda.attach.sms;

/**
 * sms data transfer 
 * @author edgar
 */
public class Message {

    private String phone;
    private String sms;
    
    /**
     * sms message constructor
     * @param phone contact of the sender
     * @param sms messsage body contents
     */
    public Message(String phone, String sms) {
        this.phone = phone;
        this.sms = sms;
    }
    /**
     * get the phone 
     * @return sms phone 
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * get the sms 
     * @return sms body
     */
    public String getSms() {
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
