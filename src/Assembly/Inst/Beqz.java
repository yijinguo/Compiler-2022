package Assembly.Inst;

import Assembly.ASMBlock;
import Assembly.Operand.Reg;

public class Beqz extends Inst{

    public ASMBlock block;

    public Beqz(Reg rs1, ASMBlock b){
        this.rs1 = rs1;
        this.block = b;
    }

    @Override
    public String toString(){
        return ("beqz\t" + rs1 + ", " + block.blockName);
    }
}
