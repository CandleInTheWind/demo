package server;

import common.BinarySequencesHelper;
import common.PrivateKey;
import common.PubKey;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rage on 24.05.15.
 */
public class VerifiedPubKey {

    private final int bobStepForA = BinarySequencesHelper.stepRandom();
    private final int bobStepForG = BinarySequencesHelper.stepRandom();
    private final int bobStepForH = BinarySequencesHelper.stepRandom();

    private static String alisaCountZ(PrivateKey privateKey, List<BobTransformation> transformationList, BigInteger sizeGraph, int n) {
        Map<String, Integer> stepForBasic = privateKey.getStepForBasic();
        BigInteger answer = BigInteger.ZERO;
        boolean calc = false;
        for (BobTransformation bobTransformation : transformationList) {
            Integer step = stepForBasic.get(bobTransformation.getBasicNum());
            if (step != null) {
                BigInteger vertex = new BigInteger(bobTransformation.getTransformBasicNum(), 2);
                vertex = BinarySequencesHelper.operatorA(vertex, sizeGraph, n, step);
                answer = answer.xor(vertex);
                calc = true;
            }
        }
        return calc ? BinarySequencesHelper.makeLeadingZero(answer.toString(2), n) : null;
    }

    public boolean verified(PubKey pubKey) {
        int n = pubKey.getControlSum().length();
        BigInteger sizeGraph = BinarySequencesHelper.getSizeGraph(n);
        List<BobTransformation> transformationList = bobTransformation(pubKey, sizeGraph, n);
        PrivateKey privateKey = GenerateKeys.getMapPubPrivate().get(pubKey);
        if (privateKey != null) {
            String alisaCountZ = alisaCountZ(privateKey, transformationList, sizeGraph, n);
            if (alisaCountZ != null) {
                System.out.println("alisaCountZ = " + alisaCountZ);
                String transformVertex = BinarySequencesHelper.transformVertexByAllOperator(pubKey.getControlSum(), sizeGraph, n,
                        bobStepForA, bobStepForG, bobStepForH);
                System.out.println("transformVertex = " + transformVertex);
                if (transformVertex.equals(alisaCountZ)) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<BobTransformation> bobTransformation(PubKey pubKey, BigInteger sizeGraph, int n) {
        List<BobTransformation> bobTransformationList = new ArrayList<BobTransformation>();
        for (String basicNum : pubKey.getListBasicNums()) {
            String transformVertex = BinarySequencesHelper.transformVertexByAllOperator(basicNum, sizeGraph, n,
                    bobStepForA, bobStepForG, bobStepForH);
            bobTransformationList.add(new BobTransformation(basicNum, transformVertex));
        }
        return bobTransformationList;
    }

    private static class BobTransformation {
        private final String basicNum;
        private final String transformBasicNum;

        private BobTransformation(String basicNum, String transformBasicNum) {
            this.basicNum = basicNum;
            this.transformBasicNum = transformBasicNum;
        }

        public String getBasicNum() {
            return basicNum;
        }

        public String getTransformBasicNum() {
            return transformBasicNum;
        }
    }
}
