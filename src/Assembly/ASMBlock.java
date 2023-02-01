package Assembly;

import Assembly.Inst.Inst;

import java.util.ArrayList;
import java.util.LinkedList;

public class ASMBlock {
    public String blockName;
    public LinkedList<Inst> insts = new LinkedList<>();
    public ASMBlock headBlock = null;
    public LinkedList<ASMBlock> tailBlock = new LinkedList<>();

    public ASMBlock(String name) {
        this.blockName = name;
    }

    public void addInst(Inst inst) {
        insts.add(inst);
    }

    public String print(){
        String ret = "";
        if (blockName!=null) ret += blockName + ":\n";
        for (Inst inst : insts)
            ret += "\t" + inst + "\n";
        return ret;
    }

}
