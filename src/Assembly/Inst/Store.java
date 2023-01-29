package Assembly.Inst;

import Assembly.Operand.Imm;
import Assembly.Operand.Reg;

public class Store extends Inst{

    int type;

    public Store(int type, Reg rs1, Reg rs2, Imm imm){
        this.type = type;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.imm = imm;
    }

    @Override
    public String toString(){
        return ("s" + (type==1 ? "b" : "w") + "\t" + rs2 + ", " + imm + "(" + rs1 + ")");
    }
}
