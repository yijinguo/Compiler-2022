package MIR.Statmemt;

import MIR.*;

public class array extends statement{
    public entity array_name;
    public entity array_dim;

    public array(entity array_name, entity array_dim){
        super();
        this.array_name = array_name;
        this.array_dim = array_dim;
    }
}
