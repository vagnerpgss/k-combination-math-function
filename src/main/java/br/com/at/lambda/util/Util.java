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

    public static Integer[] getNumerosAusentes(Integer[] bet) {
        int total = 25 - bet.length;
        Integer[] result = new Integer[total];
        int index = 0;
        for (int i = 1; i <= 25; i++) {
            if (!contais(bet, i)) {
                result[index] = i;
                index++;
            } else if (index == total) {
                break;
            }
        }
        return result;
    }

    private static boolean contais(Integer[] bet, int i) {
        for (int j = 0; j < bet.length; j++) {
            if (i == bet[j])
                return true;
        }
        return false;
    }

    public static Integer[] joinArrays(Integer[] f, Integer[] s) {

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

    public static int getInitializer(int size) {
        switch (size) {
            case 19:
                return 3876;
            case 18:
                return 816;
            case 17:
                return 136;
            case 16:
                return 16;
            case 15:
                return 1;
            case 14:
                return 11;
            case 13:
                return 66;
            case 12:
                return 286;
            case 11:
                return 1001;
            case 10:
                return 3003;
            default:
                return 0;
        }
    }

    //    private static Integer[] joinArrays(Integer[]... arrays) {
//        int length = 0;
//        for (Integer[] array : arrays) {
//            length += array.length;
//        }
//
//        final Integer[] result = (Integer[]) Array.newInstance(arrays[0].getClass().getComponentType(), length);
//
//        int offset = 0;
//        for (Integer[] array : arrays) {
//            System.arraycopy(array, 0, result, offset, array.length);
//            offset += array.length;
//        }
//
//        return result;
//    }

}
