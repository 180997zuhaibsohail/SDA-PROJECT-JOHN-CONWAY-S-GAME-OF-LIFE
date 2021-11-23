package Game;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){
        System.out.println("I am Game.");
        Board board = new Board(30);
        board.Print();
        board.Draw(3,3);
        board.Draw(4,4);
        board.Draw(5,2);
        board.Draw(5,3);
        board.Draw(5,4);
        board.SetDelay(1);
        board.Print();
        board.Start();
    }
}
