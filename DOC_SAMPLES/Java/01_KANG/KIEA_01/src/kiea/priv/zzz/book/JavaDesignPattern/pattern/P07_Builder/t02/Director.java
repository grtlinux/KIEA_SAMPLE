package kiea.priv.zzz.book.JavaDesignPattern.pattern.P07_Builder.t02;

public class Director
{
	private Builder builder;
	
	public Director(Builder builder)
	{
		this.builder = builder;
	}
	
	public String construct()
	{
		builder.makeTitle("아침과 낮에");
		builder.makeContent("안녕히 주무셨습니까?");
		builder.makeItems(new String[] {
				"좋은 아침입니다.",
				"안녕히 주무셨습니까!",
		});
		return builder.getResult();
	}
}
