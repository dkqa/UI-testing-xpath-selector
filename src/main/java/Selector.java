public interface Selector<T extends Selector> {

    String getName();
    T tag(String tag);
    T attribute(boolean enabled, String attr, String value, boolean contains);
    T position(int pos);
    T text(boolean enabled, String text, boolean contains);
    String toXPath();

}
