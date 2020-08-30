package br.com.at.lambda.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Util {

    /**
     * Coloca a lista em ordem crescente.
     * Facilita a compara��o tornando-a ainda mais r�pida.
     *
     * @param tickets
     */
    public static void orderByAsc(List<Integer> tickets) {
        Collections.sort(tickets, new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                Integer num1 = (Integer) o1;
                Integer num2 = (Integer) o2;
                if (num1 < num2) {
                    return -1;
                }
                if (num1 > num2) {
                    return 1;
                }
                return 0;
            }
        });
    }

    public static Integer[] getMissingNumbers(Integer[] numbers) {
        int total = 25 - numbers.length;
        Integer[] result = new Integer[total];
        int index = 0;
        for (int i = 1; i <= 25; i++) {
            if (!contais(numbers, i)) {
                result[index] = i;
                index++;
            } else if (index == total) {
                break;
            }
        }
        return result;
    }

    private static boolean contais(Integer[] numbers, int i) {
        for (int j = 0; j < numbers.length; j++) {
            if (i == numbers[j])
                return true;
        }
        return false;
    }

    public static Integer[] joinArrays(Integer[] f, Integer[] s) {
        if (f == null) {
            return s;
        }
        int idx = 0;
        final Integer[] result = new Integer[f.length + s.length];
        for (Integer i : f) {
            result[idx] = i;
            idx++;
        }
        for (Integer i : s) {
            result[idx] = i;
            idx++;
        }
        return result;
    }
}