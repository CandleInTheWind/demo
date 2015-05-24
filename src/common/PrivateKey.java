package common;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by rage on 23.05.15.
 */
public class PrivateKey implements Serializable {

    private final Map<String, Integer> stepForBasic;

    public PrivateKey(Map<String, Integer> stepForBasic) {
        this.stepForBasic = stepForBasic;
    }

    public Map<String, Integer> getStepForBasic() {
        return stepForBasic;
    }

    @Override
    public String toString() {
        return "PrivateKey{" +
                "stepForBasic=" + stepForBasic +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivateKey that = (PrivateKey) o;

        if (!stepForBasic.equals(that.stepForBasic)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return stepForBasic.hashCode();
    }

    public String outputInFile() {
        return stepForBasic.toString();
    }
}
