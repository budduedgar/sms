package org.jpereda.attach.sms;

import com.gluonhq.attach.util.Services;
import javafx.collections.ObservableList;
import java.util.Optional;
/**
 * SMS - Service Interface
 * @author edgar
 */
public interface SmsService {

    /**
     * Returns an instance of {@link SmsService}.
     * @return An instance of {@link SmsService}.
     */
    static Optional<SmsService> create() {
        return Services.get(SmsService.class);
    }
    
    /**
     * message from the sms-center
     * @return list of the intercepted sms
     */
    ObservableList<Message> getMessages();
}
