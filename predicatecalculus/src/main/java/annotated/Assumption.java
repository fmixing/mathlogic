package annotated;

public class Assumption implements Annotation {
    private final int index;

    public Assumption(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "предп. " + index;
    }
}