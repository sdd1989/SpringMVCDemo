package com.ql;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/test/")
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("test1")
    public String test1(@RequestParam String id , @RequestParam String name){

        logger.info("==id:{},name:{}",id,name);
        return "ok";
    }

    @RequestMapping("test2")
    public String test2(@RequestBody TestVO vo){
        logger.info("==id:{},name:{}",vo.getId(),vo.getName());

        return "ok";
    }

    @RequestMapping("test3")
    public String test3(ServletRequest request){
        Map map = request.getParameterMap();
        logger.info("paramMap:{}", JSON.toJSONString(map));

        return "ok";
    }

    @RequestMapping("test4")
    public String test4(ServletRequest request){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        logger.info("==id:{},name:{}",id,name);

        return "ok";
    }
    @RequestMapping("test5")
    public String test5(ServletRequest request) throws IOException {
        String body = IOUtils.toString(request.getReader());
        logger.info("==body:{}",body);

        return "ok";
    }

    @RequestMapping("test6")
    public String test6(ServletRequest request){
        String id = request.getParameter("id");
        String[] names = request.getParameterValues("names");
        logger.info("==id:{},names:{}",id,names);
        return "ok";
    }

    @RequestMapping("test7")
    public String test7(ServletRequest request){
        String name = request.getParameter("邱亮");
        logger.info("==name:{}",name);
        return "ok";
    }
}
