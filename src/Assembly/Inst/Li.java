package Assembly.Inst;

import Assembly.Operand.Imm;
import Assembly.Operand.Reg;

public class Li extends Inst{

    public Li(Reg rd, Imm imm){
        this.rd = rd;
        this.imm = imm;
    }

    @Override
    public String toString(){
        return ("li\t" + rd + ", " + imm);
    }
}
