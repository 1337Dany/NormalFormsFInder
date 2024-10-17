import java.util.ArrayList;

public class NFFinder {

    public static boolean isTrivialDependancy(ArrayList<String> key) {
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
                String compare1 = String.valueOf(Keys.getKeys().get(i));
                String compare2 = String.valueOf(key);
                if(compare1.contains(compare2))
                    return false;
            }
        }
        return false;
    }

    public static String findNormalForm() {
        boolean twoNF = true;
        boolean threeNF = true;
        boolean BCNF = true;

        //Check is trivial dep?
        for (ArrayList<String> key : Dependency.getDependencies().keySet()) {
            if (BCNF) {
                if (BCNF != isTrivialDependancy(key)) {
                    BCNF = false;
                }
            }
            if (BCNF) {
                if (BCNF != XIsSuperkey(key)) {
                    BCNF = false;
                }
            }
            if (threeNF) {
                if (threeNF != AIsKeyAttribute(key)) {
                    threeNF = false;
                }
            }
            if (twoNF) {
                if (twoNF != isPartialDependancy(key)) {
                    twoNF = false;
                }
            }
        }

        return BCNF ? "BCNF" : threeNF ? "3NF" : twoNF ? "2NF" : "Neither";
    }


}
