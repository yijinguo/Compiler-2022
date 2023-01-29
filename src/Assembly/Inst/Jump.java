package Assembly.Inst;

import Assembly.ASMBlock;

public class Jump extends Inst{
    ASMBlock destination;

    public Jump(ASMBlock b){
        this.destination = b;
    }

    @Override
    public String toString(){
        return ("j\t" + destination.blockName);
    }
}
