package kiea.priv.zzz.book.JavaDesignPattern.pattern.P07_Builder.t02;

public abstract class Builder
{
	private boolean initialized = false;
	
	public void makeTitle(String title)
	{
		if (!this.initialized) {
			buildTitle(title);
			this.initialized = true;
		}
	}
	
	public void makeContent(String string)
	{
		if (this.initialized) {
			buildContent(string);
		}
	}
	
	public void makeItems(String[] arrStr)
	{
		if (this.initialized) {
			buildItems(arrStr);
		}
	}
	
	public String getResult()
	{
		if (this.initialized) {
			return buildResult();
		} else {
			return null;
		}
	}
	
	public abstract void buildTitle(String string);
	public abstract void buildContent(String string);
	public abstract void buildItems(String[] arrStr);
	public abstract String buildResult();
}
