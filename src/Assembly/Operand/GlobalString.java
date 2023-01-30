package Assembly.Operand;

import MIR.entity.*;

public class GlobalString extends Global{
    public String str;

    public GlobalString(consString s){
        this.str = s.value;
        this.type = ".asciz";
        if (s.id == 0) this.name = ".L.str";
        else this.name = ".L.str." + s.id;
    }

    @Override
    public String toString(){
        return name+":\n\t\t" + type + "\t\"" + this.str + "\"\n";
    }

}

