package MIR.Statmemt;

import MIR.*;

public class preAdd extends statement{
    public String op;
    public register re;
    public entity v;

    public preAdd(register re, String op, entity v){
        super();
        this.re = re;
        this.op = op;
        this.v = v;
    }
}
