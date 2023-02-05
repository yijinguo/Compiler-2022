package Backend;

import Assembly.ASMProgram;
import java.io.PrintStream;

public class ASMPrinter {

    PrintStream out;

    public ASMPrinter(PrintStream out) {
        this.out = out;
    }

    public void print(ASMProgram program) {
        //todo
        out.print("\t.text\n");
        if (program.globals != null) {
            out.print("\t.section\t.rodata\n");
            program.globals.forEach(x -> out.print(x));
            out.print("\t.text\n");
        }
        program.functions.forEach(x -> {
            if (x.funcName.equals("_GLOBAL__sub_I_example.cpp")) out.print(x);
        });
        program.functions.forEach(x -> {
            if (!x.funcName.equals("_GLOBAL__sub_I_example.cpp")) out.print(x);
        });
    }

}
