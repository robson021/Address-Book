package enums;

/**
 * Created by robert on 04.04.16.
 */
public enum Categories {

    FRIEND("znajomy"),
    FAMILY("rodzina"),
    WORK("praca");
    private final String text;

    Categories(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
