package test;

/**
 * ClassName:TextDemo8
 * Package:test
 * Description:
 *
 * @Author:HP
 * @date:2021/5/27 13:09
 */
public class MyMap {
    static class Node {
        public int key;
        public int val;

        public Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public Node[] arr;
    public int size;
    public MyMap() {
        this.arr = new Node[10];
    }

    public void put(int key,int val) {
        Node node = new Node(key,val);
        int index = key % arr.length;
        Node cur = arr[index];
        while (cur != null) {
            if(cur.key == key) {
                cur.val = val;
                return;
            }
            cur = cur.next;
        }
        node.next = arr[index];
        arr[index] = node;
        this.size++;

        if(loadFactor() > 0.75) {
            resize();
        }
    }
    public double loadFactor() {
        return this.size * 1.0 / arr.length;
    }

    public void resize() {
        Node[] newArr = new Node[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            Node cur = arr[i];
            while(cur != null) {
                int index = cur.key % newArr.length;
                Node curNext = cur.next;
                cur.next = newArr[index];
                newArr[index] = cur;
                cur = curNext;
            }
        }
        arr = newArr;
    }

    public int get(int key) {
        int idx = key % arr.length;
        Node cur = arr[idx];
        while(cur != null) {
            if(cur.key == key) {
                return cur.val;
            }
            cur = cur.next;
        }
        return -1;
    }

    public void remove (int key) {
        int idx = key % arr.length;
        Node cur = arr[idx];
        Node prev = null;
        while (cur != null) {
            if(cur.key == key) {
                if(prev == null) {
                    arr[idx] = cur.next;
                    this.size--;
                    return;
                } else {
                    prev.next = cur.next;
                    this.size--;
                    return;
                }
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
    }

    public static void main(String[] args) {
        MyMap map = new MyMap();
        map.put(1,2);
        map.put(11,6);
        map.put(5,6);
        map.put(25,8);
        map.put(11,9);
        System.out.println(map.get(25));
        System.out.println(map.get(11));
        map.remove(1);
        System.out.println(map.get(1));
    }
}
