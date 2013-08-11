package Types;

public class RECORD extends Type {
   public Symbo.Symbol fieldName;
   public Type fieldType;
   public RECORD tail;
   public RECORD(Symbo.Symbol n, Type t, RECORD x) {
       fieldName=n; fieldType=t; tail=x;
   }
   public boolean coerceTo(Type t) {
	return this==t.actual();
   }
}
   

