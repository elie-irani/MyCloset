package lcts.inc.mycloset;

public class Clothes {

    private String name;
    private String type;
    private byte[] imageResourceId;

    public static Clothes[] clothes;

    public Clothes(String type, String name, byte[] imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public byte[] getImageResourceId() {
        return imageResourceId;
    }

    public String getType() {
        return type;
    }

    public static void setClothes(Clothes[] input) {
        clothes = input;
    }
}
