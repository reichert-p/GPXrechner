package GPXrechner.Calculations.TourSplitting;

import java.util.Arrays;

public class Representation {
    final boolean[] bitstring;

    public Representation(int length) {
        bitstring = new boolean[length];
        for (int i = 0; i < bitstring.length; i++){
            bitstring[i] = true;
        }
    }

    public Representation(boolean[] bitstring) {
        this.bitstring = bitstring;
    }
    public boolean[] getBitstring() {
        return bitstring;
    }

    public Representation bitFlip(){
        int randomNumber = (int)(Math.random() * bitstring.length);
        boolean[] newBitString = Arrays.copyOf(bitstring,bitstring.length);
        newBitString[randomNumber] = !newBitString[randomNumber];
        return new Representation(newBitString);
    }

    public Representation copy(){
        return new Representation(Arrays.copyOf(bitstring,bitstring.length));
    }

    public Representation crossover(Representation otherParent){
        boolean[] newBitString = Arrays.copyOf(bitstring,bitstring.length);
        for (int i = 0; i < bitstring.length; i++){
            if (Math.random() < 0.5){
                newBitString[i] = otherParent.getBitstring()[i];
            }
        }
        return new Representation(newBitString);
    }



}
