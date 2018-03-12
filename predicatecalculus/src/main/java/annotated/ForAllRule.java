package annotated;

public class ForAllRule implements Annotation {
    private final int index;

    public ForAllRule(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "введение квантора всеобщности " + index;
    }

    public int getIndex() {
        return index;
    }
}
