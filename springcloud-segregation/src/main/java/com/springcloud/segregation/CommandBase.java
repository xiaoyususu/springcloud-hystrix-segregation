package com.springcloud.segregation;
import com.netflix.hystrix.*;
import org.springframework.web.client.RestTemplate;


/**
 * @ClassName CommandBase
 * @Description TODO
 * @Author boy
 * @Date 2020/1/12 2:05 PM
 */

public class CommandBase extends HystrixCommand<String> {

    private static final int MAX_POOL_SIZE = 50;
    private static final int KEEP_ALIVE_TIME_MINUTES = 5;
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2 + 1;

    private RestTemplate restTemplate;

    private String url;


    protected CommandBase(String groupName,RestTemplate restTemplate,String url) {
        super(Setter.withGroupKey(
                //服务分组
                HystrixCommandGroupKey.Factory.asKey(groupName))
                //线程分组
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(groupName))
                //线程池配置
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(CORE_POOL_SIZE)
                                .withKeepAliveTimeMinutes(KEEP_ALIVE_TIME_MINUTES)
                                .withMaxQueueSize(MAX_POOL_SIZE)
                                .withQueueSizeRejectionThreshold(10000))
                //线程池隔离
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
        );
        this.restTemplate = restTemplate;
        this.url = url;
    }


//    @Override
//    protected String run() throws Exception {
//        return restTemplate.getForEntity(url,String.class).getBody();
//    }

    @Override
    protected String getFallback() {
        return "error";
    }

    @Override
    protected String run() throws Exception {
        String s = null;
        try{
            s = restTemplate.getForEntity(url,String.class).getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }
}
