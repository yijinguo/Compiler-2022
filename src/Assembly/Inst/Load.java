package Assembly.Inst;

import Assembly.Operand.Imm;
import Assembly.Operand.Reg;

public class Load extends Inst{
    int type;

    public Load(int type, Reg rd, Reg rs1, Imm imm){
        this.type = type;
        this.rd = rd;
        this.rs1 = rs1;
        this.imm = imm;
    }

    @Override
    public String toString(){
        return ("l" + (type == 1 ? "b" : "w") + "\t" + rd + ", " + imm + "(" + rs1 + ")");
    }

}
