package br.com.at.lambda.lotofacil.combination.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "apostas"
})
public class CombinationResponse {

    @JsonProperty("total_bets")
    private int total = 0;

    @JsonProperty("total_bets")
    public int getTotal() {
        return total;
    }

    @JsonProperty("total_bets")
    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("bets")
    private Integer[][] bets = null;

    @JsonProperty("bets")
    public Integer[][] getBets() {
        return bets;
    }

    @JsonProperty("bets")
    public void setBets(Integer[][] bets) {
        this.bets = bets;
    }

    public CombinationResponse(int total, Integer[][] bets) {
        this.total = total;
        this.bets = bets;
    }
}
