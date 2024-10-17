import java.util.ArrayList;

public class ShowNFTable {

    public ShowNFTable() {
        showResult();
    }

    private void showResult() {
        int columnWidth = 15;

        System.out.println("-----------------------");
        System.out.println("Keys:");
        showKeys();
        System.out.println("-----------------------");

        String relation = String.valueOf(Relation.getRelation());

        System.out.printf("%-15s %-15s %-20s %-20s %-20s%n",
                relation, "Trivial dep.", "X is a super key", "A is key attribute", "Not a pratial dep.");

        for (ArrayList<String> key : Dependency.getDependencies().keySet()) {

            String value1 = getArrayDataAsLine(key) + " -> " + getArrayDataAsLine(Dependency.getDependencies().get(key));
            String value2 = NFFinder.isTrivialDependancy(key) ? "T" : "F";
            String value3 = NFFinder.XIsSuperkey(key) ? "T" : "F";
            String value4 = NFFinder.AIsKeyAttribute(key) ? "T" : "F";
            String value5 = NFFinder.isPartialDependancy(key)? "T" : "F";

                    System.out.printf("%-15s %-15s %-20s %-20s %-20s%n", value1,value2,value3,value4,value5);

        }

        System.out.println("-----------------------");

        System.out.println("Answer: " + NFFinder.findNormalForm());

    }

    private String getArrayDataAsLine(ArrayList<String> arrayList) {
        String line = "";
        for (String s : arrayList) {
            line += s ;
        }
        return line;
    }

    private void showKeys() {
        for (int i = 0; i < Keys.getKeys().size(); i++) {
            System.out.print("K" + i + " = ");
            System.out.println(getArrayDataAsLine(Keys.getKeys().get(i)));
        }
    }
}
