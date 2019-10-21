import java.io.*;
import java.util.*;

class Node {
    Node left;
    Node right;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
public class Solution {
    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }
    public static void printrange(Node root, int low, int high){
        if (root != null){
            printrange(root.left, low, high);
        if (root.data >= low && root.data <= high) {
            System.out.print(root.data + " ");
         }
         printrange(root.right, low, high);
     }
    }
        
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        Node root = null;
        while (num-- > 0){
            int actualnum = s.nextInt();
            root = insert(root,actualnum);
        }
        int low = s.nextInt();
        int high = s.nextInt();
        s.close();
        printrange(root,low,high);
    }
}