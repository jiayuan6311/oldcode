package Semant;

import errormsg.ErrorMsg;

import Symbo.*;
public class Env {
    Table fenv;
    Table tenv;
    ErrorMsg errorMsg;
    Env(ErrorMsg err){
    	errorMsg=err;
		fenv = new Table();
		tenv = new Table();
		Symbol i,s;
		
		i=Symbol.symbol("int");
		s=Symbol.symbol("string");
		//Types.RECORD trec=null;
		//Label lab;
		//BoolList formals;
		tenv.put(i, new Types.INT());
		tenv.put(s, new Types.STRING());
		
		fenv.put(Symbol.symbol("print"), new Types.VOID());
		fenv.put(Symbol.symbol("printi"), new Types.VOID());
		fenv.put(Symbol.symbol("flush"), new Types.VOID());
		fenv.put(Symbol.symbol("getchar"), new Types.STRING());
		fenv.put(Symbol.symbol("ord"), new Types.INT());
		fenv.put(Symbol.symbol("chr"), new Types.STRING());
		fenv.put(Symbol.symbol("size"), new Types.INT());
		fenv.put(Symbol.symbol("substring"), new Types.STRING());
		fenv.put(Symbol.symbol("concat"), new Types.STRING());
		fenv.put(Symbol.symbol("not"), new Types.INT());
		fenv.put(Symbol.symbol("exit"), new Types.VOID());
		
    }
}



abstract class Entry{	
}

class VarEntry extends Entry{
	Types.Type ty;
	VarEntry (Types.Type t){ty=t;}
}

class FunEntry extends Entry{
	Types.RECORD formals;
	Types.Type ty;
	public FunEntry(Types.RECORD f,Types.Type t){formals=f;ty=t;}
}