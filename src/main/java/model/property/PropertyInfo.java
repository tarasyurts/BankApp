package model.property;

public abstract class PropertyInfo {

    protected final String propertyName;
    protected final int length;

    public PropertyInfo(String propertyName, int length) {
        this.propertyName = propertyName;
        this.length = length;
    }
}
