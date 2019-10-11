package tiralabra.viisitoistapeli.utility;
import java.util.Arrays;

/**
 * A class to test the concept of min-max heap using integers.
 * This class is a part of a learning process and it's not actually used for 
 * the project. 
 */
public class IntegerQueue {

    private int[] tree = new int[15];
    private int last = 0;

    public void add(int gp) {
        this.tree[this.last + 1] = gp;
        this.last++;
        heapifyUp(this.last);     
    }

    public int poll() {
        int result = this.tree[1];
        this.tree[1] = this.tree[last];
        this.last--;
        if (this.last>1)      heapifyDown(1);
        return result;
    }
    
    public boolean isEmpty() {
        return this.last==0;
    }
    
    public int size() {
        return this.last;
    }

    private void heapifyUp(int i) {
        if (i==1) return;
        System.out.println("i: "+i);
        int current = this.tree[i];
        int parent = this.tree[i / 2];
        if (current>parent) {
            System.out.println("again");
            tree[i / 2] = current;
            tree[i] = parent;
            if ((i / 2) > 1) {       
                heapifyUp(i / 2);
            }
        }
    }

    private void heapifyDown(int i) {
        int current = this.tree[i];
        int betterChild;
        int betterChildIndex;
        int rightChild;
        int leftChild = this.tree[i * 2];

        if (i * 2 + 1 > this.last) {
            betterChild = leftChild;
            betterChildIndex = i * 2;
        } else {
            rightChild = this.tree[i * 2 + 1];
            if (leftChild>rightChild) {
                betterChildIndex = i * 2;
                betterChild = leftChild;
            } else {
                betterChildIndex = i * 2 + 1;
                betterChild = rightChild;
            }
        }
        if(betterChild==0) System.out.println("heapifyFown: oh no it is zero: "+ this.last);

        if (current<betterChild) {
            this.tree[betterChildIndex] = current;
            this.tree[i] = betterChild;
            if (betterChildIndex * 2 <= this.last) {
                heapifyDown(betterChildIndex);
            }
        }
    }   

    @Override
    public String toString() {
        return "IntegerQueue{" + "tree=" + Arrays.toString(tree) + ", last=" + last + '}';
    }
}