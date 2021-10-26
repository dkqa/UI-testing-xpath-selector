public interface Selector<T extends Selector> {

    String getName();
    T tag(String tag);
    T attribute(String attr, String value, boolean contains, boolean enabled);
    T position(int pos);
    T text(String text, boolean dot, boolean contains, boolean enabled);
    T name(String name);
    String toXPath();
}
