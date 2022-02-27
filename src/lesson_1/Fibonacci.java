package lesson_1;

import java.util.Arrays;

public class Fibonacci {

    private long[] fibArray;
    // уменьшение кэша??

    public Fibonacci() {
    }

    public long recursion(long value) {
        if (value <= 0) {
            return 0;
        } else if (value == 2) {
            return 1;
        } else {
            return recursion(value - 1) + recursion(value - 2);
        }
    }

    public void cycle(int capacityFibArray) {
        fibArray = new long[capacityFibArray];
        fibArray[0] = 0;
        fibArray[1] = 1;
        for (int i = 2; i < fibArray.length; i++) {
            long result = fibArray[i - 1] + fibArray[i - 2];
            fibArray[i] = result;
        }
        printFibArray();
    }

    private void printFibArray() {
        System.out.println(Arrays.toString(getFibArray()));
    }

    private long[] getFibArray() {
        return fibArray;
    }
}
