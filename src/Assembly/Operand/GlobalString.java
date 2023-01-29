package Assembly.Operand;

import MIR.entity.constant;

public class GlobalString extends Global{
    String str;

    public GlobalString(constant c){
        this.str = c.irType.string_value;
        this.type = ".asciz";
        if (c.irType.string_value_r == 0) this.name = ".L.str";
        else this.name = ".L.str." + c.irType.string_value_r;
    }

    @Override
    public String toString(){
        return name+":\n\t\t" + type + "\t\"" + this.str + "\"\n";
    }

}

