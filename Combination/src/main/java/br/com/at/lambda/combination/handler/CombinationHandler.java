package br.com.at.lambda.combination.handler;

import br.com.at.lambda.calc.Combination;
import br.com.at.lambda.model.ServerlessInput;
import br.com.at.lambda.model.ServerlessOutput;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Handler for requests to Lambda function.
 */
public class CombinationHandler implements RequestHandler<ServerlessInput, ServerlessOutput> {

    @Override
    public ServerlessOutput handleRequest(ServerlessInput input, Context context) {
        ServerlessOutput output = new ServerlessOutput();
        try {
            int p = Integer.parseInt(input.getQueryStringParameters().get("p"));
            Integer[] n = JsonIterator.deserialize(input.getBody(), Integer[].class);
            output.setBody(calc(p, n));
            output.setStatusCode(200);
        } catch (Exception e) {
            e.printStackTrace();
            output.setStatusCode(500);
            output.setBody(e.getMessage());
        }
        return output;
    }

    public String calc(int p, Integer[] n) {
        Combination combination = new Combination(n, p);
        List<List<Integer>> combinations = new ArrayList<>();
        while (combination.hasNext()) {
            combinations.add(Arrays.asList(combination.next()));
        }
        return JsonStream.serialize(combinations);
    }
}