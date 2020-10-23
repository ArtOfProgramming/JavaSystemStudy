package com.daiwei.testmystarter;

import com.daiwei.mytest.autoconfigure.MytestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestMystarterController {

    @Autowired
    private MytestService mytestService;

    @RequestMapping("/mystartername/{name}")
    public String getName(@PathVariable("name") String name) {
        return mytestService.getName(name);
    }
}
