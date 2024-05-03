import java.util.Deque;
import java.util.LinkedList;

public class Latihan{
public static void main(String[] args){
    Deque<String> stack = new LinkedList<>();

    stack.offerLast("Satoru");
    stack.offerLast("Suguru");

    for(var item= stack.pollLast(); item != null; item = stack.pollLast()){
        System.out.println(item);
        }
    }
}