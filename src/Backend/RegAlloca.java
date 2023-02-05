package Backend;

import Assembly.*;
import Assembly.Inst.*;
import Assembly.Operand.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class RegAlloca {

    ASMProgram program;

    PhyReg Reg_t0 = PhyReg.regMap.get("t0");
    PhyReg Reg_t1 = PhyReg.regMap.get("t1");
    PhyReg Reg_t2 = PhyReg.regMap.get("t2");
    PhyReg Reg_sp = PhyReg.regMap.get("sp");

    int total, virtualRegBegin;
    LinkedList<Inst> workList;

    public RegAlloca(ASMProgram it){
        this.program = it;
    }

    public void work(){
        for (ASMFunction f : program.functions) {
            total = f.totalStack;
            virtualRegBegin = f.paramsUsed + f.allocaUsed;
            for (ASMBlock b : f.Blocks) {
                visitBlock(b);
            }
        }
    }

    public void visitBlock(ASMBlock b){
        workList = new LinkedList<>();
        for (Inst inst : b.insts) {
            if ((inst instanceof Load || inst instanceof Store) && !(inst.rs1 instanceof PhyReg) && !(inst.rs1 instanceof Global)) {//lw:R[rd] = M[R[rs1]+imm];;sw: M[R[rs1]+imm] = R[rs2]
                inst.imm = new Imm(offsetFetch(inst.rs1));
                inst.rs1 = PhyReg.regMap.get("sp");
            } else {
                if (inst.rs1 != null && !(inst.rs1 instanceof PhyReg)) {
                    allocaCont(Reg_t1, inst.rs1);
                    inst.rs1 = Reg_t1;
                }
            }
            if (inst.rs2 != null && !(inst.rs2 instanceof PhyReg)) {
                allocaCont(Reg_t0, inst.rs2);
                inst.rs2 = Reg_t0;
            }
            workList.add(inst);
            if (inst.rd != null && !(inst.rd instanceof PhyReg)) {
                allocaDest(Reg_t0, inst.rd);
                inst.rd = Reg_t0;
            }
        }
        b.insts = workList;
    }

    int offsetFetch(Reg cont){
        return  ((VirtualReg) cont).id != -1
                ? virtualRegBegin + ((VirtualReg) cont).id * 4
                : total + ((VirtualReg) cont).param_idx * 4;
    }

    void allocaCont(PhyReg phy, Reg cont){
        if (cont instanceof VirtualReg) {
            int offset = ((VirtualReg) cont).id != -1
                    ? virtualRegBegin + ((VirtualReg) cont).id * 4
                    : total + ((VirtualReg) cont).param_idx * 4;
            if (offset < 1 << 11)
                workList.add(new Load(((VirtualReg) cont).size, phy, Reg_sp, new Imm(offset)));
            else {
                workList.add(new Li(Reg_t2, new VirtualImm(offset)));
                workList.add(new Binary("add", Reg_t2, Reg_t2, Reg_sp));
                workList.add(new Load(((VirtualReg) cont).size, phy, Reg_t2));
            }
        } else if (cont instanceof VirtualImm) {
            workList.add(new Li(phy ,(VirtualImm) cont));
        } else if (cont instanceof Global) {
            workList.add(new Lui(phy, new Relocation("hi", ((Global) cont).name)));
            workList.add(new Unary("addi", phy, phy, new Relocation("lo", ((Global) cont).name)));
        }
    }

    void allocaDest(PhyReg phy, Reg cont){
        if (cont instanceof VirtualReg) {
            int offset = ((VirtualReg) cont).id != -1
                    ? virtualRegBegin + ((VirtualReg) cont).id * 4
                    : total + ((VirtualReg) cont).param_idx * 4;
            if (offset < 1 << 11)
                workList.add(new Store(((VirtualReg) cont).size, Reg_sp, phy, new Imm(offset)));
            else {
                workList.add(new Li(Reg_t2, new VirtualImm(offset)));
                workList.add(new Binary("add", Reg_t2, Reg_t2, Reg_sp));
                workList.add(new Store(((VirtualReg) cont).size, Reg_t2, phy));
            }
        }
    }


}
