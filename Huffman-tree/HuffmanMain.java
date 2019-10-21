import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Scanner;

class BinNode<Key, E> implements Comparable<BinNode<Key, E>> {
	private Key key;
	private E value;
	private BinNode<Key, E> left;
	private BinNode<Key, E> right;
	public BinNode(Key k, E e) {
		key = k;
		value = e;
		left = right = null;
	}
	public Key getKey() { return key; }
	public E getValue() { return value; }
	public void setKey(Key k) { key = k; }
	public void setValue(E e) { value = e; }
	public void setLeft(BinNode<Key, E> node) { left = node; }
	public void setRight(BinNode<Key, E> node) { right = node; }
	public BinNode<Key, E> getLeft() { return left; }
	public BinNode<Key, E> getRight() { return right; }
	public boolean isLeaf() {
		if((left == null) && (right == null)) return true;
		else return false;
	}
	@Override
  public int compareTo(BinNode<Key, E> other) {
      return (int) this.getKey() - (int) other.getKey();
  }
}

class HuffTree<Key, E> {
	private PriorityQueue<BinNode<Integer, String>> queue = new PriorityQueue<BinNode<Integer, String>>(); //for building the huffman tree
	private BinNode<Integer, String> root; //for traversal
	private Dictionary<String, String> codeTable; //huffman code table
	private Dictionary<String, Integer> codeFreq;
	public HuffTree(String letters, int[] weights) {
		init(letters, weights);
		buildTree();
		codeTable = new Hashtable<String, String>();
		getCode();		
		summary();
	}
	private void init(String letters, int[] weights) {
		codeFreq = new Hashtable<String, Integer>();
		for(int i = 0; i < letters.length(); i ++) 
			codeFreq.put(letters.substring(i, i + 1), weights[i]);
		for(int i = 0; i < letters.length(); i ++) {
			BinNode<Integer, String> node = new BinNode<Integer, String>(weights[i], letters.substring(i, i + 1));
			queue.add(node);
		}
	}
	private void buildTree() {
		while(queue.size() > 1) {
			BinNode<Integer, String> node1 = queue.remove(); 
			BinNode<Integer, String> node2 = queue.remove();
			BinNode<Integer, String> newnode = new BinNode<Integer, String>(node1.getKey() + node2.getKey(), " ");
			newnode.setLeft(node1);
			newnode.setRight(node2);
			queue.add(newnode);
			queue.toString();
		}
		root = queue.remove();
		System.out.println("root of the hufftree: weight " + root.getKey());
	}
	public void summary() {
		if(codeTable.isEmpty()) {
			System.out.println("codes not found");
			return;
		}
		//display the code & compute the sum of weighted path lengths
		Enumeration<String> keys = codeFreq.keys();
		int sumOfWeightedPath = 0;
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
		    System.out.println("Letter: " + key + " " + codeTable.get(key));
		    sumOfWeightedPath += codeTable.get(key).length() * codeFreq.get(key); 
		}
		System.out.println("Total letters: " + root.getKey());
		System.out.println("Sum of weighted path lengths: " + sumOfWeightedPath);
		System.out.println("Ave-Code-Length: " + sumOfWeightedPath * 1.0 / root.getKey());
	}
	//function 1: get the codes by walking in the tree
	//each leaf node is a letter, and the corresponding path is the code
	//for simplicity, the codes are represented using strings of "0" and "1", not bits
	private void getCode() {
		getCodeHelp(root, "");	
	}
	
	private void getCodeHelp(BinNode<Integer, String>entry, String incode) {
		if(entry == null) return;
		if(entry.getLeft() != null) {
			getCodeHelp(entry.getLeft(), incode + "0");
			getCodeHelp(entry.getRight(), incode + "1");
		}
		else {
			codeTable.put(entry.getValue(), incode);
		} 
	}

	//to-implement
	//encode a message
	public String encode(String instr) {
		String code = "";
		for (int i = 0; i < instr.length(); i++) {
			code = code.concat(codeTable.get(instr.substring(i,i+1)));
		}
		return code;
	}
	
	//to-implement
	//decode a message
	
	public String decode(String coded) {
		String code = "";
		BinNode<Integer, String> cur = root;
		for (int i = 0; i< coded.length(); i++) {
			 String c = coded.substring(i,i+1);
			 if (c.equals("0")) {
				 cur = cur.getLeft();
					 
			 }else if (c.equals("1")) {
					 cur = cur.getRight();
			 }
			 if (cur.isLeaf()) {
				 code += cur.getValue();
				 cur = root;
			 }
		}
		return code;	
	}
}
/*	public String decode(String coded) {
/		String code = "";
		BinNode<Integer, String> cur = root;
		for (int i = 0; i< coded.length(); i++) {
			 String c = coded.substring(i,i+1);
			 if (c.equals("0")) {
				 if(root.getLeft().isLeaf()) {
					 code += cur.getLeft().getValue();
					 cur = root;
					 }else {
						 cur = cur.getLeft();
					 }
			 }else if (c.equals("1")) {
				 if (root.getRight().isLeaf()) {
					 code += cur.getRight().getValue();
					 cur = root;
				 }else {
					 cur = cur.getRight();
				 }
			 }
		}
		return code;	
	}*/

public class HuffmanMain {
	public static void main(String[] argv) {
		//int[] weights = {2, 7, 24, 32, 37, 42, 42, 120};
		//String letters = "ZKMCUDLE";
		Scanner in = new Scanner(System.in);
		String letters = in.next();
		int num = letters.length();
		int[] weights = new int[num];
		for(int i = 0; i < num; i ++) {
			weights[i] = in.nextInt();
		}
		HuffTree<Integer, String> tree = new HuffTree<Integer, String>(letters, weights);
		int test = in.nextInt();
		for(int i = 0; i < test; i ++) {
			String what = in.next();
			String instr = in.next();
			if(what.compareTo("encode") == 0) {
				System.out.println("encode " + instr + " " + tree.encode(instr));
			}
			else if(what.compareTo("decode") == 0) {
				System.out.println("decode " + instr + " " + tree.decode(instr));
			}
		}
	}
}