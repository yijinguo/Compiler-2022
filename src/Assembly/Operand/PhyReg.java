package Assembly.Operand;

import java.util.HashMap;

public class PhyReg extends Reg{
    public String name;
    public static HashMap<String, PhyReg> regMap = new HashMap<>() {
        {
            put("zero", new PhyReg("zero"));
            put("ra", new PhyReg("ra"));
            put("sp", new PhyReg("sp"));
            put("t0", new PhyReg("t0"));
            put("t1", new PhyReg("t1"));
            put("t2", new PhyReg("t2"));
            put("a0", new PhyReg("a0"));
            put("a1", new PhyReg("a1"));
            put("a2", new PhyReg("a2"));
            put("a3", new PhyReg("a3"));
            put("a4", new PhyReg("a4"));
            put("a5", new PhyReg("a5"));
            put("a6", new PhyReg("a6"));
            put("a7", new PhyReg("a7"));
        }
    };

    public PhyReg(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
