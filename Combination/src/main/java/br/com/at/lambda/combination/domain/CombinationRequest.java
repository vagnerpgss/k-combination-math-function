package br.com.at.lambda.combination.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "total",
    "numbers"
})
public class CombinationRequest {

    @JsonProperty("p")
    private Integer p = null;

    @JsonProperty("n")
    private Integer[] n = null;

    @JsonProperty("p")
    public Integer getP() {
        return p;
    }

    @JsonProperty("p")
    public void setP(Integer p) {
        this.p = p;
    }

    @JsonProperty("n")
    public Integer[] getN() {
        return n;
    }

    @JsonProperty("n")
    public void setN(Integer[] n) {
        this.n = n;
    }

}
