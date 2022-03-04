import java.util.*;
import java.io.*;
import java.util.Map.Entry;
import java.util.zip.CheckedInputStream;

public class SuffixArray {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class Suffix implements Comparable {
        String suffix;
        int start;

        Suffix(String suffix, int start) {
            this.suffix = suffix;
            this.start = start;
        }

        @Override
        public int compareTo(Object o) {
            Suffix other = (Suffix) o;
            return suffix.compareTo(other.suffix);
        }
    }

    // Build suffix array of the string text and
    // return an int[] result of the same length as the text
    // such that the value result[i] is the index (0-based)
    // in text where the i-th lexicographically smallest
    // suffix of text starts.
    static String leftrotate(String str, int d) {

        String ans = str.substring(d) + str.substring(0, d);
        return ans;
    }
    static String rightrotate(String str, int d)
    {
            return leftrotate(str, str.length() - d);
    }
    static class pair implements Comparable<pair>{

        String s;
        int index;


        public pair(String s , int i) {
            this.index=i;
            this.s=s;
        }

        @Override
        public int compareTo(pair o) {
            return this.s.compareTo(o.s);
        }

    }
    public SuffixArray() {
    }

    public int[] computeSuffixArray(String text) {
        // write your code here
        //HashMap<String, Integer> hash = new HashMap<>();


        //List<Entry<String,Integer>> list = new ArrayList<>();

        pair[] arr = new pair[text.length()];
        int[] result = new int[text.length()];
        //int count=text.length()-1;
        //int j = 1;
        for(int i = 0; i < text.length(); i++){
            //String s = rightrotate(text, i);
            //list.add(new AbstractMap.SimpleEntry<String, Integer>(s, count));
            arr[i] = new pair(text.substring(i), i);
            //System.out.println(s.substring(0, j));
            //hash.put(s,count);
            //result[i-1] =
            //arr[i-1]=s;
            /*
            arr[i] = leftrotate(text, i);
            result[i]=count;
            */
            /*
            if(j < text.length()-1){
            j++;
            }*/
            //count--;
        }
        //Set<Entry<String,Integer>> set = hash.entrySet();
        //set = (List<Entry<String, Integer>>) hash.entrySet();
        /*
        Collections.sort(list, new Comparator<Entry<String,Integer>>(){
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());//To change body of generated methods, choose Tools | Templates.
            }

        });
        for(int i = 0 ;i < text.length();i++){
            result[i] = list.get(i).getValue();
        }*/

        Arrays.sort(arr);
        for(int i = 0; i< text.length();i++){
            result[i] = arr[i].index;
        }

        //Collections.reverse(Arrays.asList(result));
        return result;
    }


    static public void main(String[] args) throws IOException {
        new SuffixArray().run();
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] SuffixArray = computeSuffixArray(text);
        print(SuffixArray);
    }
}
