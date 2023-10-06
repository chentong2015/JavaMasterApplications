package main.service;

import main.model.Message;
import main.model.MyRequest;
import main.model.MyResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class HttpClientManager {

    // Access Token的适使用有效期是30天
    private static final String ACCESS_TOKEN = "24.5a5ba017198866af52f204b7b2c1e197.xxx";
    private static final String URL = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions?access_token=" + ACCESS_TOKEN;

    private static final String APPLICATION_JSON = "application/json";
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    // 使用try-with-resources格式保证client和response都能够被关闭
    public static String sendPostRequest(String content) throws IOException {
        MyRequest myRequest = new MyRequest(List.of(new Message("user", content)));
        RequestBody requestBody = RequestBody.create(JsonHandler.convertRequestToJson(myRequest),
                MediaType.parse(APPLICATION_JSON));
        Request request = new Request.Builder()
                .url(URL)
                .method("POST", requestBody)
                .addHeader("Content-Type", APPLICATION_JSON)
                .addHeader("Accept", APPLICATION_JSON)
                .build();

        Response response = HTTP_CLIENT.newCall(request).execute();
        assert response.body() != null;
        String responseBody = response.body().string();
        MyResponse myResponse = JsonHandler.convertJsonToResponse(responseBody);
        return myResponse.getResult();
    }
}
