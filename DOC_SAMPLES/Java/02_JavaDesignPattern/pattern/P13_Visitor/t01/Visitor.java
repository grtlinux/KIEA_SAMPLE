package kiea.priv.zzz.book.JavaDesignPattern.pattern.P13_Visitor.t01;

public abstract class Visitor
{
	public abstract void visit(File file);
	public abstract void visit(Directory directory);
}
