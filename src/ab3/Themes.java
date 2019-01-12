package ab3;

public enum Themes {
    RED("Red"),
    NOR("Normals"),
    DIF("Colorful"),
    DIF3("Colorful Triangle"),
    RAI("Rainbow"),
    TEX1("Texture #1"),
    TEX2("Texture #2"),
    TEX3("Texture #3");

    private final String name;

    Themes(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
