package Util.Scope;

import AST.*;
import Util.Type;
import Util.error.semanticError;
import Util.position;

import java.util.HashMap;

public class globalScope extends Scope {
    public HashMap<String, Type> types = new HashMap<>();

    public void initialize(){

        TypeNode VoidType = new TypeNode(null, "void");
        TypeNode IntType = new TypeNode(null, "int");
        TypeNode BoolType = new TypeNode(null, "bool");
        TypeNode StringType = new TypeNode(null, "string");
        TypeNode NullType = new TypeNode(null, "null");

        //字符串内建
        types.put("int", new Type("int"));
        types.put("bool", new Type("bool"));
        types.put("void", new Type("void"));
        types.put("null", new Type("null"));

        Type classString = new Type("string");
        ClassDefNode classStringDeclNode = new ClassDefNode(null, "string");

        FuncDefNode Length = new FuncDefNode(null, IntType, "length",null);

        ParameterListNode SubStringList = new ParameterListNode(null);
        SubStringList.varList.add(new VarDefUnitNode(null, IntType,"left"));
        SubStringList.varList.add(new VarDefUnitNode(null, IntType,"right"));
        FuncDefNode SubString = new FuncDefNode(null, StringType, "subString", SubStringList);

        FuncDefNode ParseInt = new FuncDefNode(null, IntType, "parseInt", null);

        ParameterListNode OrdList = new ParameterListNode(null);
        OrdList.varList.add(new VarDefUnitNode(null, IntType, "pos"));
        FuncDefNode Ord = new FuncDefNode(null, IntType, "ord", OrdList);

        classStringDeclNode.funcList.add(Length);
        classStringDeclNode.funcList.add(SubString);
        classStringDeclNode.funcList.add(ParseInt);
        classStringDeclNode.funcList.add(Ord);

        classString.classDecl = classStringDeclNode;
        types.put("string", classString);


        //数组内建函数
        ParameterListNode ArrayInitList = new ParameterListNode(null);
        ArrayInitList.varList.add(new VarDefUnitNode(null, StringType, "str"));
        FuncDefNode ArrayInit = new FuncDefNode(null, IntType, "size", ArrayInitList);

        //内建函数
        ParameterListNode PrintList = new ParameterListNode(null);
        PrintList.varList.add(new VarDefUnitNode(null, StringType, "str"));
        FuncDefNode Print = new FuncDefNode(null, VoidType, "print", PrintList);

        ParameterListNode PrintlnList = new ParameterListNode(null);
        PrintlnList.varList.add(new VarDefUnitNode(null, StringType, "str"));
        FuncDefNode Println = new FuncDefNode(null, VoidType, "println", PrintList);

        ParameterListNode PrintIntList = new ParameterListNode(null);
        PrintIntList.varList.add(new VarDefUnitNode(null, IntType,"n"));
        FuncDefNode PrintInt = new FuncDefNode(null, VoidType, "printInt", PrintIntList);

        ParameterListNode PrintlnIntList = new ParameterListNode(null);
        PrintlnIntList.varList.add(new VarDefUnitNode(null,IntType, "n"));
        FuncDefNode PrintlnInt = new FuncDefNode(null, VoidType, "printlnInt", PrintlnIntList);

        FuncDefNode GetString = new FuncDefNode(null, StringType, "getString", null);

        FuncDefNode GetInt = new FuncDefNode(null, IntType, "getInt", null);

        ParameterListNode ToStringList = new ParameterListNode(null);
        ToStringList.varList.add(new VarDefUnitNode(null, IntType,"i"));
        FuncDefNode ToString = new FuncDefNode(null, StringType, "toString", ToStringList);

        functionMembers.put("size", ArrayInit);
        functionMembers.put("print", Print);
        functionMembers.put("println", Println);
        functionMembers.put("printInt", PrintInt);
        functionMembers.put("printlnInt", PrintlnInt);
        functionMembers.put("getString", GetString);
        functionMembers.put("getInt", GetInt);
        functionMembers.put("toString", ToString);
    }

    public globalScope(Scope parentScope){
        super(parentScope);
    }

    public void add_type(Type type, position pos){
        if (types.containsKey(type.typeName)) throw new semanticError("Class Name Exists", pos);
        types.put(type.typeName, type);
    }

    public Type getType(String name, position pos){
        if (!types.containsKey(name)) throw new semanticError("Invalid Type", pos);
        return types.get(name);
    }

    public FuncDefNode get_func(String name){
        return functionMembers.get(name);
    }

    public boolean haveType(String name){
        return types.containsKey(name);
    }
}
