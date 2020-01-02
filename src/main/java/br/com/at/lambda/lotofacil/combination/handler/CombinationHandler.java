package br.com.at.lambda.lotofacil.combination.handler;


import br.com.at.lambda.exception.BadRequestException;
import br.com.at.lambda.calc.Combinacao;
import br.com.at.lambda.lotofacil.combination.domain.CombinationRequest;
import br.com.at.lambda.lotofacil.combination.domain.CombinationResponse;
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
            if (combinationRequest.getNumbers().length < 11 || combinationRequest.getNumbers().length > 18) {
                throw new BadRequestException("Quantidade de números não permitida para geração de apostas");
            }
        } catch (IOException e) {
            throw new BadRequestException("Body error");
        }
        return combinationRequest;
    }

    public CombinationResponse calc(CombinationRequest combinationRequest) {
        int length = combinationRequest.getNumbers().length;
        if (length >= 15) {
            return getDesdobramentoResponse(combinationRequest, length);
        } else {
            return getFechamentoResponse(combinationRequest, length);
        }
    }

    private CombinationResponse getFechamentoResponse(CombinationRequest combinationRequest, int length) {
        Combinacao combinacao = new Combinacao(Util.getNumerosAusentes(combinationRequest.getNumbers()), 15 - length);
        Integer[][] bets = new Integer[Util.getInitializer(length)][];
        int i = 0;
        while (combinacao.hasNext()) {
            bets[i] = Util.joinArrays(combinationRequest.getNumbers(), combinacao.next());
            i++;
        }
        return new CombinationResponse(bets.length, bets);
    }

    private CombinationResponse getDesdobramentoResponse(CombinationRequest combinationRequest, int length) {
        Combinacao combinacao = new Combinacao(combinationRequest.getNumbers(), 15);
        Integer[][] bets = new Integer[Util.getInitializer(length)][];
        int i = 0;
        while (combinacao.hasNext()) {
            bets[i] = combinacao.next();
            i++;
        }
        return new CombinationResponse(bets.length, bets);
    }

}
