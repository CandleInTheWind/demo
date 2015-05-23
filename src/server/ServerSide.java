package server;

import common.PrivateKey;
import common.PubKey;
import javafx.util.Pair;

import java.io.*;
import java.util.StringTokenizer;

public class ServerSide {

    //    final boolean ONLINE_JUDGE = System.getProperty("ONLINE_JUDGE") != null;
    BufferedReader in;
    PrintWriter outPublic;
    PrintWriter outPrivate;
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new ServerSide().run();
        // Sworn to fight and die
    }

    void init() throws FileNotFoundException {
//        if (ONLINE_JUDGE) {
        in = new BufferedReader(new InputStreamReader(System.in));
//            out = new PrintWriter(System.out);
//        } else {
//            in = new BufferedReader(new FileReader("input.txt"));
        outPublic = new PrintWriter("publicKey.txt");
        outPrivate = new PrintWriter("privateKey.txt");
//        }
    }

    int readInt() throws IOException {
        return Integer.parseInt(readString());
    }

    String readString() throws IOException {
        while (!tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }

    public void run() {
        try {
            long t1 = System.currentTimeMillis();
            init();
            solve();
            outPublic.close();
            outPrivate.close();
            long t2 = System.currentTimeMillis();
            System.err.println("Time = " + (t2 - t1));
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }

    public void solve() throws IOException {

        Pair<PrivateKey, PubKey> generate = GenerateKeys.generate(10);
        outPublic.println(generate.getValue().outputInFile());
        outPrivate.println(generate.getKey().outputInFile());

//        String sequences = readString();
//        int stepForH = readInt();
//        int n = sequences.length();
//        BigInteger sizeGraph = BinarySequencesHelper.getSizeGraph(n);
//        BigInteger sizeTree = BinarySequencesHelper.getSizeTree(n);
//        BigInteger vertex = new BigInteger(sequences, 2);
//        Deque<BigInteger> arrayDeque = new ArrayDeque<BigInteger>();
//        arrayDeque.add(vertex);
//        Set<BigInteger> set = new HashSet<BigInteger>();
//        set.add(vertex);
//        while (!arrayDeque.isEmpty()) {
//            vertex = arrayDeque.pollFirst();
//            for (int i = 1; i < stepForH; i++) {
//                BigInteger curVertex = BinarySequencesHelper.operatorH(vertex, sizeGraph, n, i);
//                if (!set.contains(curVertex)) {
//                    arrayDeque.add(curVertex);
//                    set.add(curVertex);
//                }
//            }
//        }
//        System.out.println("All vertex after operatorH is " + set.size());
    }
}
