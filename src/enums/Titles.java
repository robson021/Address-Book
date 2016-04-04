package enums;

/**
 * Created by robert on 04.04.16.
 */
public enum Titles {
    DOCTOR("dr."),
    MASTER("mgr."),
    ENGEENIER("inż."),
    ENGEENIER_MASTER("inż. mgr."),
    NONE("brak");

    private final String text;

    Titles(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
