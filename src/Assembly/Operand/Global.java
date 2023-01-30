package Assembly.Operand;

public abstract class Global extends Reg{
    public String name;
    public String type;
    //.asciz : GlobalString
    // .word : int, string
    // .byte : bool
    // .zero : class

    public abstract String toString();
    //name:
    //      type    value
}

//j:
//        .word   1                               # 0x1
//
//kk:
//        .byte   1                               # 0x1
//
//.L.str:
//        .asciz  "hello"
