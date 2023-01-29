package Assembly.Inst;

import Assembly.Operand.Imm;
import Assembly.Operand.Reg;

public abstract class Inst {

    public Reg rd, rs1, rs2;
    public Imm imm;

    public abstract String toString();
}
