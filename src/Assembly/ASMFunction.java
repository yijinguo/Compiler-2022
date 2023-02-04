package Assembly;

import Assembly.Operand.Reg;

import java.util.ArrayList;

public class ASMFunction {

    public String funcName;
    public ArrayList<ASMBlock> Blocks = new ArrayList<>();
    public ArrayList<Reg> params = new ArrayList<>();

    public int totalStack = 0;
    public int paramsUsed = 0, allocaUsed = 0, virtualRegCnt = 0;

    public ASMFunction(String name){
        this.funcName = name;
    }

    public void add_block(ASMBlock b){
        Blocks.add(b);
    }

    public void add_param(Reg p){
        params.add(p);
    }

    public String toString(){
        String ret;
        if (funcName.equals("_GLOBAL__sub_I_example.cpp")) {
            ret = "\t.section\t.rodata\n";
        } else {
            ret = "\t.globl\t" + funcName + "\n\t.type\t" + funcName + ", @function\n";
        }
        ret += funcName + ":\n";
        for (ASMBlock b : Blocks) {
            ret += b.print();
        }
        ret += "\tret\n";
        if (!funcName.equals("_GLOBAL__sub_I_example.cpp")) {
            ret += "\t.size\t" + funcName + ", .-" + funcName + "\n\n";
        }
        return ret;
    }

}
