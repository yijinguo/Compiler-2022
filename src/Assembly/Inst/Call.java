package Assembly.Inst;

public class Call extends Inst{

    public String funcName;

    public Call(String name){
        this.funcName = name;
    }

    @Override
    public String toString(){
        return ("call\t" + this.funcName);
    }
}
