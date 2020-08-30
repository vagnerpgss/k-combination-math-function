package br.com.at.lambda.test;

import br.com.at.lambda.combination.handler.CombinationHandler;
import br.com.at.lambda.model.ServerlessInput;
import br.com.at.lambda.model.ServerlessOutput;

import java.util.HashMap;
import java.util.Map;

public class MainTest {

    public static void main(String[] args) {
        CombinationHandler combinationHandler = new CombinationHandler();
        ServerlessOutput output = combinationHandler.handleRequest(buildRequest(), null);
        System.out.println(output.getStatusCode());
        System.out.println(output.getBody());
    }

    private static ServerlessInput buildRequest() {
        ServerlessInput serverlessInput = new ServerlessInput();
//        serverlessInput.setBody("{\"p\": \"2\", \"n\": [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]}");
        Map<String, String> q = new HashMap<>();
        q.put("p", "6");
        serverlessInput.setQueryStringParameters(q);
        serverlessInput.setBody("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]");
        serverlessInput.setHttpMethod("POST");
        return serverlessInput;
    }

}