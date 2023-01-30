package MIR.type;

public class IRPtr extends IRType{

    public IRType base;
    public int dim = 1;

    public IRPtr(IRType base) {
        super(base.name + "*", 4);
        if (base instanceof IRPtr) {
            this.base = ((IRPtr) base).base;
            this.dim = ((IRPtr) base).dim + 1;
        } else {
            this.base = base;
            //this.dim = 1;
        }
    }

    public IRPtr(IRType base, int dim) {
        super(base.name + "*".repeat(dim), 4);
        if (base instanceof IRPtr) {
            this.base = ((IRPtr)base).base;
            this.dim = dim + ((IRPtr) base).dim;
        } else {
            this.base = base;
            this.dim = dim;
        }
    }

}
