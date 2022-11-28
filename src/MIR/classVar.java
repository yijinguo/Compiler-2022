package MIR;

import MIR.*;
import java.util.ArrayList;
import java.util.HashMap;

public class classVar {
    public String className;
    public HashMap<String, entity> entities = new HashMap<>();
    public HashMap<String, function> functions = new HashMap<>();
    public classVar(String className){
        this.className = className;
    }
    public entity getEntity(String name){
        return entities.get(name);
    }
}
