package common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rage on 23.05.15.
 */
public class BinarySequencesHelper {

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
    public static BigInteger fewOperatorA(BigInteger vertex, BigInteger sizeGraph, int n, int k) {
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
     * @param k         - k
     * @return G^k(x) = y
     */

    public static BigInteger operatorG(BigInteger vertex, BigInteger sizeGraph, int k) {
        return vertex.shiftLeft(k).mod(sizeGraph.subtract(BigInteger.ONE));
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
            bigIntegers.add(fewOperatorA(vertex, sizeGraph, n, i));
        }
        BigInteger ans = BigInteger.ZERO;
        for (BigInteger bigInteger : bigIntegers) {
            ans = ans.xor(bigInteger);
        }
        return ans;
    }

}
