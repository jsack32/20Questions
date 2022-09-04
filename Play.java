import java.io.*;
/**
 * Plays the game 20 questions
 *
 * @author Jesse Sack, Lifu Zhang, Tom Mitchell
 * @version 0.0.1
 */
public class Play {
    private static Tree20Q game;
    public static void main(String[] args) throws IOException {
        game = new Tree20Q();
        try {
            game.rebuild();
        } 
        catch(FileNotFoundException ex) { }
        game.run();
        game.overwrite();
    }
}
