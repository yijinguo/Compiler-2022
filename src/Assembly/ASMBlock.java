package Assembly;

import Assembly.Inst.Inst;

import java.util.ArrayList;

public class ASMBlock {
    public String blockName;
    public ArrayList<Inst> insts = new ArrayList<>();
    public ASMBlock headBlock = null;
    public ArrayList<ASMBlock> tailBlock = new ArrayList<>();

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
