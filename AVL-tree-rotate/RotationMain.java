import java.util.*;

class Node <E> {
 private Node<E> left;
 private Node<E> right;
 private E data;

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
}

class BST <E extends Comparable<?super E>> {
 private Node<E> root;
 BST() {
  root = null;
 }
 Node<E> getRoot() { 
  return root; 
 }
 void inOrder() {
  System.out.print("in-order: ");
  inOrder(root);
  System.out.println();
 }
 void inOrder(Node<E>root) {
  if(root == null) return;
  inOrder(root.getLeft());
  System.out.print(root.getData() + " ");
  inOrder(root.getRight());
 }
 void preOrder() {
  System.out.print("pre-order: ");
  preOrder(root);
  System.out.println();
 }
 void preOrder(Node<E>root) {
  if(root == null) return;
  System.out.print(root.getData() + " ");
  if(root.hasLeft()) preOrder(root.getLeft());
  if(root.hasRight()) preOrder(root.getRight());
 }
 void insert(E data) {
  root = insert(root, data);
 }
 Node<E> insert(Node<E> root, E data) {
  if (root == null) {
   return new Node<E>(data);
  } else {
   Node<E> cur;
   if (root.getData().compareTo(data) > 0) {
    cur = insert(root.getLeft(), data);
    root.setLeft(cur);
   } else {
    cur = insert(root.getRight(), data);
    root.setRight(cur);
   }
   return root;
  }
 }
 //apply left rotate to the root node
 void rotateLeft() {
   {if(root == null)
     return;}
   Node<E> tree = root.getRight();
   root.setRight(tree.getLeft());
   tree.setLeft(root);
   root = tree;
 }

 //apply right rotate to the root node
 void rotateRight() {
   {if(root == null)
     return;}
   Node<E> tree = root.getLeft();
   root.setLeft(tree.getRight());
   tree.setRight(root);
   root = tree;
 }

}

public class RotationMain {
 public static void main(String[] argv) {
  int n;
  Scanner in = new Scanner(System.in);
  n = in.nextInt();
  BST<Integer> tree = new BST<Integer>();
  for(int i = 0; i < n; i ++) {
   tree.insert(in.nextInt());
  }
  in.close();
  //if BST is correctly built, items will be displayed in sorted order using
  //in-order traversal
  tree.inOrder();

  System.out.println("before rotation: ");
  tree.preOrder();
  
  System.out.println("after rotating left: ");
  tree.rotateLeft();
  tree.preOrder(); 
  
  System.out.println("after rotating right: ");
  tree.rotateRight();
  tree.preOrder();
 } 
}