import java.io.*;
import java.util.*;

// Java program to search a given key in a given BST

class Node {
    int key, size;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
        size = 1;
    }
}

class BinarySearchTree {
    Node root;
    static int count = 0;

    // Constructor
    BinarySearchTree() {
        root = null;
    }

    // A utility function to insert
    // a new node with given key in BST
    Node insert(Node node, int key) {
        // If the tree is empty, return a new node
        if (node == null) {
            node = new Node(key);
            return node;
        }

        // Otherwise, recur down the tree
        if (key <= node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);

        // Return the (unchanged) node pointer
        return node;
    }

    // Utility function to search a key in a BST
    Node search(Node root, int key) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.key == key)
            return root;

        // Key is greater than root's key
        if (root.key < key)
            return search(root.right, key);

        // Key is smaller than root's key
        return search(root.left, key);
    }

    static void setSize(Node root) {

        while (root != null) {
            int c = 0;

            if (root.left == null && root.right == null) {
                c = 1;
            } else if (root.left != null && root.right != null) {
                c = addSize(root.left) + addSize(root.right) + 1;
            }

            root.size = c;

            return;
        }

    }

    static int addSize(Node n) {
        int c = 0;
        while (n != null && c == 0) {

            if (n.left == null && n.right == null) {
                c = 1;
            }

            c = addSize(n.left) + addSize(n.right) + 1;

            n.size = c;

        }

        return c;
    }

    static void printSize(Node root) {

        // Traverse until root reaches a dead end
        while (root != null) {
            // Print size of key
            System.out.println("(" + root.key + ", " + root.size + ")");
            // Recursive calls
            printSize(root.left);
            printSize(root.right);

            return;

        }

    }

    static void printAmmount(Node root, int am) {

        while (root != null) {
            System.out.println("(" + root.key + ", " + root.size + ") @ " + count);
            // Recursive calls
            count++;

            if (count >= am) {
                System.exit(0);
            }
            printAmmount(root.left, am);
            printAmmount(root.right, am);

            return;

        }
        return;
    }

    // Driver Code
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        // Inserting nodes
        // tree.root = tree.insert(tree.root, 7);
        // tree.insert(tree.root, 3);
        // tree.insert(tree.root, 10);
        // tree.insert(tree.root, 9);
        // tree.insert(tree.root, 13);
        // tree.insert(tree.root, 11);

        ArrayList<Integer> nums = new ArrayList<Integer>();
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            System.out.println("Please enter the name of the file:");
            String fileName = input.nextLine();

            try {
                File fileReader = new File(fileName);
                Scanner myReader = new Scanner(fileReader);
                while (myReader.hasNextInt()) {
                    int data = myReader.nextInt();
                    nums.add(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");

            }
        }

        int n = nums.get(0);
        nums.remove(0);

        tree.root = tree.insert(tree.root, nums.get(0));
        for (int i = 1; i < n; i++) {
            tree.insert(tree.root, nums.get(i));
        }

        setSize(tree.root);

        printAmmount(tree.root, 25);

    }
}
