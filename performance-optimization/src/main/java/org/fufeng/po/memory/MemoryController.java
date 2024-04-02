package org.fufeng.po.memory;

import org.fufeng.po.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author fufeng
 * @Date 2024-03-31 20:51
 */
@RestController
@RequestMapping(value = "/memory")
public class MemoryController {

    private List<User> users = new ArrayList<User>();
    private List<Class<?>> classes = new ArrayList<Class<?>>();

    /**
     * 模拟堆内存溢出
     * -Xms32M -Xmx32M
     *
     * @return {@link String}
     */
    @GetMapping(value = "/heap")
    public String heap() {
        int i = 0;
        while (true) {
            User user = new User(i, "fufeng" + i);
            users.add(user);
            i++;
        }
        //return "0";
    }

    /**
     * 模拟非堆内存溢出
     * -XX:MaxMetaspaceSize=32M -XX:MetaspaceSize=32M
     *
     * @return {@link String}
     */
    @GetMapping(value = "/nonheap")
    public String nonheap() {
        while (true) {
            classes.addAll(Metaspace.createClasses());
        }
        //return "0";
    }
}
