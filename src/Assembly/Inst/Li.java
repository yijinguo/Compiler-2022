package Assembly.Inst;

import Assembly.Operand.Imm;
import Assembly.Operand.Reg;
import Assembly.Operand.VirtualImm;

public class Li extends Inst{

    public VirtualImm pseudo;

    public Li(Reg rd, VirtualImm imm){
        this.rd = rd;
        this.pseudo = imm;
    }

    @Override
    public String toString(){
        return ("li\t" + rd + ", " + pseudo);
    }
}
