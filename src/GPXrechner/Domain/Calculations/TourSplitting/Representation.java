package GPXrechner.Domain.Calculations.TourSplitting;

import java.util.Arrays;

public class Representation {
    final boolean[] bitString;

    public Representation(int length) {
        bitString = new boolean[length];
        for (int i = 0; i < bitString.length; i++) {
            bitString[i] = true;
        }
    }

    public Representation(boolean[] bitString) {
        this.bitString = bitString;
    }

    public boolean[] getBitString() {
        return bitString;
    }

    public Representation bitFlip() {
        int randomNumber = (int) (Math.random() * bitString.length);
        boolean[] newBitString = Arrays.copyOf(bitString, bitString.length);
        newBitString[randomNumber] = !newBitString[randomNumber];
        return new Representation(newBitString);
    }

    public Representation crossover(Representation otherParent) {
        boolean[] newBitString = Arrays.copyOf(bitString, bitString.length);
        for (int i = 0; i < bitString.length; i++) {
            if (Math.random() < 0.5) {
                newBitString[i] = otherParent.getBitString()[i];
            }
        }
        return new Representation(newBitString);
    }
}
