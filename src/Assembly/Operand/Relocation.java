package Assembly.Operand;

public class Relocation extends Imm{

    public String type;
    public String dest;

    public Relocation(String type, String dest){
        super(0);
        this.type = type;
        this.dest = dest;
    }

    public String toString(){
        return (type.equals("hi")) ? ("%hi(" + dest + ")") : ("%lo(" + dest + ")");
    }

}
