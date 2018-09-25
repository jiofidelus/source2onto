package cm.uy1.source2onto.controller.test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean // Use @javax.faces.bean.ManagedBean on outdated environments.
@RequestScoped // Use @javax.faces.bean.RequestScoped on outdated environments.
public class Bean {

    private String text;
    private String choice;
    private String result;

    public void submit() {
        result = "Submitted values: " + text + ", " + choice;
        System.out.println(result);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getResult() {
        return result;
    }
}