import java.util.ArrayList;

public class Keys {
    public ArrayList<ArrayList<String>> keys;
    public ArrayList<String> keyArguments;
    public Dependency dependency;

    public ArrayList<String> variables;
    public ArrayList<String> unobtainable;
    public ArrayList<String> availableAttributes;

    public ArrayList<String> temporaryKey;


    public Keys(Dependency dependency, Relation relation) {
        this.dependency = dependency;

        this.variables = new ArrayList<>(relation.relation);

        checkObtainable();

        this.availableAttributes = new ArrayList<>(relation.relation);
        availableAttributes.removeAll(unobtainable);

        findKeys();
    }

    private void checkObtainable() {
        for (ArrayList<String> leftHandSide : dependency.getDependencies().keySet()) {
            for (String rightVar : dependency.dependencies.get(leftHandSide)) {
                unobtainable.remove(rightVar);
            }
        }
    }

    private void findKeys() {
        ArrayList<ArrayList<String>> possibleKeys = new ArrayList<>();
        for (ArrayList<String> combination : findCombinations()) {
            checkDependencies(combination);
            if (isIdentical(temporaryKey, variables)) {
                possibleKeys.add(temporaryKey);
            }
        }

        for (ArrayList<String> possibleKey : possibleKeys) {
            boolean hasSubsets = false;
            for (ArrayList<String> comparedTo : keys) {
                if (isSubset(possibleKey, comparedTo)) {
                    hasSubsets = true;
                    break;
                }
            }
            if (!hasSubsets) {
                keys.add(possibleKey);
                for (String attribute : possibleKey) {
                    if (!keyArguments.contains(attribute)) keyArguments.add(attribute);
                }
            }
        }
    }

    private ArrayList<ArrayList<String>> findCombinations () {
        //todo сделать говнокод в любом виде и любой ценой
    }

    private void checkDependencies(ArrayList<String> extension) {
        temporaryKey = new ArrayList<>(unobtainable);
        temporaryKey.addAll(extension);

        boolean extended = true;
        while (extended) {
            extended = false;

            for (ArrayList<String> leftHandSide : dependency.getDependencies().keySet()) {

                if (isSuperset(temporaryKey, leftHandSide)) {
                    for (String rightVar : dependency.dependencies.get(leftHandSide)) {

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