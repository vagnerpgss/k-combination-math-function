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

    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("numbers")
    private Integer[] numbers = null;

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    @JsonProperty("numbers")
    public Integer[] getNumbers() {
        return numbers;
    }

    @JsonProperty("numbers")
    public void setNumbers(Integer[] numbers) {
        this.numbers = numbers;
    }

}
