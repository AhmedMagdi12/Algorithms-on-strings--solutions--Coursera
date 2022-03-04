import java.util.*;
import java.io.*;
import java.util.zip.CheckedInputStream;

public class SuffixArrayLong {
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
    public int[] computeSuffixArray(String text) {
        int[] result = new int[text.length()];
        int[] order = sort(text);

        int[] clas = computeClass(text, order);

        int l = 1;
        while (l < text.length()){
            order = sortDoubled(text, l, order, clas);
            clas = updateClasses(order, clas, l);
            l = 2*l;
        }
        // write your code here

        return order;
    }
    public int conv(char c){
        if(c == '$')
            return 0;
        if(c == 'A')
            return 1;
        if(c == 'C')
            return 2;
        if(c == 'G')
            return 3;
        if(c == 'T')
            return 4;
        return 0;
    }
    public int[] sort(String s) {
        // write your code here
        //
        int[] order = new int[s.length()];
        int[] count = new int[5];
        for(int i = 0; i <s.length();i++){
            char c = s.charAt(i);
            count[conv(c)] = count[conv(c)] + 1;
        }

        for(int j = 1; j <count.length;j++){
            count[j] = count[j] + count[j-1];
        }
        for(int i = s.length()-1; i >=0;i--){
            char c = s.charAt(i);
            count[conv(c)]-=1;
            order[count[conv(c)]] = i;
        }
        return order;
    }
    public int[] computeClass(String s , int[] order){
        int[] charClass =  new int[s.length()];
        charClass[order[0]] = 0;
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(order[i]) != s.charAt(order[i-1]))
                charClass[order[i]] = charClass[order[i-1]] + 1;
            else
                charClass[order[i]] = charClass[order[i-1]];
        }

        return charClass;
    }
    public int[] sortDoubled(String s , int l , int[] order, int[] clas) {

    int count[] = new int[s.length()];
    int newOrder[] = new int[s.length()];
    for(int i = 0; i <s.length();i++){
        count[clas[i]] = count[clas[i]] + 1;
    }
    for(int j = 1; j <s.length();j++){
        count[j] = count[j] + count[j-1];
    }
    for(int i = s.length() - 1; i >= 0;i--){
        int start = (order[i] - l + s.length()) % s.length();
        int cl = clas[start];
        count[cl] = count[cl] - 1;
        //System.out.println(count[cl]);
        newOrder[count[cl]] = start;
    }

    return newOrder;
    }
    public int[] updateClasses(int[] newOrder , int [] clas,int l){
        int n = newOrder.length;
        int newClass[] = new int[n];
        newClass[newOrder[0]] = 0;
        for(int i = 1; i < n ; i++){
            int current = newOrder[i];
            int prev = newOrder[i-1];
            int mid = current+l;
            int midPrev = (prev + l) % n;
            if((clas[current] != clas[prev]) || (clas[mid] != clas[midPrev])){
                newClass[current] = newClass[prev] + 1;

            } else {
                newClass[current] =  newClass[prev];
            }

        }
        return newClass;
    }

    static public void main(String[] args) throws IOException {
        new SuffixArrayLong().run();
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
        int[] suffix_array = computeSuffixArray(text);
        print(suffix_array);
    }
}
