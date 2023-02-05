package MIR.entity;

import MIR.type.*;
import MIR.*;
import Util.Type;

public class globalVar extends register{

    public entity init = null;
    public boolean ifClass = false;
    public function build = null;

    public globalVar(String name, IRType type) {
        super(name, new IRPtr(type));
        --reg_cnt;
    }

    public void initial(){
        IRType tmp = ((IRPtr)irType).pointDown();
        if (tmp instanceof IRInt) {
            switch (irType.size) {
                case 4 -> init = new consInt(0);
                case 1 -> init = new consBool(false);
                default -> {}
            }
        } else if (tmp.name.equals("i8*")) {
            init = null;
        } else if (tmp instanceof IRPtr) {
            init = new consNull(irType);
        } else if (tmp instanceof IRClass) {
            init = new consNull(irType);
        }
    }

    @Override
    public String toString(){
        return "@" + identity;
    }

    @Override
    public String printWithType(){
        return irType.toString() + " " + this;
    }
}
