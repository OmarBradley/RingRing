package olab.ringring.network.response.users;

import lombok.Getter;

/**
 * Created by 재화 on 2016-06-03.
 */
public enum ValidateMessage {
    OK("OK"), NO("NO");

    ValidateMessage(String messageType){
        this.messageType = messageType;
    }

    @Getter private String messageType;
}
