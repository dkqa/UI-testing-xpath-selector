public interface Selector<T extends Selector> {

    String getName();
    T tag(String tag);
    T attribute(boolean enabled, String attr, String value, boolean contains);
    T position(int pos);
    T text(boolean dot, boolean enabled, String text, boolean contains);
    T name(String name);
    String toXPath();

}
