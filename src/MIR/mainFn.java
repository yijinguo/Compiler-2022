package MIR;

import java.util.HashSet;
import java.util.Set;

public class mainFn {
    public block rootBlock = null;
    public Set<block> blocks = new HashSet<>();

    public mainFn(){
        blocks.add(rootBlock);
    }
}
