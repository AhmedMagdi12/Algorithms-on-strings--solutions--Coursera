import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;
//import javafx.util.Pair;

public class InverseBWT {

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
    static class pair {
        int i;
        char c;

        public pair(char c , Integer i){
            this.c=c;
            this.i=i;
        }
        public char key(){ return c;}
        public int value(){ return i;}

    }
    static String inverseBWT(String bwt) {
        StringBuilder result = new StringBuilder();


        //String result ="";


        // last column
        List<pair> l = new ArrayList<>();
        for(int i = 0; i < bwt.length(); i++){
            l.add(new pair(bwt.charAt(i),i));
        }

        // first column
        List<pair> f = new ArrayList<>();
        f=l;
        // sort first column
        f.sort(new Comparator<pair>() {

            @Override
            public int compare(pair o1, pair o2) {
                return o1.key()<o2.key()?-1:1;//To change body of generated methods, choose Tools | Templates.
            }
        });
        // get the result
        pair p = f.get(0);
        for (int i = 0; i < bwt.length(); i++) {
            p = f.get(p.value());
            result.append(p.key());
        }
        return result.toString();

    } 

    static public void main(String[] args) throws IOException {
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}
