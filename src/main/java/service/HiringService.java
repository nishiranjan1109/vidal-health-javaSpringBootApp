package service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dto.queryRequest;
import dto.webHookResponse;
import dto.webhookRequest;

@Service
public class HiringService {
	
private final RestTemplate restTemplate = new RestTemplate();

private static final String GENERATE_URL =
    "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

private static final String REG_NO = "250850120114"; 

public void executeHiringTask() {

    
    webhookRequest request = new webhookRequest(
            "Nishi",
            REG_NO,
            "nishiranjan1109@gmail.com"
    );

    ResponseEntity<webHookResponse> response =
            restTemplate.postForEntity(
                    GENERATE_URL,
                    request,
                    webHookResponse.class
            );

    webHookResponse data = response.getBody();

    if (data == null) {
        throw new RuntimeException("Webhook respnse is null");
    }


    String finalQuery = "select e.name, d.department_name " +
            "from employee e join department d on e.dept_id = d.id";

    
    submitFinalQuery(
            data.getWebhook(),
            data.getAccessToken(),
            finalQuery
    );
}



private void submitFinalQuery(String webhookUrl,
                              String token,
                              String finalQuery) {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", token);

   queryRequest body = new queryRequest(finalQuery);

    HttpEntity<queryRequest> entity =
            new HttpEntity<>(body, headers);

    restTemplate.postForEntity(
            webhookUrl,
            entity,
            String.class
    );
}
}
