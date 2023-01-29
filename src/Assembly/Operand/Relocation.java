package Assembly.Operand;

public class Relocation extends Imm{

    public String type;
    public String dest;

    public Relocation(){
        super(0);
    }

    public String toString(){
        return (type.equals("hi")) ? ("%hi(" + dest + ")") : ("%lo(" + dest + ")");
    }

}
