import java.util.ArrayList;

public class ShowNFTable {

    ArrayList<String> dependencyList = new ArrayList<>();

    public ShowNFTable() {
        showResult();
    }

    private void showResult() {
        int columnWidth = 15;

        System.out.println("-----------------------");
        System.out.print("Keys:");
        showKeys();
        System.out.println("-----------------------");

        String relation = String.valueOf(Relation.getRelation());

        System.out.printf("%-" + columnWidth + "s%-" + columnWidth + "s%-" + columnWidth + "s%-" + columnWidth + "s%-"
                        + columnWidth + "s%n",
                relation,
                "Trivial dep.", "X is a super key", "A is key attribute", "Not a pratial dep.");

        for (ArrayList<String> key : Dependency.getDependencies().keySet()) {

            String value1 = getArrayDataAsLine(key) + " -> " + getArrayDataAsLine(Dependency.getDependencies().get(key));
            String value2 = NFFinder.isTrivialDependancy(key) ? "T" : "F";
            String value3 = NFFinder.XIsSuperkey(key) ? "T" : "F";
            String value4 = NFFinder.AIsKeyAttribute(key) ? "T" : "F";
            String value5 = NFFinder.isPartialDependancy(key)? "T" : "F";

                    System.out.printf("%" + (columnWidth + value1.length()) / 2 + "s%" + (columnWidth + value2.length()) / 2 + "s%"
                            + (columnWidth + value3.length()) / 2 + "s%" + (columnWidth + value4.length()) / 2 + "s%"
                            + (columnWidth + value5.length()) / 2 + "s%n", value1, value2, value3, value4, value5);

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
            System.out.println("K" + i + " = ");
            System.out.println(getArrayDataAsLine(Keys.getKeys().get(i)));
        }
    }
}
