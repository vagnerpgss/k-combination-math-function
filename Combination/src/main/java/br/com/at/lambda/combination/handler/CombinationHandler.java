package br.com.at.lambda.combination.handler;


import br.com.at.lambda.exception.BadRequestException;
import br.com.at.lambda.calc.Combination;
import br.com.at.lambda.combination.domain.CombinationRequest;
import br.com.at.lambda.combination.domain.CombinationResponse;
import br.com.at.lambda.model.ServerlessInput;
import br.com.at.lambda.model.ServerlessOutput;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handler for requests to Lambda function.
 */
public class CombinationHandler implements RequestHandler<ServerlessInput, ServerlessOutput> {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public ServerlessOutput handleRequest(ServerlessInput input, Context context) {
        Instant start = Instant.now();
        ServerlessOutput output = new ServerlessOutput();
        try {
            CombinationRequest combinationRequest = extractFromBody(input.getBody());
            validate(combinationRequest);
            output.setBody(MAPPER.writeValueAsString(calc(combinationRequest)));
            output.setStatusCode(200);
        } catch (BadRequestException e) {
            output.setStatusCode(400);
            output.setBody(e.getMessage());
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            output.setStatusCode(500);
            output.setBody(sw.toString());
        }
        Instant end = Instant.now();
        System.out.println(" ### handleRequest " + Duration.between(start, end).toMillis());
        return output;
    }

    private void validate(CombinationRequest combinationRequest) throws BadRequestException {
        if (combinationRequest.getP() > combinationRequest.getN().length) {
            throw new BadRequestException("P must be a natural number less than or equal to the total number in N");
        }
    }

    private CombinationRequest extractFromBody(String body) throws BadRequestException {
        Instant start = Instant.now();
        CombinationRequest combinationRequest = null;
        try {
            combinationRequest = MAPPER.readValue(body, CombinationRequest.class);
        } catch (IOException e) {
            throw new BadRequestException("Body error");
        }
        Instant end = Instant.now();
        System.out.println(" ### extractFromBody " + Duration.between(start, end).toMillis());
        return combinationRequest;
    }

    public CombinationResponse calc(CombinationRequest combinationRequest) {
        Instant start = Instant.now();
        Instant start1 = Instant.now();
        Combination combination = new Combination(combinationRequest.getN(), combinationRequest.getP());
        List<List<Integer>> combinations = new ArrayList<>();
        while (combination.hasNext()) {
            combinations.add(Arrays.asList(combination.next()));
        }
        Instant end1 = Instant.now();
        System.out.println(" ### calc comb " + Duration.between(start1, end1).toMillis());
        Instant start2 = Instant.now();
        CombinationResponse combinationResponse = new CombinationResponse(combinations.size(), combinations.stream()
                .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new));
        Instant end2 = Instant.now();
        System.out.println(" ### calc stream " + Duration.between(start2, end2).toMillis());
        Instant end = Instant.now();
        System.out.println(" ### calc total " + Duration.between(start, end).toMillis());
        return combinationResponse;
    }
}