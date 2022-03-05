package lesson_1_fibonacci;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

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

    public void streamBigInteger(int limit) {
        Stream.iterate(new BigInteger[]{BigInteger.ZERO, BigInteger.ONE}, t -> new BigInteger[]{t[1], t[0].add(t[1])})
                .map(t -> t[0])
                .limit(limit)
                .forEach(num -> System.out.print(num + ", "));
    }


    private void printFibArray() {
        System.out.println(Arrays.toString(getFibArray()));
    }

    private long[] getFibArray() {
        return fibArray;
    }
}
