public enum Axes {


    DEFAULT("//");

    private String axis;
    Axes(String axis) {
        this.axis = axis;
    }
    @Override
    public String toString() {
        return axis;
    }
}
