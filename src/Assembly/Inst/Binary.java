package Assembly.Inst;

import Assembly.Operand.Reg;

public class Binary extends Inst{

    String op;
    //mul, div, rem, add, sub, sll, sra, and, xor, or,   slt, sqt, sle, sge,

    public Binary(String op, Reg rd, Reg rs1, Reg rs2){
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        switch (op) {
            case "sdiv" -> this.op = "div";
            case "srem" -> this.op = "rem";
            case "shl" -> this.op = "sll";
            case "ashr" -> this.op = "sra";
            default -> this.op = op;
        }
    }

    @Override
    public String toString(){
        return (op + "\t" + rd + ", " + rs1 + ", " + rs2);
    }
}
