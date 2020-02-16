package com.springcloud.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProviderController
 * @Description TODO
 * @Author boy
 * @Date 2019/12/28 6:26 PM
 */
@RestController
public class ProviderController {

    @RequestMapping(value = "/segregation", method= RequestMethod.GET)
    public String hello() throws Exception{
        return "hello-segregation";
    }

    @RequestMapping("/hellosegregation")
    public String helloSegregation() throws Exception{
        return "hello-segregation-test";
    }
}
