package pw.pref.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.authentication.api.client.RestServiceClient;
import pw.pref.api.helper.ApiContext;
import pw.pref.constants.ApiConstants;

@Service
public class ChatService {
    private RestServiceClient restServiceClient;

    @Autowired
    public ChatService(RestServiceClient restServiceClient) {
        this.restServiceClient = restServiceClient;
    }

    public void notifyChatOfNewValue(ApiContext apiContext) {
        restServiceClient
                .callRestApi(ApiConstants.CHATSERVER_URL, ApiConstants.NOTIFY_PREFERENCE_CHANGE_CONTEXT,
                        ApiConstants.USER_ID_PARAMETER,
                        apiContext.getUserId(),
                        ApiConstants.KEY_PARAMETER, apiContext.getKey(), ApiConstants.VALUE_PARAMETER,
                        apiContext.getValue());
    }


}
