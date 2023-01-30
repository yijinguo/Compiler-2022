package MIR.type;

public class IRInt extends IRType{

    public int bitSize;

    public IRInt(int bitSize){
        super("i"+bitSize, bitSize/8);
        this.bitSize = bitSize;
    }

}
