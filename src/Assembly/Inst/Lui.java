package Assembly.Inst;

import Assembly.Operand.Imm;
import Assembly.Operand.Reg;

public class Lui extends Inst{

    public Lui(Reg rd, Imm imm){
        this.rd = rd;
        this.imm = imm;
    }

    @Override
    public String toString(){
        return ("lui\t" + rd + ", " + imm);
    }
}
