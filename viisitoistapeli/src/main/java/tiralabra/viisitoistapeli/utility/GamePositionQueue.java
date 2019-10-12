package tiralabra.viisitoistapeli.utility;

import tiralabra.viisitoistapeli.GamePosition;

/**
 * A class made to replace the Java PriorityQueue class. Made specifically to
 * handle a queue of GamePosition objects stored as a complete binary tree.
 */
public class GamePositionQueue {

    private GamePosition[] tree = new GamePosition[10]; //initial size
    private int last = 0; //the position of the last item in the queue

    /**
     * Add a new game position to the heap.
     *
     * @param gp the GamePosition object to add
     */
    public void add(GamePosition gp) {
        tree[last + 1] = gp;
        last++;
        heapifyUp(last);
        if (last == tree.length - 1) {
            //need to expand tree
            expandTree();
        }
    }

    /**
     * Poll the top game position from the heap.
     *
     * @return the GamePosition object currently on top of the heap.
     */
    public GamePosition poll() {
        GamePosition result = this.tree[1];
        this.tree[1] = this.tree[last];
        this.last--;
        if (last > 1) {
            heapifyDown(1);
        }
        if (last > 100 && last < tree.length / 4) {
            //can free up space
            shortenTree();
        }
        return result;
    }

    /**
     * Method to double the size of the tree if it is running out of space
     */
    private void expandTree() {
        GamePosition[] newTree = new GamePosition[tree.length * 2];
        for (int i = 0; i <= last; i++) {
            newTree[i] = tree[i];
        }
        tree = newTree;
        //System.out.println("tree expanded to " + tree.length);
    }

    /**
     * Method to cut the size of the tree in half to free up space.
     */
    private void shortenTree() {
        GamePosition[] newTree = new GamePosition[tree.length / 2];
        for (int i = 0; i <= last; i++) {
            newTree[i] = tree[i];
        }
        tree = newTree;
    }

    /**
     * Tests if the queue is empty
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return this.last == 0;
    }

    /**
     * @return size of queue
     */
    public int size() {
        return this.last;
    }

    /**
     * Recursive method used to maintain the min heap property after adding new
     * item to the heap.
     */
    private void heapifyUp(int i) {
        if (i == 1) {
            return;
        }
        GamePosition current = this.tree[i];
        GamePosition parent = this.tree[i / 2];
        if (current.compareTo(parent) < 0) {
            //current better than parent, replace
            tree[i / 2] = current;
            tree[i] = parent;
            if ((i / 2) > 1) {
                heapifyUp(i / 2);
            }
        }
    }

    /**
     * Recursive method used to maintain the min heap property after polling an
     * item from the top of the heap.
     */
    private void heapifyDown(int i) {
        if (i * 2 > last) {
            return;
        }
        GamePosition current = this.tree[i];
        GamePosition betterChild;
        int betterChildIndex;
        GamePosition rightChild;
        GamePosition leftChild = this.tree[i * 2];

        if (i * 2 + 1 > this.last) {
            //only left child exists
            betterChild = leftChild;
            betterChildIndex = i * 2;
        } else {
            rightChild = this.tree[i * 2 + 1];
            if (leftChild.compareTo(rightChild) < 0) {
                //left child is better
                betterChildIndex = i * 2;
                betterChild = leftChild;
            } else {
                //right child is better
                betterChildIndex = i * 2 + 1;
                betterChild = rightChild;
            }
        }
        if (betterChild == null) {
            System.out.println("heapifyFown: oh no it is null: " + this.last);
        }

        if (current.compareTo(betterChild) > 0) {
            //child is better than current, replace
            this.tree[betterChildIndex] = current;
            this.tree[i] = betterChild;
            heapifyDown(betterChildIndex);
        }
    }
}
