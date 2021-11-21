package model;

public abstract class Item implements Usable{
    String name;

    public Item(String string) {
        this.name = string;
    }

    public String getName() {
        return name;
    }
}
