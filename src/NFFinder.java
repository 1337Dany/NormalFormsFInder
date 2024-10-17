import java.util.ArrayList;
import java.util.Map;

public class NFFinder {

    public static boolean isTrivialDependancy(ArrayList<String> key) {
        boolean result;
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < Dependency.getDependencies().get(key).size(); j++) {
                if (key.get(i).equals(Dependency.getDependencies().get(key).get(j)))
                    return true;
            }
        }
        return false;
    }

    public static boolean XIsSuperkey(ArrayList<String> key) {
        for (int i = 0; i < Keys.getKeys().size(); i++) {
            if (key.equals(Keys.getKeys().get(i)))
                return true;
        }
        return false;
    }

    public static boolean AIsKeyAttribute(ArrayList<String> key) {
        for (int i = 0; i < Keys.getKeys().size(); i++) {
            if (Keys.getKeys().get(i).contains(Dependency.getDependencies().get(key)))
                return true;
        }
        return false;
    }

    public static boolean isPartialDependancy(ArrayList<String> key) {
        for (int i = 0; i < Keys.getKeys().size(); i++) {
            if (key.equals(Keys.getKeys().get(i))) {
                return true;
            } else {
                for (int j = 0; j < Keys.getKeys().get(i).size(); j++) {
                    if (Keys.getKeys().get(i).get(j).equals(key.get(j)))
                        return false;
                }
            }
        }
        return false;
    }

    public static String findNormalForm() {
        boolean twoNF = true;
        boolean threeNF = true;
        boolean BCNF = true;
        for (ArrayList<String> key : Dependency.getDependencies().keySet()) {
            if (BCNF && (isTrivialDependancy(key) || XIsSuperkey(key))) {
                BCNF = false;
            }
            if (threeNF) {
                if (AIsKeyAttribute(key)) {
                    threeNF = false;
                }
            }
            if (twoNF && isPartialDependancy(key)) {
                twoNF = false;
            }

        }
        return BCNF ? "BCNF" : threeNF ? "3NF" : twoNF ? "2NF" : "Neither";
    }


}
