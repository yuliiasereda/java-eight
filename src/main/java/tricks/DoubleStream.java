package tricks;

import java.util.Random;
import java.util.stream.LongStream;

public class DoubleStream {

  public static void main(String[] args) {
    long numbers[] = getLongs();
    System.out.println(getAverageWithSimpleStreamAPI(numbers));

    //Next two: the most accuracy result
    System.out.println(getAverageWithKahanSummation(numbers));;
    System.out.println(getAverageWithKahanSummationInParallelMode(numbers));

    System.out.println(getAverageInStandardWay(numbers));
  }

  private static long[] getLongs() {
    return new Random(55).longs(1000).toArray();
  }

  /**
   * FIRST: long summation SECOND: calculating average (can be values -> Long.MAX_VALUE or
   * Long.MIN_VALUE) value overflow THIRD: get double value
   */
  private static double getAverageWithSimpleStreamAPI(long[] numbers) {
    return LongStream.of(numbers).average().getAsDouble();
  }

  /**
   * Different order of long summation
   */
  private static double getAverageWithKahanSummationInParallelMode(long[] numbers) {
    return LongStream.of(numbers).asDoubleStream().parallel().average().getAsDouble();
  }

  /**
   * Kahan summation algorithm, Significantly reduces the numerical error in the total obtained
   */
  private static double getAverageWithKahanSummation(long[] numbers) {
    return LongStream.of(numbers).asDoubleStream().average().getAsDouble();
  }

  /**
   * Numerical error appears during calculation
   */
  private static double getAverageInStandardWay(long[] numbers) {
    double result = 0.0;
    for (long number : numbers
    ) {
      result += number;
    }
    result /= numbers.length;
    return result;
  }
}
