package wordsearch;

import java.util.Random;

/**
 * Created by nirajthapaliya on 2/8/15.
 */
public class Letters {
    private static final int[] VALUES = {
            1, 3, 3, 2, 1, 4, 2, 4, 1, 8,
            5, 1, 3, 1, 1, 3, 10, 1, 1, 1,
            1, 4, 4, 8, 4, 10,
    };
    private static final int[] STARTING_BAG = {
            9, 2, 2, 4, 12, 2, 3, 2, 9, 1,
            1, 4, 2, 6, 8, 2, 1, 6, 4, 6,
            4, 2, 2, 1, 2, 1,
    };
    private static final int TOTAL_LETTERS = sum(STARTING_BAG);
    private int[] bag;
    private int[] histogram;
    private Random random;

    public Letters() {
        random = new Random();
        bag = STARTING_BAG.clone();

        histogram = new int[bag.length];
        assert histogram.length == bag.length;
    }

    private static int sum(int[] n) {
        int sum = 0;
        for (int i : n) {
            sum += i;
        }
        return sum;
    }

    public static int Value(char c) {
        return VALUES[c - 'a'];
    }

    // while I hate this, Sum() conveniently updates the local histogram as well.
    public int Sum() {
        int sum = 0;
        for (int i = 0; i < bag.length; i++) {
            sum += bag[i];
            histogram[i] = sum;
        }
        assert sum <= TOTAL_LETTERS;
        return sum;
    }

    public char DrawRandom() {
        int sum = Sum();
        if (sum < 1) {
            return '!';
        }

        // pick a random number from [0..n] inclusive
        int pick = random.nextInt(sum);
        int index = 0;

        while (histogram[index] <= pick) {
            index++;
        }
        bag[index]--;
        return (char) (index + 'a');
    }

    void Add(char c) {
        bag[c - 'a']++;
        assert Sum() <= TOTAL_LETTERS;
    }
}
