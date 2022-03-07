package co.il.dmobile.myapplication_2;

import java.io.Serializable;

public class Recipes implements Serializable {
    private int Image;
    private String uri;
    private String level;
    private String name;
    private String Time;
    private String Ingredients;
    private String Cooking;


    public int getImage() {
        return Image;
    }

    public String getLevel() {
        return level;
    }
    public String getName() {
        return name;
    }

    public String getCooking() {
        return Cooking;
    }

    public String getTime() {
        return Time;
    }

    public String getIngredients() {
        return Ingredients;
    }
    public String getUri() {
        return uri;
    }

    public void setUri(String image){
        this.uri = image;
    }

    public Recipes(int Image, String Level, String Name, String Time, String Ingredients, String Cooking)
    {
        this.Image = Image;
        this.level = Level;
        this.name = Name;
        this.Time = Time;
        this.Ingredients = Ingredients;
        this.Cooking = Cooking;
        this.uri = null;
    }

}
