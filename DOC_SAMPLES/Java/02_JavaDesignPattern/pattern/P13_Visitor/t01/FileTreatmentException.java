package kiea.priv.zzz.book.JavaDesignPattern.pattern.P13_Visitor.t01;

public class FileTreatmentException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public FileTreatmentException() {}
	
	public FileTreatmentException(String msg)
	{
		super(msg);
	}
}
