package bmi;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	//Using ComboBox to avoid user not entering height
	ComboBox<Integer> cbFeet = new ComboBox<>();
	ComboBox<Integer> cbInches = new ComboBox<>();
	TextField tfFeet = new TextField();
	TextField tfInches = new TextField();
	TextField tfPounds = new TextField();
	Text info = new Text();
	
	@Override
	public void start(Stage primaryStage)
	{
		//GUI Components
		BorderPane pane = new BorderPane();
		GridPane gridPane = new GridPane();
		Text text = new Text("Body Mass Index Calculator");
		Text height = new Text("Enter your height");
		Text weight = new Text("Enter your weight");
		Label feet = new Label("ft", cbFeet);
		Label inches = new Label("in", cbInches);
		Label pounds = new Label("lbs", tfPounds);
		Button btCalculate = new Button("Calculate");
		
		//Adding elements of both ComboBox objects
		cbFeet.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8);
		cbInches.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
		
		//Set default value to avoid null values
		cbFeet.setValue(0);
		cbInches.setValue(0);
		
		tfPounds.setPrefWidth(40);
		
		//Adding nodes to the pane
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		
		gridPane.add(height, 0, 0);
		gridPane.add(feet, 1, 0);
		gridPane.add(inches, 2, 0);
		
		gridPane.add(weight, 0, 1);
		gridPane.add(pounds, 1, 1);
		
		gridPane.add(btCalculate, 1, 2);
		
		//Event handler for calculate button will calculate BMI and display it to the user along with a message about their BMI
		btCalculate.setOnAction(e-> {
			
			double bmi = calculateBMI();
			info.setText(String.format("%s%.2f", "Your BMI is ", bmi));
			info.setFill(Color.RED);
			
			pane.setBottom(info);
			BorderPane.setAlignment(info, Pos.CENTER);
			
			//Input validation
			//If BMI is zero. User entered wrong data
			if (bmi == 0)
				info.setText("Error. Enter a valid weight.");
			
			else if (bmi < 0)
				info.setText("Error. Weight must be positive.");
			
			else if (cbFeet.getValue() == 0 && cbInches.getValue() == 0)
				info.setText("Error. Height cannot be zero.");
			
			//Determine your weight category with the information that we have
			else if (bmi < 18.5)
				info.setText(info.getText() + "\nYou are underweight.");
				
			else if (bmi >= 25)
				info.setText(info.getText() + "\nYou are overweight.");
			
			else
			{
				info.setText(info.getText() + "\nYou're at an optimal weight. Keep it up!");
				info.setFill(Color.GREEN);
			}
		});
		
		//Setting up elements in the main pane
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
	
	//This method uses the values for the height and weight and calculates the person's BMI
	public double calculateBMI()
	{
		double feet, inches, totalInches, weight, bmi = 0;
		
		//Try-catch block handles cases where the user enters the wrong data or doesn't enter anything
		try
		{
			feet = cbFeet.getValue();
			inches = cbInches.getValue();
			weight = Double.parseDouble(tfPounds.getText());
			totalInches = feet * 12 + inches;
			bmi = (weight * 703) / Math.pow(totalInches, 2); //BMI formula
		}
		
		catch (NumberFormatException e)
		{
			//Caught
		}
		
		return bmi;
	}
	 
	public static void main(String[] args) 
	{
		launch(args);
	}

}
