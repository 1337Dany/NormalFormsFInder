import java.util.ArrayList;

public class NFFinder {

    public static boolean isTrivialDependency(ArrayList<String> leftHandSide) {
       return SetOperations.isSubset(leftHandSide, Dependency.getDependencies().get(leftHandSide));
    }

    public static boolean XIsSuperKey(ArrayList<String> leftHandSide) {
       for (ArrayList<String> key : Keys.getKeys()) {
           if (SetOperations.isSuperset(leftHandSide, key)) return true;
       }
       return false;
    }

    public static boolean AIsKeyAttribute(ArrayList<String> mapKey) {
        return SetOperations.isSubset(Dependency.getDependencies().get(mapKey), Keys.getKeyArguments());
    }

    public static boolean isPartialDependency(ArrayList<String> leftHandSide) {
        for (ArrayList<String> key : Keys.getKeys()) {
            if (SetOperations.isProperSubset(leftHandSide, key)) return true;
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
                if (BCNF1 != isTrivialDependency(key)) {
                    BCNF1 = false;
                }
            }
            if(BCNF2){
                if (BCNF2 != XIsSuperKey(key)) {
                    BCNF2 = false;
                }
            }
            if (threeNF) {
                if (threeNF != AIsKeyAttribute(key)) {
                    threeNF = false;
                }
            }
            if (twoNF) {
                if (twoNF == isPartialDependency(key)) {
                    twoNF = false;
                }
            }
        }

        return BCNF1 || BCNF2 ? "BCNF" : threeNF ? "3NF" : twoNF ? "2NF" : "Neither";
    }
}