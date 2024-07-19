package com.jnu.myllm.controller;

import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesis;
import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisParam;
import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dashscope.exception.NoApiKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ImageController {
    @RequestMapping("/image")
    public List<String> image(@RequestParam  String say) throws NoApiKeyException {
        ImageSynthesis is = new ImageSynthesis();
        ImageSynthesisParam param =
                ImageSynthesisParam.builder()
                        .apiKey("sk-0181a7a1e6be46c7afe9c04554d83e8e")
                        .model("wanx-v1")
                        .prompt(say)
                        .n(1)
                        .seed(1)
                        .size("1024*1024")
                        .style("<auto>")
                        .build();

        ImageSynthesisResult result = is.call(param);

        List<String> list = new ArrayList<>();
        for(int i = 0;i < result.getOutput().getResults().size();i++){
            list.add(result.getOutput().getResults().get(i).get("url"));
        }

        return list;
    }
}
