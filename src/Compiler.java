import AST.RootNode;
import Frontend.ASTBuilder;
import Frontend.SemanticChecker;
import Frontend.SymbolCollector;
import Util.Scope.globalScope;
import Util.MxErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

import grammar.*;

public class Compiler {

    public static void main(String[] args) throws Exception{
        String name="src/testcase.mx";
        InputStream input=new FileInputStream(name);
        //InputStream input = System.in;
        try {
            RootNode root;
            globalScope GlobalScope=new globalScope(null);
            GlobalScope. initialize();

            MxLexer lexer=new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());

            MxParser parser=new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());

            ParseTree parseTreeRoot=parser.program();

            ASTBuilder astBuilder=new ASTBuilder(GlobalScope);
            root=(RootNode) astBuilder.visit(parseTreeRoot);
            SymbolCollector symbolCollector=new SymbolCollector(GlobalScope);
            symbolCollector.visit(root);

            SemanticChecker semanticChecker=new SemanticChecker(GlobalScope);
            semanticChecker.visit(root);

            /*
            //ast to ir
            IRBuilder irBuilder = new IRBuilder(GlobalScope);
            irBuilder.visit(root);

            PrintStream ir_out = new PrintStream(new FileOutputStream("src/output.ll"));
            IRPrinter irPrinter = new IRPrinter(ir_out);
            irPrinter.printIR(irBuilder);
            */
        }catch (Error err){
//            System.out.println(err.errorMsg());
            throw err;
        }
    }
}