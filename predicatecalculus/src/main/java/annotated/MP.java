package annotated;

public class MP implements Annotation {
    private final int alphaIndex;

    private final int gammaIndex;

    public MP(int alphaIndex, int gammaIndex) {
        this.alphaIndex = alphaIndex;
        this.gammaIndex = gammaIndex;
    }

    @Override
    public String toString() {
        return "M.P. " + gammaIndex + " " + alphaIndex;
    }

    public int getAlphaIndex() {
        return alphaIndex;
    }

    public int getGammaIndex() {
        return gammaIndex;
    }
}
