package Assembly;

import Assembly.Operand.Reg;

import java.util.ArrayList;

public class ASMFunction {

    public String funcName;
    public ArrayList<ASMBlock> Blocks = new ArrayList<>();
    public ArrayList<Reg> params = new ArrayList<>();

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
        String ret = funcName;
        for (ASMBlock b : Blocks) {
            ret += b;
        }
        return ret;
    }

}
