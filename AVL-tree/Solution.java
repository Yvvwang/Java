import java.io.*;
import java.util.*;

class Node <E> {
 private Node<E> left;
 private Node<E> right;
 private E data;
 private int height;

 Node(E data) {
  this.data = data;
  left = null;
  right = null;
 }

 void setData(E d) {
  data = d;
 }

 E getData() {
  return data;
 }

 void setLeft(Node<E> i) {
  left = i;
 }

 void setRight(Node<E> i) {
  right = i;
 }

 Boolean hasLeft() {
  return left != null;
 }
 
 Node<E> getLeft() {
  return left;
 }

 Boolean hasRight() {
  return right != null;
 }
 Node<E> getRight() {
  return right;
 }
 
 int getheight() {
  return height;
 }
 
 void setheight() {
     int left;
     int right;
     if (hasLeft()) {
         left = getLeft().getheight();
     }else {
         left = 0;
     }
     if (hasRight()) {
         right = getRight().getheight();
     }else {
         right = 0;
     }
     height = (Math.max(left, right) + 1);
 }
 
 int factor() {
     int left;
     int right;
     if (hasLeft()) {
         left = getLeft().getheight();
     }else {
         left = 0;
     }
     if (hasRight()) {
         right = getRight().getheight();
     }else {
         right = 0;
     }
     return left - right;
 }
}

class AVL <E extends Comparable<?super E>> {
  private Node<E> root;
  AVL() {
    root = null;
  }
  Node<E> getRoot() { 
    return root; 
  }
   Node<E> rotateLeft(Node<E> root) {
   Node<E> tree = root.getRight();
   root.setRight(tree.getLeft());
   tree.setLeft(root);
   return tree;
 }
   
   Node<E> rotateRight(Node<E> root) {
       Node<E> tree = root.getLeft();
       root.setLeft(tree.getRight());
       tree.setRight(root);
       return tree;
   
 }

  void height() {
      height(root);
  }
 int height(Node<E> tree){
     if (tree == null){
      return 0;
    }else{
      return tree.getheight();
    }
 }
 
  void insert(E data) {
    root = insert(root, data);
 }
  
  Node<E> insert(Node<E> root,E data) {
    if (root == null) {
      return new Node<E>(data);
    }else{
      Node<E> cur;
      int result = root.getData().compareTo(data);
      
      if (root.getData().compareTo(data) > 0) {
          root.setLeft(insert(root.getLeft(), data));
      } else {
          root.setRight(insert(root.getRight(), data));
      }

      int hf = root.factor();
      if (hf > 1) {
          if (root.getLeft().factor() < 0) {
              root.setLeft(rotateLeft(root.getLeft()));
          }
          
          root = rotateRight(root);
      } else if (hf < -1) {
          if (root.getRight().factor() > 0) {
              root.setRight(rotateRight(root.getRight()));
          }
          root = rotateLeft(root);
      }

       if (root.hasLeft()) {
           root.getLeft().setheight();
       }
        if (root.hasRight()) {
           root.getRight().setheight();
       }
       root.setheight();
       return root;
    }
  }
}

public class Solution {
   public static void main(String[] argv) {
     int n;
     String type;
     Scanner in = new Scanner(System.in);
     n = in.nextInt();
     type = in.next();
     if (type.equals("int")){
       AVL<Integer> tree = new AVL<Integer>();
       for(int i = 0; i < n; i ++) {
           tree.insert(in.nextInt());
         }
       System.out.print("root=" + tree.getRoot().getData());
       System.out.print(" height=" + tree.getRoot().getheight());
       System.out.print(" heightfactor=" + tree.getRoot().factor());
     }
     if (type.equals("string")){
       AVL<String> tree = new AVL<String>();
       for(int i = 0; i < n; i ++) {
           tree.insert(in.next());
         }
       System.out.print("root=" + tree.getRoot().getData());
       System.out.print(" height=" + tree.getRoot().getheight());
       System.out.print(" heightfactor=" + tree.getRoot().factor());
     }
   }
}