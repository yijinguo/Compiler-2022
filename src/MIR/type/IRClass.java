package MIR.type;

import java.util.ArrayList;
import java.util.HashMap;

import MIR.*;

public class IRClass extends IRType{

    public ArrayList<IRType> memberType = new ArrayList<>();
    public HashMap<String, Integer> memberMap = new HashMap<>();
    public boolean have_build = false;
    public function build = null;

    public IRClass(String name, int size){
        super("%class." + name, size);
    }

    public void add_member(String name, IRType type){
        memberType.add(type);
        memberMap.put(name, memberType.size() - 1);
    }

    public IRType get_member(String name){
        return memberMap.containsKey(name) ? null : memberType.get(memberMap.get(name));
    }

    public void calc_size() {
        size = memberType.size() << 2;
        //size = 0;
        //memberType.forEach(x->{size += x.size; });
    }



}
