package com.wz.springcloud.servcie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestService {

    @Autowired
    RestTemplate restTemplate;

    // 用restTemplate的形式去消费注册到nacos的服务
    public String getTestMessage() {
        Map<String, String> parm = new HashMap<>(1);
        parm.put("name", "test");
        // 这里的返回类型必须与接口返回类型一致
        return restTemplate.getForObject("http://alibaba-nacos-discovery-server/hello?name={0}", String.class,"test");
    }
}
