package kiea.priv.zzz.book.JavaDesignPattern.pattern.P23_Interpreter.t01;

import java.util.Vector;

/**
 * 
 * @author KangSeok
 *
 * <command list> ::= <command>* end
 */
public class CommandListNode extends Node
{
	private Vector<Node> list = new Vector<Node>();
	
	public void parse(Context context) throws ParseException
	{
		while (true) {
			if (context.currentToken() == null) {
				throw new ParseException("Missing 'end'");
			} else if (context.currentToken().equals("end")) {
				context.skipToken("end");
				break;
			} else {
				Node commandNode = new CommandNode();
				commandNode.parse(context);
				list.add(commandNode);
			}
		}
	}
	
	public String toString()
	{
		return "" + list;
	}
}
