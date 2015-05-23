package common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rage on 23.05.15.
 */
public class PubKey implements Serializable {

    private final List<String> listBasicNums;
    private final String controlSum;

    public PubKey(List<String> listBasicNums, String controlSum) {
        this.listBasicNums = listBasicNums;
        this.controlSum = controlSum;
    }

    public List<String> getListBasicNums() {
        return listBasicNums;
    }

    public String getControlSum() {
        return controlSum;
    }

    @Override
    public String toString() {
        return "PubKey{" +
                "listBasicNums=" + listBasicNums +
                ", controlSum='" + controlSum + '\'' +
                '}';
    }

    public String outputInFile() {
        return listBasicNums + "\n" + controlSum;
    }
}
