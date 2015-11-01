// Jordan Cazamias
// PerformanceTest.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The MyHashMap class is ready to be used in any Java code.
 * In lieu of an interactive script, running this program will
 * run a performance test to gather statistics on the program's running time.
 */
public class PerformanceTest {

    public static void main(String args[]) {

        final int SIZE = 1000000;
        final int NS_PER_S = 1000000000;
        Random rand = new Random();

        MyHashMap map = new MyHashMap(SIZE);
        List<Long> times = new ArrayList<Long>(SIZE);
        long totalNS = 0;

        for(int i = 0; i < SIZE; i++) {
            int r = rand.nextInt();
            long start = System.nanoTime();
            map.set(Integer.toString(r), "Entry " + i + ", key " + Integer.toString(r));
            long end = System.nanoTime();
            long time = end - start;

            times.add(time);
            totalNS += time;
        }

        // Compute time statistics
        Collections.sort(times);
        double totalTime = (double) totalNS / NS_PER_S;
        double avgTime = (double) (totalNS / SIZE) / NS_PER_S;
        double shortestTime = (double) times.get(0) / NS_PER_S;
        double q1Time = (double) times.get(times.size() / 4) / NS_PER_S;
        double medianTime = (double) times.get(times.size() / 2) / NS_PER_S;
        double q3Time = (double) times.get(3 * times.size() / 4) / NS_PER_S;
        double longestTime = (double) times.get(times.size() - 1) / NS_PER_S;

        System.out.println("SEQUENTIAL FILLING");
        System.out.println("Total Time (" + SIZE + " entries) : " + totalTime + " s");
        System.out.println("Avg. Time per entry: " + (double) avgTime / 1000000000 + " s");
        System.out.println("---------------------------------------");
        System.out.println("Min: " + shortestTime + " s");
        System.out.println("Q1: " + q1Time + " s");
        System.out.println("Median: " + medianTime + " s");
        System.out.println("Q3: " + q3Time + " s");
        System.out.println("Max: " + longestTime + " s");
    }
}