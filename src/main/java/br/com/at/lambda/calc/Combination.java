package br.com.at.lambda.calc;

import br.com.at.lambda.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {

    private int r;
    private Integer[] input;
    private int MAX;
    private int N;

    /**
     * if r is zero then all combinations will be done (with any number of elements).
     */
    public Combination(Integer[] input, int r) {
        this.r = r;
        this.input = input;
        this.MAX = ~(1 << input.length);
        this.N = 1;
    }

    /**
     * return true when there is at least one combination available.
     */
    public boolean hasNext() {
        if (r != 0) {
            while (((this.N & this.MAX) != 0) && (countbits() != r))
                N += 1;
        }
        return (this.N & this.MAX) != 0;
    }

    /**
     * return the total amount of bits for N.
     */
    private int countbits() {
        int i;
        int c;

        i = 1;
        c = 0;
        while ((this.MAX & i) != 0) {
            if ((this.N & i) != 0) {
                c++;
            }
            i = i << 1;
        }

        return c;
    }

    /**
     * useful to obtain the size of the input.
     * this size is the number of positions of the vector returned by next.
     */
    public int getOutputLength() {
        if (r != 0) {
            return r;
        }
        return this.countbits();
    }

    /**
     * return one combination
     * <p>
     * ATTENTION: Always use next() when you are sure that a combination is available.
     * That is, always use next() when hasNext() returns true.
     */
    public Integer[] next() {
        int output_index, input_index, i;

        Integer[] output = new Integer[this.getOutputLength()];

        input_index = 0;
        output_index = 0;
        i = 1;

        while ((this.MAX & i) != 0) {
            if ((this.N & i) != 0) {
                output[output_index] = input[input_index];
                output_index += 1;
            }
            input_index += 1;
            i = i << 1;
        }

        N += 1;

        return output;
    }

}