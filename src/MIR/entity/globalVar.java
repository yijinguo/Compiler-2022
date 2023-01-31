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
        if (irType instanceof IRInt) {
            switch (irType.size) {
                case 4 -> init = new consInt(0);
                case 1 -> init = new consBool(false);
                default -> {}
            }
        } else if (irType instanceof IRPtr) {
            init = new consString("");
        } else if (irType instanceof IRClass) {

        }
    }

    @Override
    public String toString(){
        return "@" + identity;
    }

    @Override
    public String printWithType(){
        return irType.toString() + this;
    }
}
