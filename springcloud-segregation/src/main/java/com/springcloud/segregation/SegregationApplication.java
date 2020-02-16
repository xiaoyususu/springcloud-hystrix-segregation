package com.springcloud.segregation;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName SegregationApplication
 * @Description TODO
 * @Author boy
 * @Date 2020/1/12 2:00 PM
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker//允许断路器
public class SegregationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SegregationApplication.class, args);
    }

    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }

    @Bean
    @LoadBalanced
//    @Scope("prototype")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
