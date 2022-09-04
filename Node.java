
/**
 * Stores either a question or a final guess. Leaf nodes will store final
 * guesses, and intermediate nodes hold questions. Answering yes or no
 * will eventually lead to the answers in the leaf nodes. The tree expands
 * learning new answers, input by users, that aren't in the tree's database.
 * Answering yes navigates to a Node's right child, while no navigates to a
 * Node's left child.
 *
 * @author Jesse Sack, Lifu Zhang, Tom Mitchell
 * @version 0.0.1
 */
public class Node {
    private String info;
    private boolean ans; //keeps track of if it's an answer or a question node
    private Node left, right;
    public Node(String i) {
        info = i;
        ans = true;
    }
    
    public void setInfo(String i) {
        info = i;
    }

    public String getInfo() {
        return info;
    }

    public Node getRight() {
        return right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node p) {
        left = p;
    }

    public void setRight(Node p) {
        right = p;
    }
    
    public void setAnswer(boolean b) {
        ans = b;
    }
    
    public boolean isAnswer() {
        return ans;
    }
}
