package Assembly.Operand;

import MIR.entity.*;

public class VirtualImm extends Reg{
    public int value;
    public VirtualImm(int value) {
        this.value = value;
    }

    public VirtualImm(constant c) {
        if (c instanceof consInt) {
            value = ((consInt) c).value;
        } else if (c instanceof consBool) {
            value = ((consBool) c).value ? 1 : 0;
        } else if (c instanceof consCondition) {
            value = ((consCondition) c).value ? 1 : 0;
        } else {
            value = 0;
        }
    }

    public String toString() {
        return Integer.toString(value);
    }

}
