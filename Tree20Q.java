import java.util.*;
import java.io.*;
/**
 * Tree20Q class - stores nodes representing the questions and answers in a binary tree.
 *
 * @author Jesse Sack, Lifu Zhang, Tom Mitchell
 * @version 0.0.1
 */
public class Tree20Q {
    private static Node root;

    public Tree20Q() {  //starter tree before taught anything
        root = new Node("Is it a fire type?");
        root.setAnswer(false);
        root.setLeft(new Node("Squirtle"));
        root.setRight(new Node("Charmander"));
    }

    /**
     * update method - updates the Tree20Q if an answer if an answer choice is not
     * in a tree
     * 
     * @param Node n - the current wrong answer node
     * @param newAnswer - the new answer that the user has in mind
     * @param newQuestion - a question input by the user to distinguish newAnswer from the original answer
     * @param relations - relationship between newAnswer and newQuestion
     */
    public void update(Node n, String newAnswer, String newQuestion, boolean relations) {
        String original = n.getInfo();  //stores info of wrong answer
        n.setInfo(newQuestion); //sets the current node's answer to the new question
        n.setAnswer(false);
        if(relations)
        {
            n.setRight(new Node(newAnswer));
            n.setLeft(new Node(original));
        }
        else
        {
            n.setLeft(new Node(newAnswer));
            n.setRight(new Node(original));
        }
    }

    /**
     * run method - a runner method to play 20 questions
     */
    public void run() {
        Scanner scan = new Scanner(System.in);
        Node currNode = root;   //keep track of current question
        while(true) {
            //get new boolean from scanner
            if(!currNode.isAnswer()) {
                System.out.println(currNode.getInfo());
                if(scan.nextLine().contains("n"))
                    currNode = currNode.getLeft();
                else
                    currNode = currNode.getRight();
            }
            else {
                System.out.println("Is it " + currNode.getInfo() + "?");
                if(scan.nextLine().contains("y")) {
                    System.out.println("Epic!");
                }
                else
                {
                    //get new answer, question, and boolean
                    System.out.println("What were you thinking of?");   //new answer
                    String newAnswer = scan.nextLine();
                    System.out.println("Type a question distinguishing \"" + newAnswer
                        + "\" and \"" + currNode.getInfo() + "\".");    //new question
                    String newQuestion = scan.nextLine();
                    System.out.println();
                    System.out.println("With \"" + newAnswer + "\" in mind,");
                    System.out.println(newQuestion);    //relation
                    System.out.println("'y' or 'n'");
                    boolean relation = scan.nextLine().contains("y");
                    update(currNode, newAnswer, newQuestion, relation);
                }
                System.out.println();
                System.out.println("Play again?");
                if(scan.nextLine().contains("n")) {
                    System.out.println("Thanks for playing!");
                    break;
                }
                currNode = root;    //reset game during new play
                System.out.println();
            }
        }
    }

    /**
     * overwrite method - overwrite and save the tree in a file
     * by calling a recursive preorder traversal of the tree
     */
    public void overwrite() throws IOException {
        PrintWriter writer = new PrintWriter(new File("gameData.txt"));
        overwrite(root, writer);
        writer.close();
    }

    private void overwrite(Node p, PrintWriter writer) {    //preorder traversal of the tree
        if(p.isAnswer()) {
            writer.println("%");    //% denotes that it's an answer; used in rebuild as base case
            writer.println(p.getInfo());
            return;
        }
        writer.println(p.getInfo());
        overwrite(p.getLeft(), writer);
        overwrite(p.getRight(), writer);
    }

    /**
     * rebuild method - rebuilds a Tree20Q saved from a previous game
     */
    public static void rebuild() throws IOException {
        Scanner scan = new Scanner(new File("gameData.txt"));
        root = rebuild(scan);
    }

    private static Node rebuild(Scanner s) {    //preorder recursion
        if(!s.hasNext())    //traversed whole file
            return null;
        String str = s.nextLine();
        if(str.equals("%")) {   //is answer node
            //p = new Node(s.nextLine());
            return new Node(s.nextLine());
        }
        Node p = new Node(str);
        p.setAnswer(false); //not an answer
        p.setLeft(rebuild(s));
        p.setRight(rebuild(s));
        return p;
    }
}
