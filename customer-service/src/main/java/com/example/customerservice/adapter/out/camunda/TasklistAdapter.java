package com.example.customerservice.adapter.out.camunda;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TasklistAdapter {

    private final TasklistFeignClient feignClient;
    public String findUserTaskId(String taskName) {
        String query = """
            query {
              userTasks(query: {state: CREATED, name: "%s"}) {
                id
              }
            }
            """.formatted(taskName);

        Map<String, Object> response = feignClient.callTasklist(Map.of("query", query));
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        List<Map<String, Object>> tasks = (List<Map<String, Object>>) data.get("userTasks");

        return tasks.isEmpty() ? null : (String) tasks.get(0).get("id");
    }

    public void completeUserTask(String taskId, Map<String, Object> variables) {
        String mutation = """
            mutation($taskId: String!, $vars: JSON!) {
              completeUserTask(taskId: $taskId, variables: $vars) {
                id
              }
            }
            """;

        feignClient.callTasklist(Map.of(
                "query", mutation,
                "variables", Map.of("taskId", taskId, "vars", variables)
        ));
    }
}
