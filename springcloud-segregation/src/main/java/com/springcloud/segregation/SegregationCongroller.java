package com.springcloud.segregation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName SegregationCongroller
 * @Description TODO
 * @Author boy
 * @Date 2020/1/13 10:22 PM
 */
@RestController
public class SegregationCongroller {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/segregation")
    public String helloSegregation() throws ExecutionException, InterruptedException {
//        RestTemplate restTemplate = new RestTemplate();

        String url1 = "http://HELLO-SEGREGATION/hellosegregation/";
        String s1 = restTemplate.getForEntity(url1,String.class).getBody();
//        String url2 = "http://HELLO-SERVICE/helloprovider/";
        String s2 = restTemplate.getForEntity(url1,String.class).getBody();
        String url = "http://HELLO-SEGREGATION/segregation/";
        String s = restTemplate.getForEntity(url,String.class).getBody();
        s1 = restTemplate.getForEntity(url,String.class).getBody();
        return s1;
//        CommandBase command = new CommandBase("HELLO-SEGREGATION",restTemplate,url);
//        Future<String> queue = command.queue();
//        return queue.get();
    }
}
