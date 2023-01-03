package GPXrechner.WayModel.Entities;

import java.util.List;

public class ProfileCalculation {
    public static List<Double> normalize(List<Double> list, double min, double max){
        double diff = max - min;
        for (int i = 0; i < list.size(); i++) {
            double val = list.get(i);
            double normalizedVal = (val - min) / diff;
            list.set(i,normalizedVal);
        }
        return list;
    }

    public static int[] split(int pool,int sections){
        int[] output = new int[sections];
        int base = pool/sections;
        int remainder = pool % sections;
        for (int i = 0; i < output.length; i++){
            if (remainder <= i){
                output[i] = base;
            }
            if (remainder > i){
                output[i] = 1+base;
            }
        }
        return output;
    }
}
