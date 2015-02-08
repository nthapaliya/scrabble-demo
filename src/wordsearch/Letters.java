package wordsearch;

/**
 * Created by nirajthapaliya on 2/8/15.
 */
public class Letters {
    int[] Bag;
    Letters() {
        Bag = new int[]{
                9, 2, 2, 4, 12,
                2, 3, 2, 9, 1,
                1, 4, 2, 6, 8,
                2, 1, 6, 4, 6,
                4, 2, 2, 1, 2,
                1,
        };
//        System.out.println(Sum(Bag)); // Should be 98
    }
    int Sum(int[] bag){
        int sum = 0;
        for (int num: bag){
            sum+=num;
        }
        return sum;
    }
}
