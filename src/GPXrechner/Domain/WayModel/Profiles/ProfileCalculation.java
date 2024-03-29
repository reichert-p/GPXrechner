package GPXrechner.Domain.WayModel.Profiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProfileCalculation {
    public static List<Double> normalize(List<Double> plist) {
        if (plist.isEmpty()){
            return plist;
        }
        Double min = Collections.min(plist);
        Double max = Collections.max(plist);
        double diff = max - min;
        if (diff == 0) {
            return plist.stream().map(e -> e / max).toList();
        }
        ArrayList<Double> list = (ArrayList<Double>) plist;
        for (int i = 0; i < list.size(); i++) {
            double val = list.get(i);
            double normalizedVal = (val - min) / diff;
            list.set(i, normalizedVal);
        }
        return list;
    }

    public static int[] split(int pool, int sections) {
        if ( pool < 0 || sections <= 0){
            return new int[0];
        }
        int[] output = new int[sections];
        int base = pool / sections;
        int remainder = pool % sections;
        for (int i = 0; i < output.length; i++) {
            if (remainder <= i) {
                output[i] = base;
            }
            if (remainder > i) {
                output[i] = 1 + base;
            }
        }
        return output;
    }
}
