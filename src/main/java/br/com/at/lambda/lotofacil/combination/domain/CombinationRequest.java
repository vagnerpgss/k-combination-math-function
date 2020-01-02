package br.com.at.lambda.lotofacil.combination.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "numbers"
})
public class CombinationRequest {

    @JsonProperty("numbers")
    private Integer[] numbers = null;

    @JsonProperty("numbers")
    public Integer[] getNumbers() {
        return numbers;
    }

    @JsonProperty("numbers")
    public void setNumbers(Integer[] numbers) {
        this.numbers = numbers;
    }

}
