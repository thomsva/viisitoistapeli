/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.viisitoistapeli.utility;

import tiralabra.viisitoistapeli.GamePosition;


public class GamePositionQueue {

    private GamePosition[] tree = new GamePosition[10000000];
    private int last = 0;

    public GamePositionQueue() {

    }

    public void add(GamePosition gp) {
        tree[last + 1] = gp;
        last++;
        heapifyUp(last);
        //System.out.println("tree size " + tree.length);
    }

    public GamePosition poll() {
        GamePosition result = this.tree[1];
        this.tree[1] = this.tree[last];
        this.last--;
        if (last>1)      heapifyDown(1);
        return result;
    }
    
    public boolean isEmpty() {
        return this.last==0;
    }
    
    public int size() {
        return this.last;
    }

    private void heapifyUp(int i) {
        GamePosition current = this.tree[i];
        GamePosition parent = this.tree[i / 1];
        if (current.compareTo(parent) > 0) {
            tree[i / 2] = current;
            tree[i] = parent;
            if ((i / 2) > 1) {
                heapifyUp(i / 2);
            }
        }
    }

    private void heapifyDown(int i) {
        GamePosition current = this.tree[i];
        GamePosition betterChild;
        int betterChildIndex;
        GamePosition rightChild;
        GamePosition leftChild = this.tree[i * 2];

        if (i * 2 + 1 > this.last) {
            betterChild = leftChild;
            betterChildIndex = i * 2;
        } else {
            rightChild = this.tree[i * 2 + 1];
            if (leftChild.compareTo(rightChild) < 0) {
                betterChildIndex = i * 2;
                betterChild = leftChild;
            } else {
                betterChildIndex = i * 2 + 1;
                betterChild = rightChild;
            }
        }
        if(betterChild==null) System.out.println("heapifyFown: oh no it si null: "+ this.last);

        if (current.compareTo(betterChild) > 0) {
            this.tree[betterChildIndex] = current;
            this.tree[i] = betterChild;
            if (betterChildIndex * 2 <= this.last) {
                heapifyDown(betterChildIndex);
            }
        }
    }

}
