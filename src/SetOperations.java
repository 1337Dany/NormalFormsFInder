import java.util.ArrayList;

public class SetOperations {
    private SetOperations(){}

    public static boolean isSuperset(ArrayList<String> superset, ArrayList<String> set) {
        for (String val : set) {
            if (!superset.contains(val)) return false;
        }
        return true;
    }

    public static boolean isSubset(ArrayList<String> subset, ArrayList<String> set) {
        for (String val : subset) {
            if (!set.contains(val)) return false;
        }
        return true;
    }

    public static boolean isProperSubset(ArrayList<String> properSubset, ArrayList<String> set) {
        for (String val : properSubset) {
            if (!set.contains(val)) return false;
        }
        return properSubset.size() < set.size();
    }

    public static boolean isIdentical(ArrayList<String> a, ArrayList<String> b) {
        for (String val : a) {
            if (!b.contains(val)) return false;
        }
        for (String val : b) {
            if (!a.contains(val)) return false;
        }
        return true;
    }
}