package server;

import common.BinarySequencesHelper;
import common.PrivateKey;
import common.PubKey;
import javafx.util.Pair;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by rage on 23.05.15.
 */

public class GenerateKeys {

    private static final int AMOUNT_BASIC_NUM = 5;//10000;

    public static Pair<PrivateKey, PubKey> generate(int n) {

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


}
