package wordsearch;

import java.util.Random;
/**
 * Created by nirajthapaliya on 2/8/15.
 */
public class Letters {
    int[] bag;
    int [] histogram;
    Random random;

    public Letters() {
        random = new Random();
        bag = new int[]{
                9, 2, 2, 4, 12,
                2, 3, 2, 9, 1,
                1, 4, 2, 6, 8,
                2, 1, 6, 4, 6,
                4, 2, 2, 1, 2,
                1,
        };
        histogram = new int[bag.length];
//      Sum();
    }

    // while I hate this, Sum() conveniently updates the local histogram as well.
    public int Sum(){
        int sum = 0;
        for (int i = 0; i < bag.length; i++) {
            sum += bag[i];
            histogram[i] = sum;
        }

        return sum;
    }

    public char DrawRandom() {
        int sum = Sum();
        if (sum < 1){
            return '!';
        }

        // pick a random number from [0..n] inclusive
        int pick = random.nextInt(sum);
        int index = 0;

        while (histogram[index] <= pick){
            index++;
        }
        bag[index]--;
        return (char) (index+'a');
    }

    void Add(char c) {
        bag[c-'a']++;
    }
}
