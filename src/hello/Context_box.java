package hello;
import java.util.*;
import java.io.*;

public class Context_box {
	private HashMap<String,Boolean> hm = new HashMap<String,Boolean>();
	private HashMap<String,ArrayList< Pair<Integer,ArrayList<Integer> > > > hm1 = new HashMap<String,ArrayList< Pair<Integer,ArrayList<Integer> > > > ();
	private HashMap<Integer,String> hm2 = new HashMap<Integer,String>();
	public Context_box()
	{
		try
		{
			
			FileReader in = new FileReader("/home/abhi/Desktop/stopwords.txt");
			BufferedReader br = new BufferedReader(in);
			String line = "";
			while((line = br.readLine()) != null )
			{
				//System.out.println(line);
				hm.put(line,true);
			}
			int count = 0;
			in = new FileReader("/home/abhi/workspace1/hello/WebContent/text_files.txt");
			br = new BufferedReader(in);
			while((line = br.readLine()) != null )
			{
				
				count++;
				hm2.put(count, line);
				FileReader in1 = new FileReader("/home/abhi/workspace1/hello/WebContent/"+line);
				BufferedReader br1 = new BufferedReader(in1);
				String line1 = "";
				int count1 = 0;
				while((line1 = br1.readLine()) != null )
				{
					String tokens[] = line1.split(" ");
					int size = tokens.length;

					//System.out.println(size);
					for(int i=0; i < size; i++)
					{
						

						if(hm.containsKey(tokens[i]) == false )
						{
							/* we are only incrementing count word is not a stopword*/
							count1++;
							if(hm1.containsKey(tokens[i]) == false)
							{
								hm1.put(tokens[i],new  ArrayList< Pair<Integer,ArrayList<Integer> > >());
								Pair<Integer,ArrayList<Integer> > p = new Pair<Integer,ArrayList<Integer> >();
								p.setFirst(count);
								ArrayList<Integer> as = new ArrayList<Integer>();
								p.setSecond(as);
								p.getSecond().add(count1);
								hm1.get(tokens[i]).add(p);
							}
							else
							{
								ArrayList< Pair<Integer,ArrayList<Integer> > > pp = hm1.get(tokens[i]);
								int flag = 0;
								for(Pair<Integer,ArrayList<Integer> > ppp : pp)
								{
									if(ppp.getFirst() == count)
									{
										ppp.getSecond().add(count1);
										flag = 1;
										break;
									}
								}
								if(flag == 0)
								{
									Pair<Integer,ArrayList<Integer> > p = new Pair<Integer,ArrayList<Integer> >();
									p.setFirst(count);
									ArrayList<Integer> as = new ArrayList<Integer>();
									p.setSecond(as);
									p.getSecond().add(count1);
									pp.add(p);
								}	
							}
							
						}
						//System.out.println(tokens[i]);
					}
				}
				
			}
			
			System.out.println(count);

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	ArrayList<String> search(String str) 
	{
		ArrayList<String> As = new ArrayList<String>();
		try
		{
			ArrayList< Pair<Integer,ArrayList<Integer> > > as = hm1.get(str);
			for(Pair<Integer,ArrayList<Integer> > o : as)
			{
				//System.out.println(o.getFirst());
				As.add("hello/"+hm2.get(o.getFirst()));
			}
		}
		catch(Exception e)
		{
			
		}
		return As;
	}
	int index(ArrayList<Pair<Integer,ArrayList<Integer> > > as,int key)
	{
		int r = as.size()-1;
		int l = 0;
		int ans = -1;
		while(l <= r)
		{
			int mid = (l+r) >> 1;
			int check = as.get(mid).getFirst();
			if(check == key)
			{
				ans = mid;
				break;
			}
			if(key > check)
				l = mid+1;
			else
				r = mid-1;
		}
		return ans;
	}
	int index1(ArrayList<Integer> as,int key)
	{
		int r = as.size()-1;
		int l = 0;
		int ans = -1;
		while(l <= r)
		{
			int mid = (l+r) >> 1;
			int check = as.get(mid);
			if(check == key)
			{
				ans = mid;
				break;
			}
			if(key > check)
				l = mid+1;
			else
				r = mid-1;
		}
		return ans;
	}
	ArrayList<String> search4(String str)
	{
		ArrayList<String> result = new ArrayList<String>();
		try
		{
			String words[] = str.split(" ");
			ArrayList<Pair<Integer,ArrayList<Integer> > > as1 = hm1.get(words[0]);
			ArrayList<Pair<Integer,ArrayList<Integer> > > as2 = hm1.get(words[1]);
			ArrayList<Pair<Integer,ArrayList<Integer> > > as3 = hm1.get(words[2]);
			ArrayList<Pair<Integer,ArrayList<Integer> > > as4 = hm1.get(words[3]);
			//System.out.println(as4.get(0).getFirst());
			for(Pair<Integer,ArrayList<Integer> > pp : as1)
			{
				int a2 = index(as2,pp.getFirst());
				int a3 = index(as3,pp.getFirst());
				int a4 = index(as4,pp.getFirst());
				
				if(a2 == -1 || a3 == -1 || a4 == -1)
					continue;
				for(int i : pp.getSecond())
				{
					int a22 = index1(as2.get(a2).getSecond(),i+1);
					int a33 = index1(as3.get(a3).getSecond(),i+2);
					int a44 = index1(as4.get(a4).getSecond(),i+3);
					if(a22 ==-1 || a33 == -1 || a44 == -1)
						continue;
					result.add("hello/"+hm2.get(pp.getFirst()));
					
				}
				
				
			}
		}
		catch(Exception e)
		{
			
		}
		return result;
	}
	ArrayList<String> search3(String str)
	{
		ArrayList<String> result = new ArrayList<String>();
		try
		{
			String words[] = str.split(" ");
			ArrayList<Pair<Integer,ArrayList<Integer> > > as1 = hm1.get(words[0]);
			ArrayList<Pair<Integer,ArrayList<Integer> > > as2 = hm1.get(words[1]);
			ArrayList<Pair<Integer,ArrayList<Integer> > > as3 = hm1.get(words[2]);
			//ArrayList<Pair<Integer,ArrayList<Integer> > > as4 = hm1.get(words[3]);
			//System.out.println(as4.get(0).getFirst());
			for(Pair<Integer,ArrayList<Integer> > pp : as1)
			{
				int a2 = index(as2,(int)pp.getFirst());
			//	System.out.print(hm2.get(pp.getFirst()));
				int a3 = index(as3,(int)pp.getFirst());
			//	System.out.println(" "+a2+" "+a3);
				if(a2 == -1 || a3 == -1)
					continue;
				//System.out.println(hm2.get(pp.getFirst()));
				for(int i : pp.getSecond())
				{
					int a22 = index1(as2.get(a2).getSecond(),i+1);
					int a33 = index1(as3.get(a3).getSecond(),i+2);
				//	int a44 = index1(as4.get(a4).getSecond(),i+3);
					if(a22 ==-1 || a33 == -1)
						continue;
					result.add("hello/"+hm2.get((int)pp.getFirst()));
					
				}
				
				
			}
		}
		catch(Exception e)
		{
			
		}
		return result;
	}
	
	ArrayList<String> search2(String str)
	{
		String tokens[] = str.split(" ");
		int size = tokens.length;
		//System.out.println(tokens[1]);
		ArrayList<String> result = new ArrayList<String>();
		ArrayList< Pair<Integer,ArrayList<Integer> > > as1 = hm1.get(tokens[0]);
		ArrayList< Pair<Integer,ArrayList<Integer> > > as2 = hm1.get(tokens[1]);
		//System.out.println(as1.size() + " " + as2.size());
		for(Pair<Integer,ArrayList<Integer> > pp1 : as1)
		{
			ArrayList<Integer> ass1 = pp1.getSecond();
			//System.out.println(pp1.getFirst());
			for(Integer ppp1 : ass1)
			{
				//System.out.print(ppp1+" ");
				for(Pair<Integer,ArrayList<Integer> > pp2 : as2)
				{
					//System.out.println(pp2.getFirst()+ " " + pp1.getFirst());
					if(pp1.getFirst().hashCode() == pp2.getFirst().hashCode())
					{
				//		System.out.println("yoyoyb");
						ArrayList<Integer> ass2 = pp2.getSecond();
						for(Integer ppp2: ass2)
						{
							if(ppp1 == ppp2-1)
							{
								result.add("hello/"+hm2.get(pp1.getFirst()));
							}
						}
					}
				}
			}
		}
		return result;
	}
	ArrayList<String> mainsearch(String str)
	{
		ArrayList<String> result;
		String words[] = str.split(" ");
		//int len = words.length;
		String se = "";
		int len = 0;
		for(String s : words)
		{
			if(hm.containsKey(s) == false)
			{
				se += s+ " ";
				len++;
				
			}
		}
		if(se.length() != 0)
			se = se.substring(0, se.length()-1);
		System.out.println(len);
		//System.out.println(se.charAt(se.length()-1));
	//	int len = words.length;
		if(len == 0)
		{
			result = new ArrayList<String>();
			result.add("no result");
		}
		else if(len == 1)
			result = search(se);
		else if(len == 2)
			result = search2(se);
		else if(len == 3)
			result = search3(se);
		else
			result = search4(se);
		return result;
		
	}
	/*public static void main(String[] args)
	{
		Context_box c = new Context_box();
		System.out.println("uououo");
		ArrayList as = c.mainsearch("using namespace std;");
		for(Object o : as)
		{
			System.out.println((String)o);
		}
	}*/
}
