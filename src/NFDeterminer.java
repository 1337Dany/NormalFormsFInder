import java.util.Scanner;

public class NFDeterminer {
    Relation relation;
    Dependency dependency;
    Keys keys;

    NFDeterminer() {
        Scanner scanner = new Scanner(System.in);

        relation = new Relation(scanner);
        dependency = new Dependency(scanner);
        keys = new Keys();

        ShowNFTable showNFTable = new ShowNFTable();
    }
}