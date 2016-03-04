package kiea.priv.zzz.book.JavaDesignPattern.pattern.P11_Composite.t01;

public class FileTreatmentException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public FileTreatmentException() {}
	
	public FileTreatmentException(String msg)
	{
		super(msg);
	}
}
