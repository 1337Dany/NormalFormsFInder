import java.util.ArrayList;

public class Keys {

    /*todo:How to find a key
    *  first - check does everything on right hand side could be obtainable*/

    ArrayList<String> keys = new ArrayList<>();
    Keys(Relation relation, Dependency dependency){
        findKeys(relation, dependency);
    }

    private void findKeys(Relation relation, Dependency dependency){

        //todo: check does everything on right hand side are
        for (int i = 0; i < dependency.getDependencies().size(); i++) {

        }
    }

    public ArrayList<String> getKeys(){
        return keys;
    }
}
