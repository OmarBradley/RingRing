package olab.ringring.network.request.home;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import olab.ringring.network.request.RequestBuilder;
import olab.ringring.network.response.chat.ChatContent;

/**
 * Created by 재화 on 2016-06-02.
 */
public class HomeProtocol {

    public static final Request maeChatContentRequest(Context tag, ChatContent content) {
        Map<String, String> bodyParameters = new HashMap<>();
        bodyParameters.put("receiverId", content.getReceiverId());
        bodyParameters.put("messageContent", content.getMessage());
        bodyParameters.put("senderId", content.getSenderId());
        bodyParameters.put("sendDate", ""+content.getSendDate());
        bodyParameters.put("readStatus", ""+content.getReadStatus());
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(HomeProtocolUrl.CHAT_CONTENT_URL.getUrl())
                .addPageSegment(HomeProtocolUrl.CHAT_CONTENT_URL.getPageSegment())
                .addBodyParameters(bodyParameters)
                .build();
    }





}
