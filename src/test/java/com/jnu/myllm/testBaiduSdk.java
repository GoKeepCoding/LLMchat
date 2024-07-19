package com.jnu.myllm;


import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.core.auth.Auth;
import com.baidubce.qianfan.model.chat.ChatResponse;

public class testBaiduSdk {
    public static void main(String[] args) {
        // 使用安全认证AK/SK鉴权，替换下列示例中参数，安全认证Access Key替换your_iam_ak，Secret Key替换your_iam_sk
        Qianfan qianfan = new Qianfan(Auth.TYPE_OAUTH,"a4SbKrp9rMTp3cDCJvcJsF4F", "upgN1JUFcFNEo6NJRp91MQts4dlWOf9S");

        // 指定模型
        ChatResponse resp = qianfan.chatCompletion()
                .model("ERNIE-4.0-8K")
                .addMessage("user", "你好")
                .execute();
        System.out.println(resp.getResult());
    }
}