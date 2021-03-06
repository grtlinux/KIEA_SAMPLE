package kiea.priv.zzz.book.CookBook.t01.demo.com.darwinsys.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVRE implements CSVParser
{
	/** The rather involved pattern used to match CSV's consists of three
	 * alternations: the first matches a quoted field, the second unquoted,
	 * the third a null field.
	 */
	public static final String CSV_PATTERN =
		"\"([^\"]+?)\",?|([^,]+),?|,";

	private final static Pattern csvRE = Pattern.compile(CSV_PATTERN);

	public static void main(final String[] argv) throws IOException {
		System.out.println(CSV_PATTERN);
		new CSVRE().process(
			new BufferedReader(new InputStreamReader(System.in)));
	}

	/** Process one file. Delegates to parse() a line at a time */
	public void process(final BufferedReader input) throws IOException {
		String line;

		// For each line...
		while ((line = input.readLine()) != null) {
			System.out.println("line = `" + line + "'");
			final List<String> list = parse(line);
			System.out.println("Found " + list.size() + " items.");
			for (String str : list) {
				System.out.print(str + ",");
			}
			System.out.println();
		}
	}

	/** Parse one line.
	 * @return List of Strings, minus their double quotes
	 */
	public List<String> parse(final String line) {
		final List<String> list = new ArrayList<>();
		final Matcher m = csvRE.matcher(line);
		// For each field
		while (m.find()) {
			String match = m.group();
			if (match == null) {
				break;
			}
			if (match.endsWith(",")) {    // trim trailing ,
				match = match.substring(0, match.length() - 1);
			}
			if (match.startsWith("\"")) { // must also end with \"
				if (!match.endsWith("\"")) {
					throw new IllegalArgumentException(
						"Quoted column missing end quote: " + line);
				}
				match = match.substring(1, match.length() - 1);
			}
			if (match.length() == 0) {
				match = "";
			}
			list.add(match);
		}
		return list;
	}
}
