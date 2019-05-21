package com.ql;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class Test {

    public static void main(String[] args) {
//        String result = "{\"shape_info\":[702,967,3],\"rotate\":0,\"handle_time\":115.20814895629883,\"download_time\":41.23997688293457,\"code\":0,\"cls_score\":[0.00019421381875872612,0.00006167884566821158,0.028251558542251587,0.12834416329860687]}";
//        OcrResponse response = JSON.parseObject(result, OcrResponse.class);
//        System.out.println(response);
//        System.out.println(response.rotate);

        Integer i = null;
        if(i>0){

        }
    }

    @Getter
    @Setter
    @ToString
    private static class OcrResponse{
        /**分数*/
        List<Float> cls_score;
        /**方向*/
        Integer rotate;
    }
}
