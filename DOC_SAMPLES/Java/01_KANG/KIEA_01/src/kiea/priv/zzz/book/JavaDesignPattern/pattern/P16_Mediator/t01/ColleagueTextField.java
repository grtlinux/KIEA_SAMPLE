package kiea.priv.zzz.book.JavaDesignPattern.pattern.P16_Mediator.t01;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class ColleagueTextField extends TextField implements TextListener, Colleague
{
	private static final long serialVersionUID = 1L;

	private Mediator mediator;
	
	public ColleagueTextField(String text, int columns)
	{
		super(text, columns);
	}
	
	public void setMediator(Mediator mediator)
	{
		this.mediator = mediator;
	}
	
	public void setColleagueEnabled(boolean enabled)
	{
		setEnabled(enabled);
		setBackground(enabled ? Color.WHITE : Color.LIGHT_GRAY);
	}
	
	public void textValueChanged(TextEvent e)
	{
		mediator.colleagueChanged(this);
	}
}
