package Util.Scope;

public class loopScope extends Scope{

    public boolean isLoop = true;

    public loopScope(Scope parentScope){
        super(parentScope);
    }
}
