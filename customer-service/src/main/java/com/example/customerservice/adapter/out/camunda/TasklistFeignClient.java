package com.example.customerservice.adapter.out.camunda;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(
        name = "tasklistClient",
        url = "http://tasklist:8093/graphql"  // Tasklist local
)
public interface TasklistFeignClient {

    @PostMapping(consumes = "application/json")
    Map<String, Object> callTasklist(@RequestBody Map<String, Object> request);
}
