package kiea.priv.zzz.book.JavaDesignPattern.pattern.P18_Memento.t01;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		Gamer gamer = new Gamer(100);  // 처음의 돈은 100
		Memento memento = gamer.createMemento();
		
		for (int i=0; i < 100; i++) {
			System.out.println("===== " + i);
			System.out.println("현 상태:" + gamer);
			
			gamer.bet();
			
			System.out.println("돈은 " + gamer.getMoney() + "원이 되었습니다.");
			
			// Memento의 취급 결정
			if (gamer.getMoney() > memento.getMoney()) {
				System.out.println("     (많이 증가했으니 현재의 상태를 보존해두자)");
				memento = gamer.createMemento();
			} else if (gamer.getMoney() < memento.getMoney() / 2) {
				System.out.println("     (많이 줄었으니 이전의 상태로 복귀하자)");
				gamer.restoreMemento(memento);
			}
			
			// 기다림.
			try { Thread.sleep(1000); } catch (InterruptedException e) {}
			
			System.out.println();
		}
	}
}
