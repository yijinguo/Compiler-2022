package MIR;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class function{
    public type returnType;
    public String funcName;
    public HashMap<String, entity> paraList = new HashMap<>();
    public block rootBlock = new block(null);
    public Set<block> blocks = new HashSet<>();
    public function(type returnType, String funcName){
        this.returnType = returnType;
        this.funcName = funcName;
        blocks.add(rootBlock);
    }
}
