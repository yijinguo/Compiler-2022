package MIR.Statmemt;

import java.util.ArrayList;
import MIR.*;

public class newExpr extends statement{
    public String typename;
    public int dim = 0;
    public ArrayList<entity> sizeList = new ArrayList<>();

    public newExpr(String typename, int dim){
        super();
        this.typename = typename;
        this.dim = dim;
    }

}
