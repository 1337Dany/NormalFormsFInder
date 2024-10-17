import java.util.ArrayList;

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
            System.out.println("comb: " + combination);
            checkDependencies(combination);
            if (SetOperations.isIdentical(temporaryKey, variables) && !combination.isEmpty()) {
                possibleKeys.add(combination);
            }
            temporaryKey = null;
        }
        System.out.println("poss: " + possibleKeys);

        for (ArrayList<String> possibleKey : possibleKeys) {
            boolean hasSubsets = false;
            for (ArrayList<String> comparedTo : possibleKeys) {
                if (SetOperations.isSubset(comparedTo, possibleKey) &&
                        !(possibleKey.size() == comparedTo.size())) {
                    hasSubsets = true;
                    break;
                }
            }
            if (!hasSubsets) {
                Keys.keys.add(possibleKey);
                for (String attribute : possibleKey) {
                    if (!Keys.keyArguments.contains(attribute)) Keys.keyArguments.add(attribute);
                }
            }
            System.out.println("keys: " + keys);
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

    private void checkDependencies(ArrayList<String> combination) {
        temporaryKey = new ArrayList<>(combination);
        System.out.println("temporaryKey: " + temporaryKey);

        boolean extended = true;
        while (extended) {
            extended = false;

            for (ArrayList<String> leftHandSide : Dependency.getDependencies().keySet()) {

                if (SetOperations.isSuperset(temporaryKey, leftHandSide)) {
                    for (String rightVar : Dependency.getDependencies().get(leftHandSide)) {

                        if (!temporaryKey.contains(rightVar)) {
                            temporaryKey.add(rightVar);
                            extended = true;
                        }
                    }
                }

            }

        }
        System.out.println("temporaryKeyAfter: " + temporaryKey);
    }
}