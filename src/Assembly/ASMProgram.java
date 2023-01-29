package Assembly;

import Assembly.Operand.Global;

import java.util.ArrayList;

public class ASMProgram {
    public ArrayList<Global> globals = new ArrayList<>();
    public ArrayList<ASMFunction> functions = new ArrayList<>();
}
