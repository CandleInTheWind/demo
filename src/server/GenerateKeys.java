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

    private static final int AMOUNT_BASIC_NUM = 100;

    private static final Map<PubKey, PrivateKey> MAP_PUB_PRIVATE = new HashMap<PubKey, PrivateKey>();

    private static Pair<PubKey, PrivateKey> generate(int n) {

        List<String> listBasicNums = new ArrayList<String>();
        BigInteger sumOfBasic = BigInteger.ZERO;
        Map<String, Integer> stepForBasic = new HashMap<String, Integer>();
        BigInteger sizeGraph = BinarySequencesHelper.getSizeGraph(n);
        Random random = new Random();
        for (int i = 0; i < AMOUNT_BASIC_NUM; i++) {
            BigInteger basicNum = BinarySequencesHelper.rndBigInt(n);
            String withLeadingZero = BinarySequencesHelper.makeLeadingZero(basicNum.toString(2), n);
            listBasicNums.add(withLeadingZero);
            int step = BinarySequencesHelper.stepRandom(random);
            stepForBasic.put(withLeadingZero, step);
            basicNum = BinarySequencesHelper.operatorA(basicNum, sizeGraph, n, step);
            sumOfBasic = sumOfBasic.xor(basicNum);
        }
        String controlSum = BinarySequencesHelper.makeLeadingZero(sumOfBasic.toString(2), n);
        PubKey pubKey = new PubKey(listBasicNums, controlSum);
        PrivateKey privateKey = new PrivateKey(stepForBasic);
        MAP_PUB_PRIVATE.put(pubKey, privateKey);
        return new Pair<PubKey, PrivateKey>(pubKey, privateKey);

    }


    public static void makeKeys() {
        try {
            long t1 = System.currentTimeMillis();
            PrintWriter outPublic = new PrintWriter("publicKey.txt");
            PrintWriter outPrivate = new PrintWriter("privateKey.txt");
            Pair<PubKey, PrivateKey> generate = GenerateKeys.generate(293);
            outPublic.println(generate.getKey().outputInFile());
            outPrivate.println(generate.getValue().outputInFile());
            outPublic.close();
            outPrivate.close();
            long t2 = System.currentTimeMillis();
            System.err.println("Verified time = " + (t2 - t1));
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }

    public static Map<PubKey, PrivateKey> getMapPubPrivate() {
        return MAP_PUB_PRIVATE;
    }
}
