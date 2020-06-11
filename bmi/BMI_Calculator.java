package bmi;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class BMI_Calculator extends Application
{
	TextField tfFeet = new TextField();
	TextField tfInches = new TextField();
	TextField tfPounds = new TextField();
	
	@Override
	public void start(Stage primaryStage)
	{
		BorderPane pane = new BorderPane();
		GridPane gridPane = new GridPane();
		Text text = new Text("Body Mass Index Calculator");
		Text height = new Text("Enter your height");
		Text weight = new Text("Enter your weight");
		
		
		Label feet = new Label("ft", tfFeet);
		Label inches = new Label("in", tfInches);
		Label pounds = new Label("lbs", tfPounds);
		
		Button btCalculate = new Button("Calculate");
		
		tfFeet.setPrefWidth(40);
		tfInches.setPrefWidth(40);
		tfPounds.setPrefWidth(40);
		
		
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		
		gridPane.add(height, 0, 0);
		gridPane.add(feet, 1, 0);
		gridPane.add(inches, 2, 0);
		
		gridPane.add(weight, 0, 1);
		gridPane.add(pounds, 1, 1);
		
		gridPane.add(btCalculate, 1, 2);
		
		btCalculate.setOnAction(e-> {
			
			final double bmi = calculateBMI();
			final Text info = new Text(String.format("%s%.2f", "Your BMI is ", bmi));
			info.setFill(Color.RED);
			
			pane.setBottom(info);
			BorderPane.setAlignment(info, Pos.CENTER);
			
			if (bmi < 18.5)
				info.setText(info.getText() + "\nYou are underweight.");
				
			
			if (bmi >= 25)
				info.setText(info.getText() + "\nYou are overweight.");
			
			else
			{
				info.setText(info.getText() + "\nYou're at an optimal weight. Keep it up!");
				info.setFill(Color.GREEN);
			}
		});
		
		
		pane.setTop(text);
		BorderPane.setAlignment(text, Pos.TOP_CENTER);
		text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		
		pane.setCenter(gridPane);
		gridPane.setAlignment(Pos.CENTER);
		
		pane.setStyle("-fx-background-color: lightblue");
		
		Scene scene = new Scene(pane, 400, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Body Mass Index Calculator");
		primaryStage.show();
	}
	
	public double calculateBMI()
	{
		double feet, inches, totalInches, weight, bmi;
		
		feet = Double.parseDouble(tfFeet.getText());
		inches = Double.parseDouble(tfInches.getText());
		weight = Double.parseDouble(tfPounds.getText());
		
		totalInches = feet * 12 + inches;
		bmi = (weight * 703) / Math.pow(totalInches, 2);
		
		return bmi;
	}
	 
	public static void main(String[] args) 
	{
		launch(args);
	}

}
