package kiea.priv.zzz.book.JavaDesignPattern.pattern.P22_Command.t01;

import java.util.Stack;

public class MacroCommand implements Command
{
	private Stack<Command> commands = new Stack<Command>();
	
	public void execute()
	{
		for (Command cmd : this.commands) {
			cmd.execute();
		}
	}
	
	public void append(Command cmd)
	{
		if (cmd != null) {
			this.commands.push(cmd);
		}
	}
	
	public void undo()
	{
		if (!this.commands.empty()) {
			this.commands.pop();
		}
	}
	
	public void clear()
	{
		this.commands.clear();
	}
}
