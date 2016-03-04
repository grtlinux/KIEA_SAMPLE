package kiea.priv.zzz.book.JavaDesignPattern.pattern.P23_Interpreter.t01;

/**
 * 
 * @author KangSeok
 *
 * <repeat command> ::= repeat <number> <command list>
 */
public class RepeatCommandNode extends Node
{
	private int number;
	private Node commandListNode;
	
	public void parse(Context context) throws ParseException
	{
		context.skipToken("repeat");
		number = context.currentNumber();
		context.nextToken();
		commandListNode = new CommandListNode();
		commandListNode.parse(context);
	}
	
	public String toString()
	{
		return "[repeat " + number + " " + commandListNode + "]";
	}
}
