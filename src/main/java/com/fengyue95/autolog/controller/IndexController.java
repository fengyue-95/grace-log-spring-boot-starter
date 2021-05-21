package com.fengyue95.autolog.controller;

import com.fengyue95.autolog.annotation.AutoLog;
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
    @AutoLog
    public String index(@RequestParam("str1") String str,@RequestParam("str2") String str2){
        int i=1/0;
        return str+str2+"hahahh";
    }
}
