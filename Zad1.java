import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Zad1
{
	List<String> slowa = new ArrayList<String>();
	List<String> nowe = new ArrayList<String>();

	public void openFile() throws Exception
	{
		String source = "path";
		String source1 = "path";
		Scanner scan = new Scanner(new FileReader(source));
		Scanner scan1 = new Scanner(new FileReader(source1));

		while (scan.hasNext())
			slowa.add(scan.next());
		while (scan1.hasNext())
			nowe.add(scan1.next());
		scan.close();
		scan1.close();
	}

	public int[] ex1()
	{
		int[] times = new int[12];

		for (String s : slowa)
			for (int i = 0; i < times.length; i++)
				if (s.length() == (i + 1))
					times[i] += 1;
		return times;
	}

	public Map<String, List<Integer>> ex2()
	{
		Map<String, List<Integer>> result = new LinkedHashMap<String, List<Integer>>();

		for (String s : nowe)
			result.put(s, Arrays.asList(0, 0));

		for (String s : slowa)
		{
			if (nowe.contains(s))
			{
				int i = result.get(s).get(0);
				int j = result.get(s).get(1);
				i++;
				result.replace(s, Arrays.asList(i, j));
			}
			if (nowe.contains(invertString(s)))
			{
				int i = result.get(invertString(s)).get(0);
				int j = result.get(invertString(s)).get(1);
				j++;
				result.replace(invertString(s), Arrays.asList(i, j));
			}
		}
		return result;
	}

	public String invertString(String s)
	{
		StringBuilder des = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--)
			des.append(s.charAt(i));
		return des.toString();
	}

	public void saveFile() throws Exception
	{
		String output = "path";
		PrintWriter save = new PrintWriter(output);

		for (int i = 0; i < ex1().length; i++)
			save.println((i + 1) + " = " + ex1()[i]);

		for (String s : ex2().keySet())
		{
			save.print(s + " ");
			for (int i : ex2().get(s))
				save.print(i + " ");
			save.println("");
		}

		save.close();
	}

	public static void main(String[] Args) throws Exception
	{
		Zad1 z = new Zad1();
		z.openFile();
		z.saveFile();
	}
}
