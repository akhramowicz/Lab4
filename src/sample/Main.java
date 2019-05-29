package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.util.Arrays;
import java.util.ArrayList;

public class Main extends Application {

    private static Integer[] vec1 = {};
    private static Integer[] vec2 = {};
    private static Integer[] result = {};

    public static int countMin()
    {
        return Integer.min(vec1.length, vec2.length);
    }



    public static void main(String[] args) {
        Application.launch(args);
    }


    /*public void init () {

    }*/

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(true);
        GridPane gridpane = new GridPane();
        Scene scene = new Scene(gridpane, 250, 95);
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);


        gridpane.add(new Label("Vector 1: "), 0, 0);
        gridpane.add(new Label("Vector 2: "), 0, 1);

        Button button = new Button("Get Result");
        gridpane.add(button, 0, 2);

        TextField fieldVec1 = new TextField();
        fieldVec1.focusedProperty().addListener((arg, oldValue, newValue) -> {
           if(!newValue){
                Checking.checkTextField(fieldVec1, "^(?:\\d\\,?)+$");
            }
        });
        gridpane.add(fieldVec1, 1, 0);

        TextField fieldVec2 = new TextField();
        fieldVec2.focusedProperty().addListener((arg, oldValue, newValue) -> {
            if(!newValue){
                Checking.checkTextField(fieldVec2, "^(?:\\d\\,?)+$");
            }
        });
        gridpane.add(fieldVec2, 1, 1);
        Label lblResult = new Label();
        gridpane.add(lblResult, 1, 2);

        button.setOnAction(e -> {
            String text1 = fieldVec1.getText();
            String text2 = fieldVec2.getText();

            if(text1.isEmpty() || text2.isEmpty()){
                new Alert(Alert.AlertType.ERROR).showAndWait();
                return;
            }

            String[] strvec1 = text1.replaceAll("\\s+","").split(",");
            String[] strvec2 = text2.replaceAll("\\s+","").split(",");

            vec1 = new Integer[strvec1.length];
            vec2 = new Integer[strvec2.length];
            ArrayList<Integer> buffer = new ArrayList();
            Integer i = 0;
            for(String num : strvec1){
                buffer.add(Integer.parseInt(num));
                i++;
            }
            System.out.println(buffer);
            vec1 = buffer.toArray(new Integer[buffer.size()]);
            System.out.println(vec1);
            buffer.clear();
            i = 0;
            for(String num : strvec2){
                buffer.add(Integer.parseInt(num));
                i++;
            }
            vec2 = buffer.toArray(new Integer[buffer.size()]);
            //Main newStream = new Main();
            result = new Integer[countMin()];
            for (int ind = 0; ind < countMin(); ind++) {
                PartMultiplication partMultiplication = new PartMultiplication(vec1, vec2, ind, result);
                //while(partMultiplication.resulting!=0) result[ind] = partMultiplication.resulting;
                partMultiplication.start();
                try {
                    partMultiplication.join(1000);
                } catch (InterruptedException exc) {
                    System.err.println(exc);
                }
            }
            Integer finalResult = 0;
            for (int count = 0; count < result.length; count++) {
                finalResult += result[count];
            }

                /*VectorsMultiplication multiplVectors = new VectorsMultiplication();
            Integer[] result = {};
            try {
                result = multiplVectors.partMultiplication(vec1, vec2);
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            Integer finalResult = 0;
            for (int count=0; count<result.length; count++) {
                finalResult += result[count];
            }*/
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Result");
            alert.setHeaderText(Integer.toString(finalResult));

            alert.showAndWait();

            fieldVec1.clear();
            fieldVec2.clear();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
