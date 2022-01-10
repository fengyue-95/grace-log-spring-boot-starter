package com.fengyue95.autolog.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;

import com.fengyue95.autolog.context.ApplicationUserContext;
import com.fengyue95.autolog.dto.People;
import com.fengyue95.autolog.dto.Person;
import com.fengyue95.autolog.dto.TestParam;
import com.fengyue95.autolog.dto.User;
import com.fengyue95.autolog.methodParamLog.annotation.MethodParamLog;
import com.fengyue95.autolog.placeholderLog.annotation.PlaceholderLog;
import com.fengyue95.autolog.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengyue
 * @date 2021/5/21
 */
@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping("index")
    @MethodParamLog
    public String index(@RequestParam("str1") String str, @RequestParam("str2") String str2)
        throws InterruptedException {
        TimeUnit.SECONDS.sleep(2L);
        return str + str2 + "hahahh";
    }

    @PostMapping("person")
    @MethodParamLog
    // @PlaceholderLog(content = "我#{#person.name + '的年龄是:' + #person.age}", currentUser = ApplicationUserContext.class)
    // @PlaceholderLog(content = "当前用户:#{#applicationUserContext.username}", currentUser = ApplicationUserContext.class)
    public String person(@RequestBody TestParam testParam)
        throws InterruptedException {
        TimeUnit.SECONDS.sleep(2L);
        return indexService.person(testParam.getPerson(),testParam.getUser(),testParam.getPeople(),"hahah");
    }
}
