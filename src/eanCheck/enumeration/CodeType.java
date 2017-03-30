package eanCheck.enumeration;

public enum CodeType {
    EAN_8("ean_8"),
    EAN_16("ean_16");

    public String name;

    CodeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
