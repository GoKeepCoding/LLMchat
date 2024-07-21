/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jnu.myllm.controller;


import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.jnu.myllm.annotation.JwtAuth;
import com.jnu.myllm.common.ServerResponseEntity;
import com.jnu.myllm.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;
import static com.jnu.myllm.constant.ResponseCode.SUCCESS;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;


@RestController
public class BasicController {

    @GetMapping("/hello/{say}")
    @JwtAuth
    public ServerResponseEntity<ResponseVO> hello(@PathVariable String say) {
        try{
            Generation gen = new Generation();

            QwenParam params = QwenParam.builder().model("qwen-turbo")
                    .apiKey("sk-0181a7a1e6be46c7afe9c04554d83e8e")
                    .prompt(say)
                    .seed(1234)
                    .topP(0.8)
                    .resultFormat("message")
                    .enableSearch(false)
                    .maxTokens(1500)
                    .temperature((float)0.85)
                    .repetitionPenalty((float)1.0)
                    .build();

            GenerationResult result = gen.call(params);




            ResponseVO responseVO = new ResponseVO();
            responseVO.setContent(result.getOutput().getChoices().get(0).getMessage().getContent());
            responseVO.setTokens(result.getUsage().getTotalTokens());
            return ServerResponseEntity.success(SUCCESS.getCode(),responseVO);

        }catch(ApiException | NoApiKeyException | InputRequiredException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
