package server;

import common.BinarySequencesHelper;
import common.PrivateKey;
import common.PubKey;
import javafx.util.Pair;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by rage on 23.05.15.
 */

public class GenerateKeys {

    private static final int AMOUNT_BASIC_NUM = 5;//10000;

    private static Pair<PrivateKey, PubKey> generate(int n) {

        List<String> listBasicNums = new ArrayList<String>();
        BigInteger sumOfBasic = BigInteger.ZERO;
        Map<String, Integer> stepForBasic = new HashMap<String, Integer>();
        BigInteger sizeGraph = BinarySequencesHelper.getSizeGraph(n);
        Random random = new Random();
        for (int i = 0; i < AMOUNT_BASIC_NUM; i++) {
            BigInteger basicNum = BinarySequencesHelper.rndBigInt(n);
            String withLeadingZero = BinarySequencesHelper.makeLeadingZero(basicNum.toString(2), n);
            listBasicNums.add(withLeadingZero);
            //todo rewrite it
//            int step = random.nextInt(10000) + 10000;
            int step = random.nextInt(100) + 100;
            stepForBasic.put(withLeadingZero, step);
            basicNum = BinarySequencesHelper.fewOperatorA(basicNum, sizeGraph, n, step);
            sumOfBasic = sumOfBasic.xor(basicNum);
        }

        String controlSum = BinarySequencesHelper.makeLeadingZero(sumOfBasic.toString(2), n);
        PubKey pubKey = new PubKey(listBasicNums, controlSum);
        PrivateKey privateKey = new PrivateKey(stepForBasic);

        return new Pair<PrivateKey, PubKey>(privateKey, pubKey);

    }


    public static void makeKeys() {
        try {
            long t1 = System.currentTimeMillis();
            PrintWriter outPublic = new PrintWriter("publicKey.txt");
            PrintWriter outPrivate = new PrintWriter("privateKey.txt");

            Pair<PrivateKey, PubKey> generate = GenerateKeys.generate(10);

            outPublic.println(generate.getValue().outputInFile());
            outPrivate.println(generate.getKey().outputInFile());
            outPublic.close();
            outPrivate.close();
            long t2 = System.currentTimeMillis();
            System.err.println("Generate time = " + (t2 - t1));
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }
}
