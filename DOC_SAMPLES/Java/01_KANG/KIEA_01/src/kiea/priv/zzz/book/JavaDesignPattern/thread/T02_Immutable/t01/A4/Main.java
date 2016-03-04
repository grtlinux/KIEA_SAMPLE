package kiea.priv.zzz.book.JavaDesignPattern.thread.T02_Immutable.t01.A4;

public class Main {
    public static void main(String[] args) {
        // 
        UserInfo userinfo = new UserInfo("Alice", "Alaska");

        // 
        System.out.println("userinfo = " + userinfo);

        // 
        StringBuffer info = userinfo.getInfo();
        info.replace(12, 17, "Bobby");  // 

        // 
        System.out.println("userinfo = " + userinfo);
    }
}
