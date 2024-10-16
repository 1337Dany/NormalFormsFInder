import java.util.*;

public class Dependency {
    Map<ArrayList<String>, ArrayList<String>> dependencies = new HashMap<>();

    Dependency(Scanner scanner) {

        System.out.println("Write dependencies:\t(Example: A,B->C)");
        System.out.println("To stop writing data enter \"stop\"");

        String dependancy = scanner.nextLine();
        while (!dependancy.equals("stop")) {
            parceDependancy(dependancy);
            dependancy = scanner.nextLine();
        }
    }

    private void parceDependancy(String line) {

        try {
            var sides = line.split("->");
            var leftHandSide = sides[0];
            var rightHandSide = sides[1];

            putDependancyToMap(leftHandSide, rightHandSide);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect input");
        }
    }

    private void putDependancyToMap(String leftHandSide, String rightHandSIde) {
        dependencies.put(
                new ArrayList<>() {
                    {
                        addAll(Arrays.asList(leftHandSide.split(",")));
                    }
                },
                new ArrayList<>() {
                    {
                        addAll(Arrays.asList(rightHandSIde.split(",")));
                    }
                }
        );
    }

    public Map<ArrayList<String>, ArrayList<String>> getDependencies() {
        return dependencies;
    }
}
