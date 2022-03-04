import java.io.*;
import java.util.*;

class Node
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];
	public boolean patternEnd;

	Node ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
		patternEnd = false;
	}
}

public class TrieMatchingExtended implements Runnable {
	static int letterToIndex (char letter)
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
        static void buildTree(List <String> patterns , ArrayList<Node> arr){
            arr.add(new Node());

            for(int i = 0; i < patterns.size(); i++){


                int current_node = 0;
                String pattern = patterns.get(i);
                for(int j = 0; j < pattern.length(); j++){

                    int index = letterToIndex(pattern.charAt(j));

                    if(arr.get(current_node).next[index] != -1)
                        current_node = arr.get(current_node).next[index];
                    else {

                        arr.get(current_node).next[index] = arr.size();
                        current_node = arr.get(current_node).next[index];
                        arr.add(new Node());
                    }
                    /*
                    if(j == pattern.length() - 1 )
                        arr.get(current_node).patternEnd=true;
                    */
                }
                arr.get(current_node).patternEnd = true;
                                    /*
                    int nextNode = arr.get(current_node).next[index];
                    if(nextNode != -1){
                        if(j == (patterns.get(i).length()-1)){
                            arr.get(nextNode).patternEnd = true;
                        }else {
                            current_node = nextNode;
                        }
                    }else {
                        arr.get(current_node).next[current_node] = ++index;
                        Node temp = new Node();
                        if(j == (patterns.get(i).length() - 1 )){
                            temp.patternEnd = true;
                        }
                        arr.add(temp);
                        current_node = index;
                    }*/

            }

        }

	List <Integer> solve (String text, int n, List <String> patterns) {


		List <Integer> result = new ArrayList <Integer> ();

		// write your code here
                for(String pattern : patterns)
                    pattern = pattern+"$";

                ArrayList<Node> arr = new ArrayList<>();
                buildTree(patterns , arr);

                for(int i = 0; i < text.length(); i++){
                    // START FROM THE I'th LETTER in the text (INDEX) AND THE ROOT NODE (X)
                    int index = i;
                    int x = 0;
                    while(index < text.length()) {
                        char ch = text.charAt(index);
                        if(arr.get(x).next[letterToIndex(ch)] == -1)
                            break;
                        x = arr.get(x).next[letterToIndex(ch)];
                        if(arr.get(x).patternEnd){
                            result.add(i);
                            break;
                        }
                        index +=1;
                    }
                    /*
                    if(arr.get(x).next[index] != -1){

                        boolean found = true;
                        for(int j = 0; arr.get(x).patternEnd == false ; j++){

                            if(j >= text.length()){
                                found = false;
                                break;
                            }
                            index = letterToIndex(text.charAt(j));
                            if(arr.get(x).next[index] != -1)
                                x = arr.get(x).next[index];
                            else {
                                found = false;
                                break;
                            }
                        }
                        if(found){
                            result.add(i);
                        }
                    }*/

                        }
		return result;
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

	public static void main (String [] args) {
		new Thread (new TrieMatchingExtended ()).start ();
	}
}
