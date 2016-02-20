import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Zad2
{
	List<String> list = new ArrayList<String>();
	int answer1;
	int answer2;
	List<String> answer = new ArrayList<String>();
	List<Integer> answerr = new ArrayList<Integer>();

	public void readFile() throws Exception
	{
		String source = "Cpath";
		Scanner scan = new Scanner(new FileReader(source));

		while (scan.hasNext())
			list.add(scan.next());

		scan.close();
	}

	public void ex1()
	{
		int res = 0;

		for (String s : list)
		{
			int i = 0;
			int j = 0;
			char[] array = s.toCharArray();
			for (char c : array)
			{
				if (c == '0')
					i++;
				if (c == '1')
					j++;
			}
			if (i > j)
				res++;
		}
		answer1 = res;
	}

	public void ex2()
	{
		int res = 0;

		for (String s : list)
		{
			char[] ch = s.toCharArray();
			boolean breaked = false;

			if (ch[0] == '0')
			{
				boolean was1 = false;
				for (char c : ch)
				{
					if (c == '1')
						was1 = true;
					if (!was1 && (c != '0'))
						breaked = true;
					if (was1 && (c != '1'))
						breaked = true;
					if (ch[ch.length - 1] != '1')
						breaked = true;
				}
			}
			else
				breaked = true;

			if (!breaked)
				res++;
		}
		answer2 = res;
	}

	public void ex3()
	{
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();

		for (String s : list)
		{
			int zeros = 0;
			char[] ch = s.toCharArray();
			List<Integer> list1 = new ArrayList<Integer>();
			for (char c : ch)
			{
				if (c == '0')
					zeros++;
				if (c == '1')
				{
					list1.add(zeros);
					zeros = 0;
				}
			}
			list1.add(zeros);
			if (list1.size() >= 1)
				map.put(s, Collections.max(list1));
			list1.clear();
		}

		int i = 0;
		for (String s : map.keySet())
			if (map.get(s) > i)
				i = map.get(s);

		for (String s : map.keySet())
			if (map.get(s) == i)
			{
				answer.add(s);
				answerr.add(map.get(s));
			}
	}

	public void saveFile() throws Exception
	{
		String output = "path";
		PrintWriter save = new PrintWriter(output);

		save.println(answer1);
		save.println(answer2);

		for (int i = 0; i < answer.size(); i++)
			save.println(answer.get(i) + " " + answerr.get(i));

		save.close();
	}

	public static void main(String[] Args) throws Exception
	{
		Zad2 e = new Zad2();
		e.readFile();
		e.ex1();
		e.ex2();
		e.ex3();
		e.saveFile();
	}
}
