package parse;

public class Parse {

  public errormsg.ErrorMsg errorMsg;

  public Parse(String filename) {
       errorMsg = new errormsg.ErrorMsg(filename);
       java.io.InputStream inp;
       try {inp=new java.io.FileInputStream(filename);
       } catch (java.io.FileNotFoundException e) {
	 throw new Error("File not found: " + filename);
       }
       parser parser = new parser(new JYTiger(inp,errorMsg), errorMsg);

      try {
          Absyn.Exp exp = (Absyn.Exp)parser./*debug_*/parse().value;
          new Absyn.Print(System.out).prExp(exp, 1);
      } catch (Throwable e) {
    	  //e.printStackTrace();
		throw new Error(e.toString());
      } 
      finally {
         try {inp.close();} catch (java.io.IOException e) {}
      }
  }
}
   

