package sg.edu.iss.nus.day17workshop.model;

import java.io.Serializable;

import jakarta.json.JsonObject;

public class Conditions implements Serializable{
    private String description;
    private String icon;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    } 

    public static Conditions createConditions (JsonObject j) {
        Conditions c = new Conditions();
        c.description = "%s - %s".formatted(j.getString("main")
                , j.getString("description"));
        
        c.icon = "https://openweathermap.org/img/wn/%s@4x.png".formatted(
                j.getString("icon"));

        return c;
    }
}
