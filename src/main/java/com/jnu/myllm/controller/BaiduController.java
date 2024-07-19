package com.jnu.myllm.controller;

import com.baidubce.qianfan.core.auth.Auth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.model.chat.ChatResponse;

@RestController
public class BaiduController {

    @GetMapping("/baidu")
    public String send(@RequestParam String say){
        Qianfan qianfan = new Qianfan(Auth.TYPE_OAUTH,"a4SbKrp9rMTp3cDCJvcJsF4F", "upgN1JUFcFNEo6NJRp91MQts4dlWOf9S");

        // 指定模型
        ChatResponse resp = qianfan.chatCompletion()
                .model("ERNIE-4.0-8K")
                .addMessage("user", say)
                .execute();
        return resp.getResult();
    }
}
