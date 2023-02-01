package Backend;

import Assembly.ASMProgram;
import java.io.PrintStream;

public class ASMPrinter {

    PrintStream out;

    public ASMPrinter(PrintStream out) {
        this.out = out;
    }

    public void print(ASMProgram program){
        //todo
        program.globals.forEach(x->out.print(x));
        program.functions.forEach(x->out.print(x));
    }

}
