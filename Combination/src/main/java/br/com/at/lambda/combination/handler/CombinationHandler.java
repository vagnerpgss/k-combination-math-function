package br.com.at.lambda.combination.handler;


import br.com.at.lambda.exception.BadRequestException;
import br.com.at.lambda.calc.Combination;
import br.com.at.lambda.combination.domain.CombinationRequest;
import br.com.at.lambda.combination.domain.CombinationResponse;
import br.com.at.lambda.model.ServerlessInput;
import br.com.at.lambda.model.ServerlessOutput;
import br.com.at.lambda.util.Util;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Handler for requests to Lambda function.
 */
public class CombinationHandler implements RequestHandler<ServerlessInput, ServerlessOutput> {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public ServerlessOutput handleRequest(ServerlessInput input, Context context) {
        ServerlessOutput output = new ServerlessOutput();
        try {
            output.setBody(MAPPER.writeValueAsString(calc(extractFromBody(input.getBody()))));
            output.setStatusCode(200);
        } catch (BadRequestException e) {
            output.setStatusCode(400);
            output.setBody(e.getMessage());
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            output.setStatusCode(500);
            output.setBody(sw.toString());
        }
        return output;
    }

    private CombinationRequest extractFromBody(String body) throws BadRequestException {
        CombinationRequest combinationRequest = null;
        try {
            combinationRequest = MAPPER.readValue(body, CombinationRequest.class);
        } catch (IOException e) {
            throw new BadRequestException("Body error");
        }
        return combinationRequest;
    }

    public CombinationResponse calc(CombinationRequest combinationRequest) {
        return getCombinationResponse(combinationRequest, combinationRequest.getNumbers().length);
    }

    private CombinationResponse getCombinationResponse(CombinationRequest combinationRequest, int length) {
        Combination combination = new Combination(Util.getMissingNumbers(combinationRequest.getNumbers()), combinationRequest.getTotal() - length);
        Integer[][] combinations = new Integer[Util.getInitializer(length)][];
        int i = 0;
        while (combination.hasNext()) {
            combinations[i] = Util.joinArrays(combinationRequest.getNumbers(), combination.next());
            i++;
        }
        return new CombinationResponse(combinations.length, combinations);
    }

}
