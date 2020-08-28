package br.com.at.lambda.combination.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "combinations"
})
public class CombinationResponse {

    @JsonProperty("total_combinations")
    private int total = 0;

    @JsonProperty("total_combinations")
    public int getTotal() {
        return total;
    }

    @JsonProperty("total_combinations")
    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("combinations")
    private int[][] combinations = null;

    @JsonProperty("combinations")
    public int[][] getCombinations() {
        return combinations;
    }

    @JsonProperty("combinations")
    public void setCombinations(int[][] combinations) {
        this.combinations = combinations;
    }

    public CombinationResponse(int total, int[][] combinations) {
        this.total = total;
        this.combinations = combinations;
    }
}
