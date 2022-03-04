/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;

class Node
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];

	Node ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
	}
}

public class TrieMatching implements Runnable {
	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: assert (false); return Node.NA;
		}
	}

	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();

		// write your code here
                String[] lst = new String[patterns.size()];
                for(int i = 0; i < patterns.size(); i++)
                {
                lst[i] = patterns.get(i);

                }
                text = text +"$";
                List<Map<Character, Integer>> trie = buildTrie(lst);

                for(int current_char = 0 ; current_char < text.length(); current_char++)
                {
                    int current_vertex = 0;
                    char symbol = text.charAt(current_char);
                    int count = current_char;
                    while (true){
                        if(trie.get(current_vertex).size()==0 ){
                            result.add(current_char);
                            break;
                        }
                        else if(trie.get(current_vertex).containsKey(symbol)){
                            current_vertex = trie.get(current_vertex).get(symbol);

                            if(count < text.length()-1){
                                count++;
                                symbol=text.charAt(count);
                            }


                        }
                        else{
                            break;
                        }
                    }


                }
		return result;
	}
            public void print(List<Map<Character, Integer>> trie) {
        for (int i = 0; i < trie.size(); ++i) {
            Map<Character, Integer> node = trie.get(i);
            for (Map.Entry<Character, Integer> entry : node.entrySet()) {
                System.out.println(i + "->" + entry.getValue() + ":" + entry.getKey());
            }
        }
    }


	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	List <String> patterns = new ArrayList <String> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
			}

			List <Integer> ans = solve (text, n, patterns);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}


        List<Map<Character, Integer>> buildTrie(String[] patterns) {

        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();


        // write your code here
        trie.add(0,new HashMap<Character,Integer>());
        // next to specify the next node number
        int next = 0;
        for(String s : patterns){

            char str[] = s.toCharArray();
            int current = 0;
            for(int i = 0 ; i < s.length() ; i++){

                char ch = str[i];
                if(trie.get(current).containsKey(ch)){
                    current = trie.get(current).get(ch);
                }
                else{
                    trie.add(next+1,new HashMap<Character,Integer>());
                    trie.get(current).put(ch, next+1);
                    next+=1;
                    current = trie.get(current).get(ch);

                }
            }
        }
        return trie;
    }

	public static void main (String [] args) {
		new Thread (new TrieMatching ()).start ();
	}
}
