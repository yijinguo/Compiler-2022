import java.io.FileInputStream;
import java.io.InputStream;
import Util.*;



public class Compiler {

    public static void main(String[] args) throws Exception{
        String name="testcase.mx";
        InputStream input=new FileInputStream(name);
        //InputStream input = System.in;
        try {
            RootNode root;
            GlobalScope globalScope=new GlobalScope(null);
            globalScope.initializeGlobalScope();

            MxLexer lexer=new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());

            MxParser parser=new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());

            ParseTree parseTreeRoot=parser.program();
            ASTBuilder astBuilder=new ASTBuilder(globalScope);
            root=(RootNode) astBuilder.visit(parseTreeRoot);
            SymbolCollector symbolCollector=new SymbolCollector(globalScope);
            symbolCollector.visit(root);
            SemanticChecker semanticChecker=new SemanticChecker(globalScope);
            semanticChecker.visit(root);
            // System.out.println("0");
        }catch (Error err){
//            System.out.println(err.errorMsg());
            throw err;
        }
    }
}