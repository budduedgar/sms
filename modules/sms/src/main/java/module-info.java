/**
 * gluon-javafx attach service for handling the sms.
 */
module org.jpereda.attach.sms {

    requires javafx.graphics;
    requires com.gluonhq.attach.util;
    
    exports org.jpereda.attach.sms;
    exports org.jpereda.attach.sms.impl to com.gluonhq.attach.util;
}