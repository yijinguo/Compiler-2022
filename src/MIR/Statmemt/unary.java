package MIR.Statmemt;

import MIR.*;

public class unary extends statement{

    public register re;
    public entity v;
    public String op;

    public unary(register re, entity v, String op){
        super();
        this.re = re;
        this.v = v;
        this.op = op;
    }

}
