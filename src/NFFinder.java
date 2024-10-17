import java.util.ArrayList;
import java.util.Arrays;
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
                String compare1 = String.valueOf(Keys.getKeys().get(i));
                String compare2 = String.valueOf(key);
                return !compare1.contains(compare2);
            }
        }
        return false;
    }

    public static String findNormalForm() {
        boolean twoNF = true;
        boolean threeNF = true;
        boolean BCNF1 = true;
        boolean BCNF2 = true;

        //Check is trivial dep?
        for (ArrayList<String> key : Dependency.getDependencies().keySet()) {
            if (BCNF1) {
                if (BCNF1 != isTrivialDependancy(key)) {
                    BCNF1 = false;
                }
            }
            if(BCNF2){
                if (BCNF2 != XIsSuperkey(key)) {
                    BCNF2 = false;
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

        return BCNF1 || BCNF2 ? "BCNF" : threeNF ? "3NF" : twoNF ? "2NF" : "Neither";
    }


}
