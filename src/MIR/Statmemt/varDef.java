package MIR.Statmemt;

import MIR.*;

public class varDef extends statement{
    public register var;
    public boolean have_init = false;
    public entity init;

    public varDef(register var){
        super();
        this.var = var;
    }

    public varDef(register var, entity init){
        super();
        this.var = var;
        have_init = true;
        this.init = init;
    }
}
