package MIR.Statmemt;

import MIR.IRVisitor;
import MIR.entity.*;
import MIR.type.IRPtr;
import MIR.type.IRType;

import java.util.ArrayList;

public class getelementptr extends statement{

    public entity dest; //register
    public entity ptr;
    public IRType ptrType;
    public ArrayList<entity> indexList = new ArrayList<>();

    public getelementptr(entity dest, entity ptr, entity... indexLists){
        this.dest = dest;
        this.ptr = ptr;
        this.ptrType = ((IRPtr) ptr.irType).pointDown();
        for (entity e : indexLists) this.indexList.add(e);
    }

    //%dest = getelement ptrType, ptr.withType, index.withType...

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
