package sample;

import javafx.scene.control.TextField;

public class Checking {
    public static void checkTextField(TextField field, String regex){
        String temp = field.getText().replaceAll("\\s+","");
        if(!temp.matches(regex)){
            field.setStyle("-fx-border-color: red;");
            field.setText("");
        }
        else {
            field.setStyle(null);
        }
    }
}
