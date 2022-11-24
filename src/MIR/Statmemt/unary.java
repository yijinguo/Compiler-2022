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
       /* if (v instanceof constant) {
            type i0 = ((constant) v).getType(), i = new type(i0.irType), r = new type(i0.irType);
            switch (op) {
                case "!" -> i.boolean_value = r.boolean_value = !(i0.boolean_value);
                case "~" -> i.int_value = r.int_value = ~(i0.int_value);
                case "+" -> i.int_value = r.int_value = i0.int_value;
                case "-" -> i.int_value = r.int_value = -(i0.int_value);
                case "++" -> {
                    i.int_value = i0.int_value + 1;
                    r.int_value = i0.int_value;
                }
                case "--" -> {
                    i.int_value = i0.int_value - 1;
                    r.int_value = i0.int_value;
                }
                default -> {

                }
            }

        }*/
    }

}
