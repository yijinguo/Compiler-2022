package MIR.type;

import java.util.ArrayList;
import java.util.HashMap;

import MIR.*;
import MIR.entity.*;

public class IRClass extends IRType{

    public String className;
    public ArrayList<IRType> memberType = new ArrayList<>();
    public HashMap<String, Integer> memberMap = new HashMap<>();
    public function build;

    public IRClass(String name, int size){
        super("%class." + name, size);
        this.className = name;
        build = new function(new IRVoid(), name + ".build");
    }

    public void add_member(String name, IRType type){
        memberType.add(type);
        memberMap.put(name, memberType.size() - 1);
    }

    public IRType get_member(String name){
        return memberMap.containsKey(name) ? memberType.get(memberMap.get(name)) : null;
    }

    public void calc_size() {
        size = memberType.size() << 2;
        //size = 0;
        //memberType.forEach(x->{size += x.size; });
    }



}
