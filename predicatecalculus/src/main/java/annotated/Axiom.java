package annotated;

public class Axiom implements Annotation {
    private final int index;

    public Axiom(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Сх. акс. " + index;
    }
}