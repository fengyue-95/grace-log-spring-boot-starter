package com.fengyue95.autolog.service;

import com.fengyue95.autolog.context.ApplicationUserContext;
import com.fengyue95.autolog.dto.People;
import com.fengyue95.autolog.dto.Person;
import com.fengyue95.autolog.dto.User;
import com.fengyue95.autolog.placeholderLog.annotation.PlaceholderLog;
import org.springframework.stereotype.Service;

/**
 * @author fengyue
 * @date 2022/1/10
 */
@Service
public class IndexService {

    @PlaceholderLog(
        content = "当前用户:#{#applicationUserContext.username+ '我是p0：'+ #p0['name'] + '的年龄是:' + #p0['age']   "
            + "+  '我是p1：'+ #p1['name'] + '的年龄是:' + #p1['age'] +'地址是：'+#p1['address']+'我是p2：'+ #p2['name'] + '的年龄是:' + #p2['age'] + '字符串测试'+#p3}",
        currentUser = ApplicationUserContext.class)
    public String person(Person person, User user, People people, String str) {
        return "1";
    }
}
