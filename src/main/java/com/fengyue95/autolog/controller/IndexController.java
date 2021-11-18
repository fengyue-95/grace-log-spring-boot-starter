package com.fengyue95.autolog.controller;

import java.util.concurrent.TimeUnit;

import com.fengyue95.autolog.context.ApplicationUserContext;
import com.fengyue95.autolog.dto.Person;
import com.fengyue95.autolog.methodParamLog.annotation.MethodParamLog;
import com.fengyue95.autolog.placeholderLog.annotation.PlaceholderLog;
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

    @GetMapping("index")
    @MethodParamLog
    public String index(@RequestParam("str1") String str,
                        @RequestParam("str2") String str2) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2L);
        return str + str2 + "hahahh";
    }

    @PostMapping("person")
    @MethodParamLog
    // @PlaceholderLog(content = "我#{#person.name + '的年龄是:' + #person.age}", currentUser = ApplicationUserContext.class)
    // @PlaceholderLog(content = "当前用户:#{#applicationUserContext.username}", currentUser = ApplicationUserContext.class)
    @PlaceholderLog(content = "当前用户:#{#applicationUserContext.username+ '我是：'+ #person.name + '的年龄是:' + #person.age}",
        currentUser = ApplicationUserContext.class)
    public String person(@RequestBody Person person) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2L);
        return person.getName() + person.getAge() + "hahahh";
    }
}
