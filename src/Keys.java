import java.util.ArrayList;
//todo fix this shit
public class Keys {
    private static final ArrayList<ArrayList<String>> keys;
    private static final ArrayList<String> keyArguments;
    static {
        keys = new ArrayList<>();
        keyArguments = new ArrayList<>();
    }


    private final ArrayList<String> variables;
    private final ArrayList<String> unobtainable;
    private final ArrayList<String> availableAttributes;

    private ArrayList<String> temporaryKey;

    public static ArrayList<ArrayList<String>> getKeys() {
        return keys;
    }
    public static ArrayList<String> getKeyArguments() {
        return keyArguments;
    }

    public Keys() {

        this.variables = new ArrayList<>(Relation.getRelation());

        this.unobtainable = new ArrayList<>(Relation.getRelation());
        checkObtainable();

        this.availableAttributes = new ArrayList<>(Relation.getRelation());
        availableAttributes.removeAll(unobtainable);

        findKeys();
        System.out.println(Keys.getKeys());
    }

    private void checkObtainable() {
        for (ArrayList<String> leftHandSide : Dependency.getDependencies().keySet()) {
            for (String rightVar : Dependency.getDependencies().get(leftHandSide)) {
                unobtainable.remove(rightVar);
            }
        }
    }

    private void findKeys() {
        ArrayList<ArrayList<String>> possibleKeys = new ArrayList<>();
        for (ArrayList<String> combination : findCombinations()) {
            combination.addAll(unobtainable);
            checkDependencies(combination);
            if (isIdentical(temporaryKey, variables)) {
                possibleKeys.add(combination);
            }
        }

        for (ArrayList<String> possibleKey : possibleKeys) {
            boolean hasSubsets = false;
            for (ArrayList<String> comparedTo : Keys.keys) {
                if (isSubset(possibleKey, comparedTo)) {
                    hasSubsets = true;
                    break;
                }
            }
            if (!hasSubsets) {
                Keys.keys.add(possibleKey);
                System.out.println(keys);
                for (String attribute : possibleKey) {
                    if (!Keys.keyArguments.contains(attribute)) Keys.keyArguments.add(attribute);
                }
            }
        }
    }

    private ArrayList<ArrayList<String>> findCombinations() {
        ArrayList<ArrayList<String>> combinations = new ArrayList<>();
        findCombinationsRecursive(availableAttributes, new ArrayList<>(), 0, combinations);
        return combinations;
    }

    private void findCombinationsRecursive(ArrayList<String> elements, ArrayList<String> currentCombination, int index, ArrayList<ArrayList<String>> combinations) {
        combinations.add(new ArrayList<>(currentCombination));

        for (int i = index; i < elements.size(); i++) {
            currentCombination.add(elements.get(i));
            findCombinationsRecursive(elements, currentCombination, i + 1, combinations);
            currentCombination.removeLast();
        }
    }

    private void checkDependencies(ArrayList<String> extension) {
        temporaryKey = new ArrayList<>(extension);

        boolean extended = true;
        while (extended) {
            extended = false;

            for (ArrayList<String> leftHandSide : Dependency.getDependencies().keySet()) {

                if (isSuperset(temporaryKey, leftHandSide)) {
                    for (String rightVar : Dependency.getDependencies().get(leftHandSide)) {

                        if (!temporaryKey.contains(rightVar)) {
                            temporaryKey.add(rightVar);
                            extended = true;
                        }
                    }
                }

            }

        }
    }

    private static boolean isSuperset(ArrayList<String> superset, ArrayList<String> set) {
        for (String val : set) {
            if (!superset.contains(val)) return false;
        }
        return true;
    }

    private static boolean isSubset(ArrayList<String> subset, ArrayList<String> set) {
        for (String val : subset) {
            if (!set.contains(val)) return false;
        }
        return true;
    }

    private static boolean isIdentical(ArrayList<String> a, ArrayList<String> b) {
        for (String val : a) {
            if (!b.contains(val)) return false;
        }
        return true;
    }
}