import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int n;


    public RandomizedQueue() {
        array = (Item[]) new Object[1];
        n = 0;
    }

    // construct an empty randomized queue
    private class RandomizedQueueIterator implements Iterator<Item> {
        private final int[] randomArrayOrder;
        private int originalSize;
        private int m;

        public RandomizedQueueIterator() {
            m = 0;
            originalSize = n;
            randomArrayOrder = createRandomArrayIndexes();
        }

        public boolean hasNext() {
            return originalSize > m;
        }

        public Item next() {
            if (hasNext()) {
                if (n == originalSize) {
                    return array[randomArrayOrder[m++]];
                }
                else {
                    throw new UnsupportedOperationException(
                            "You have modified the array after initializing the iterator.");

                }
            }
            else {
                throw new NoSuchElementException();
            }
        }

        private int[] createRandomArrayIndexes() {
            int[] randomOrder = new int[originalSize];

            for (int i = 0; i < originalSize; i++) {
                randomOrder[i] = i;
            }
            StdRandom.shuffle(randomOrder);
            return randomOrder;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isArrayIncreaseNeeded()) {
            resizeArray(2 * array.length);
        }
        array[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        handleEmpty();
        int randomNum = StdRandom.uniform(n);
        Item item = array[randomNum];

        array[randomNum] = array[--n];
        array[n] = null;

        if (isArrayShrinkNeeded()) {
            resizeArray(array.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        handleEmpty();
        return array[StdRandom.uniform(n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();

        System.out.println("Is Empty:" + randomizedQueue.isEmpty());
        randomizedQueue.enqueue("Hello");
        randomizedQueue.enqueue("World");
        randomizedQueue.enqueue("This");
        System.out.println("Size:" + randomizedQueue.size());
        randomizedQueue.enqueue("is");
        randomizedQueue.enqueue("Gabriel");
        randomizedQueue.enqueue("!");
        System.out.println("Size:" + randomizedQueue.size());

        System.out.println("Dequeu 1" + randomizedQueue.dequeue());
        System.out.println("Size:" + randomizedQueue.size());


        for (String s : randomizedQueue) {
            System.out.println("Loop: " + s);
        }

    }

    private void handleEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    private boolean isArrayIncreaseNeeded() {
        return n == array.length;
    }

    private boolean isArrayShrinkNeeded() {
        // System.out.println("Shrink");
        // System.out.println("array.length: " + array.length);
        // System.out.println("array.length/4: " + array.length / 4);
        // System.out.println("N: " + N);
        return (n > 0 && n == array.length / 4);
    }

    private void resizeArray(int capacity) {
        // System.out.println("Resize" + capacity);
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = array[i];
        }
        array = copy;
    }
}
