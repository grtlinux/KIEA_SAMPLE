package kiea.priv.zzz.book.CookBook.t01.demo.com.darwinsys.sql;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultsDecoratorHTML extends ResultsDecorator
{
    public ResultsDecoratorHTML(PrintWriter out, Verbosity v) {
        super(out, v);
    }
    
    public int write(ResultSet rs) throws IOException, SQLException
    {
        ResultSetMetaData md = rs.getMetaData();
        int colCount = md.getColumnCount();
        println("<table border=1>");
        print("<tr>");
        for (int i=1; i<=colCount; i++) {
            print("<th>");
            print(md.getColumnLabel(i));
        }
        println("</tr>");
        int rowCount = 0;
        while (rs.next()) {
            ++rowCount;
            print("<tr>");
            for (int i=1; i<=colCount; i++) {
                print("<td>");
                print(rs.getString(i));
            }
            println("</tr>");
        }
        println("</table>");
        return rowCount;
    }

    @Override
    public void displayTable(String table, ResultSet rs) throws IOException, SQLException {
        write(rs);
    }

    /** Return a printable name for this decorator
     * @see ResultsDecorator#getName()
     */
    public String getName() {
        return "HTML";
    }
}
