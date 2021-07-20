/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        Integer i = 1;
        String word = "";
        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();
            Float p = (float) 1 / i;
            Boolean probability = StdRandom.bernoulli(p);
            if (probability == true) {
                word = value;
            }
            i = i + 1;
        }
        StdOut.println(word);
    }
}
