package MIR.Statmemt;

import MIR.IRVisitor;

public abstract class statement {
    public abstract void accept(IRVisitor visitor);

}
