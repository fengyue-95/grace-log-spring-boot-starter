package com.fengyue95.autolog.controller;

import java.util.concurrent.TimeUnit;

import com.fengyue95.autolog.methodParamLog.annotation.MethodParamLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengyue
 * @date 2021/5/21
 */
@RestController
public class IndexController {


    @GetMapping("index")
    @MethodParamLog
    public String index(@RequestParam("str1") String str,@RequestParam("str2") String str2)
        throws InterruptedException {
        TimeUnit.SECONDS.sleep(2L);
        return str+str2+"hahahh";
    }
}
