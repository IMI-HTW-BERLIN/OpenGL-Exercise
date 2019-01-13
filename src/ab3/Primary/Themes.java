package ab3.Primary;

public enum Themes {
    RED("Red"),
    NOR("Normals"),
    DIF("Colorful"),
    DIF3("Colorful Triangle"),
    RAI("Rainbow"),
    TEX1("stoneBlock.png"),
    TEX2("stoneBlock2.jpg"),
    TEX3("stoneBlock2_16x16.jpg"),
    TEX4("crackedGround.jpg"),
    TEX5("hello.jpg"),
    GEN1("Generated Texture #1"),
    GEN2("Generated Texture #2"),
    GEN3("Generated Texture #3");

    private final String name;

    Themes(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
