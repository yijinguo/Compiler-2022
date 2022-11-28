package Backend;

import MIR.*;

public interface Pass {
    void visitBlock(block b);
    void visitFunction(function f);
}
