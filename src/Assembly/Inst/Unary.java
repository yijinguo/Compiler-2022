package Assembly.Inst;

import Assembly.Operand.Imm;
import Assembly.Operand.Reg;

public class Unary extends Inst{
    String op;
    //seqz(==0), snez(!=0), slli(<<), addi, xori

    public Unary(String op, Reg rd, Reg rs1, Imm imm){
        this.op = op;
        this.rd = rd;
        this.rs1 = rs1;
        this.imm = imm;
    }

    public Unary(String op, Reg rd, Reg rs1){
        this.op = op;
        this.rd = rd;
        this.rs1 = rs1;
        this.imm = null;
    }

    @Override
    public String toString(){
        if (imm == null) {
            return (op + "\t" + rd + ", " + rs1);
        } else {
            return (op + "\t" + rd + ", " + rs1 + ", " + imm);
        }
    }
}
