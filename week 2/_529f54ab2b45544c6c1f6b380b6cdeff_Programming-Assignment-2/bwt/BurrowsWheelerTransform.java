import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform {
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
    static String leftrotate(String str, int d)
    {
            String ans = str.substring(d) + str.substring(0, d);
            return ans;
    }

    // function that rotates s towards right by d
    /*
    static String rightrotate(String str, int d)
    {
            return leftrotate(str, str.length() - d);
    }
*/

    static String BWT(String text) {
        StringBuilder result = new StringBuilder();

        // write your code here
        String[] arr = new String[text.length()];
        for(int i = 0; i < arr.length; i++){

            arr[i] = leftrotate(text, i);
        }
        Arrays.sort(arr);

        for(String s : arr){
            result.append(s.charAt(s.length()-1));
        }

        return result.toString();
    }

    static public void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }
}
