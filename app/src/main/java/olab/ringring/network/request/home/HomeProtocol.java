package olab.ringring.network.request.home;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import olab.ringring.network.request.RequestBuilder;
import olab.ringring.network.response.home.SuccessSendChat;

/**
 * Created by 재화 on 2016-06-02.
 */
public class HomeProtocol {

    public static final Request maeSendChatMessageRequest(Context tag, SuccessSendChat content) {
        Map<String, String> bodyParameters = new HashMap<>();
        bodyParameters.put("receiverId", ""+content.getReceiverId());
        bodyParameters.put("messageContent", content.getMessage());
        bodyParameters.put("senderId", ""+content.getSenderId());
        bodyParameters.put("sendDate", ""+content.getSendDate());
        bodyParameters.put("readStatus", ""+content.getReadStatus());
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(HomeProtocolUrl.SEND_CHAT_MESSAGE.getUrl())
                .addPageSegment(HomeProtocolUrl.SEND_CHAT_MESSAGE.getPageSegment())
                .addBodyParameters(bodyParameters)
                .build();
    }

    public static final Request makeReceiveChatMessageRequest(Context tag, long recentTime){
        Map<String, String> bodyParameters = new HashMap<>();
        bodyParameters.put("sendDate", ""+recentTime);
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(HomeProtocolUrl.RECEIVE_CHAT_MESSAGE.getUrl())
                .addPageSegment(HomeProtocolUrl.RECEIVE_CHAT_MESSAGE.getPageSegment())
                .addBodyParameters(bodyParameters)
                .build();
    }
}
