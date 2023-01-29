package Assembly.Operand;

public class Imm extends Operand{
    int value;

    public Imm(int value){
        this.value = value;
    }

    public String toString(){
        return Integer.toString(value);
    }
}
