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

    public String outputInFile() {
        return stepForBasic.toString();
    }
}
