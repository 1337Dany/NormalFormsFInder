import java.util.Scanner;

public class NFDeterminer {
    Relation relation;
    Dependency dependency;
    Keys keys = new Keys(relation, dependency);

    NFDeterminer() {
        Scanner scanner = new Scanner(System.in);

        relation = new Relation(scanner);
        dependency = new Dependency(scanner);


    }
}
