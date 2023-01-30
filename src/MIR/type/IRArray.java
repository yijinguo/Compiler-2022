package MIR.type;

public class IRArray extends IRType{

    public IRType base;
    public int cnt;

    public IRArray(IRType base, int cnt){
        super("[" + cnt + " x " + base.name + "]", base.size * cnt);
        this.base = base;
        this.cnt = cnt;
    }

}
