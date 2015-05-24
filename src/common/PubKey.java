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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PubKey pubKey = (PubKey) o;

        if (!controlSum.equals(pubKey.controlSum)) return false;
        if (!listBasicNums.equals(pubKey.listBasicNums)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = listBasicNums.hashCode();
        result = 31 * result + controlSum.hashCode();
        return result;
    }

    public String outputInFile() {
        return listBasicNums + "\n" + controlSum;
    }
}
