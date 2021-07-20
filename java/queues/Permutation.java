import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int amount = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();


        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();
            randomizedQueue.enqueue(value);
        }

        for (int i = 0; i < amount; i++) {
            System.out.println(randomizedQueue.dequeue());
        }
    }
}
