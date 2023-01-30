package MIR;

import MIR.entity.entity;
import MIR.type.IRClass;

import java.util.HashMap;

public class classVar {
    public IRClass type;
    public String className;
    public function build = null;
    public HashMap<String, entity> entities = new HashMap<>();
    public HashMap<String, function> functions = new HashMap<>();
    public classVar(String className){
        this.className = className;
        type = new IRClass(className);
    }
    public void add_entity(String name, entity e){
        entities.put(name, e);
        type.add_member(name, e.irType);
    }

    public entity getEntity(String name){
        return entities.get(name);
    }
}
