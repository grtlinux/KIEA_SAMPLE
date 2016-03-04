package kiea.priv.zzz.book.JavaDesignPattern.pattern.P16_Mediator.t01;

import java.awt.Button;

public class ColleagueButton extends Button implements Colleague
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Mediator mediator;
	
	public ColleagueButton(String caption)
	{
		super(caption);
	}
	
	public void setMediator(Mediator mediator)
	{
		this.mediator = mediator;
	}
	
	public void setColleagueEnabled(boolean enabled)
	{
		setEnabled(enabled);
	}
}
