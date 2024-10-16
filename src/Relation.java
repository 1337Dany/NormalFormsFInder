import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Relation {
    ArrayList<String> relation = new ArrayList<>();
    Relation(Scanner scanner) {
        System.out.println("Input relation:\t(Example: A,B,C,D)");
        System.out.println("{");
        relation.addAll(Arrays.asList(scanner.nextLine().split(",")));
        System.out.println("}");
    }
    public ArrayList<String> getRelation(){
        return relation;
    }
}