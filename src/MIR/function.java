package MIR;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class function{

    public String returnType;
    public String funcName;
    public boolean isInClass;
    public String className;
    public HashMap<String, entity> paraList = new HashMap<>();
    public LinkedList<entity> entities = new LinkedList<>();
    public HashMap<String, entity> entities_q = new HashMap<>();
    public block rootBlock = null;
    public Set<block> blocks = new HashSet<>();
    public int reg_num = 0;
    public function(String returnType, String funcName){
        this.returnType = returnType;
        this.funcName = funcName;
        rootBlock = new block(0);
        blocks.add(rootBlock);
    }

    public void push_entity(int num, String name, entity entry){
        ((register) entry).reg_num = num;
        this.entities.add(entry);
        this.entities_q.put(name, entry);
        reg_num++;
    }

    public entity getEntity(String name) {
        return this.entities_q.getOrDefault(name, null);
    }
}
