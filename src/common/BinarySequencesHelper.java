package common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rage on 23.05.15.
 */
public class BinarySequencesHelper {

    private static final int RANDOM_STEP = 1000;
    private static final int MIN_RANDOM_STEP = 100;

    /**
     * Get amount vertex in graph
     *
     * @param n - length binary sequences
     * @return pow(2, n)
     */
    public static BigInteger getSizeGraph(int n) {
        return BigInteger.ONE.shiftLeft(n);
    }

    /**
     * Return size tree for particularly n
     *
     * @param n - length binary sequences
     * @return power two in n
     */
    public static BigInteger getSizeTree(int n) {
        int ans = 0;
        while (true) {
            if (n % 2 == 0) {
                ans++;
                n /= 2;
            } else {
                break;
            }
        }
        BigInteger bigAns = BigInteger.ONE;
        bigAns = bigAns.shiftLeft(1 << ans);
        return bigAns;
    }


    /**
     * BasicNum - it's value hash function which allows
     * define sequences in circle
     *
     * @param vertex   - selected vertex
     * @param sizeTree - size tree for n
     * @return next Vertex
     */

    public static BigInteger getBasicNum(BigInteger vertex, BigInteger sizeTree) {
        if (vertex.compareTo(sizeTree) < 0)
            return vertex;
        return (getBasicNum(vertex.divide(sizeTree), sizeTree)
                .xor((vertex.mod(sizeTree))));
    }


    /**
     * Operator A
     *
     * @param vertex    - particularly vertex for which we want to calculate the next value
     * @param sizeGraph - size tree for n
     * @param n         -- length binary sequences
     * @return A(x) = y  -- next Vertex
     */
    public static BigInteger operatorA(BigInteger vertex, BigInteger sizeGraph, int n) {
        if (vertex.testBit(n - 1)) {
            vertex = sizeGraph.subtract(BigInteger.ONE).subtract(vertex);
        }
        return vertex.xor(vertex.shiftLeft(1));
    }

    /**
     * A^k(x) = y
     *
     * @param vertex    - particularly vertex for which we want to calculate the next value
     * @param sizeGraph - size tree for n
     * @param n         -- length binary sequences
     * @param k         -- k how make A^k(x)
     * @return A^k(x) = y  -- new vertex
     */
    public static BigInteger operatorA(BigInteger vertex, BigInteger sizeGraph, int n, int k) {
        while (k-- > 0) {
            vertex = operatorA(vertex, sizeGraph, n);
        }
        return vertex;
    }


    /**
     * Operator G^k(x) = y
     *
     * @param vertex    - particularly vertex for which we want to calculate the next value
     * @param sizeGraph - size tree for n
     * @param n         -- length binary sequences
     * @param k         - k
     * @return G^k(x) = y
     */

    public static BigInteger operatorG(BigInteger vertex, BigInteger sizeGraph, int n, int k) {
        return vertex.shiftLeft(k % n).mod(sizeGraph.subtract(BigInteger.ONE));
    }


    /**
     * Operator H_r(x) = x XOR A(x) XOR A^2(x) XOR ... XOR ... A^r(x)
     *
     * @param vertex    - particularly vertex for which we want to calculate the next value
     * @param sizeGraph - size tree for n
     * @param n         -- length binary sequences
     * @param r         -- r for H
     * @return H_r(x) = y
     */

    public static BigInteger operatorH(BigInteger vertex, BigInteger sizeGraph, int n, int r) {
        List<BigInteger> bigIntegers = new ArrayList<BigInteger>();
        for (int i = 0; i < r; i++) {
            bigIntegers.add(operatorA(vertex, sizeGraph, n, i));
        }
        BigInteger ans = BigInteger.ZERO;
        for (BigInteger bigInteger : bigIntegers) {
            ans = ans.xor(bigInteger);
        }
        return ans;
    }


    public static String transformVertexByAllOperator(String basicNum, BigInteger sizeGraph, int n,
                                                      int bobStepForA,
                                                      int bobStepForG,
                                                      int bobStepForH) {
        BigInteger vertex = new BigInteger(basicNum, 2);
        vertex = BinarySequencesHelper.operatorA(vertex, sizeGraph, n, bobStepForA);
        vertex = BinarySequencesHelper.operatorG(vertex, sizeGraph, n, bobStepForG);
        vertex = BinarySequencesHelper.operatorH(vertex, sizeGraph, n, bobStepForH);
        return BinarySequencesHelper.makeLeadingZero(vertex.toString(2), n);
    }

    /**
     * Generate random bigInteger num with length n
     *
     * @param n - length - length binary sequences
     * @return 0 --> 2^n - 1
     */

    public static BigInteger rndBigInt(int n) {
        Random rnd = new Random();
        return new BigInteger(n, rnd);
    }

    /**
     * Make string for output with leading zero length n
     *
     * @param binaryNum - binary string without leading zerro
     * @param n         - - length binary sequences
     * @return String with leadingZero
     */

    public static String makeLeadingZero(String binaryNum, int n) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < n - binaryNum.length(); i++) {
            stringBuilder.append("0");
        }
        return stringBuilder.append(binaryNum).toString();
    }


    public static int stepRandom() {
        Random random = new Random();
        return stepRandom(random);
    }

    public static int stepRandom(Random random) {
        return Math.max(random.nextInt(RANDOM_STEP), MIN_RANDOM_STEP);
    }

}
