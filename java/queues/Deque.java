import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int count;

    private class ListIterator implements Iterator<Item> {
        private Node current;

        public ListIterator(Node initialNode) {
            current = initialNode;
        }


        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    // construct an empty deque
    public Deque() {
        count = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (count == 0) {
            first = new Node();
            first.item = item;
            last = first;
        }
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.previous = first;
        }
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newLast = new Node();
        newLast.item = item;
        if (last != null) {
            newLast.previous = last;
            last.next = newLast;
        }
        last = newLast;
        if (first == null) {
            first = last;
        }
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        if (count == 1) {
            first = null;
            last = null;
        }
        else {
            first.next.previous = null;
            first = first.next;
        }
        count--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        if (count == 1) {
            first = null;
            last = null;
        }
        else {
            last.previous.next = null;
            last = last.previous;
        }
        count--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> q = new Deque<String>();
        if (q.isEmpty()) {
            System.out.println("Q is empty.");
        }
        q.addFirst("Hello");
        q.addLast("World");
        q.addLast("My");
        q.addLast("Name");
        q.addLast("is");
        q.addLast("Gabriel");

        Iterator<String> i = q.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

}
