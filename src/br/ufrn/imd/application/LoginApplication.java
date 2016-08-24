package br.ufrn.imd.application;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginApplication extends Application {

	private static Stage stage;
	
	private AnchorPane anchorPane;
		
	private TextField loginInputBox;
	private PasswordField passwordInputBox;
	private Button entrarButton;
	private Button sairButton;
	
	private double ANCHOR_PANE_WIDTH;
	private double ANCHOR_PANE_HEIGHT;
	
	private String WINDOW_TITLE;
	private String FAREWELL_MESSAGE;
	private String FAILED_LOGIN_MESSAGE;
	
	private int SUCCESSFUL_EXIT;
	private int FAILED_EXIT;

	public double middleCordinates(double componentWidth, double anchorPaneWidth) {
		return ((anchorPaneWidth - componentWidth) / 2);
	}

	private void initComponents() {
		ANCHOR_PANE_WIDTH = 400;
		ANCHOR_PANE_HEIGHT = 300;
		SUCCESSFUL_EXIT = 0;
		FAILED_EXIT = 1;
		WINDOW_TITLE = "Login - Loja Pokemon";
		FAREWELL_MESSAGE = "Até logo!";
		FAILED_LOGIN_MESSAGE = "Erro! Login ou Senha inválidos!";
		
		anchorPane = new AnchorPane();
		anchorPane.setPrefSize(ANCHOR_PANE_WIDTH, ANCHOR_PANE_HEIGHT);
		
		loginInputBox = new TextField();
		passwordInputBox = new PasswordField();
		entrarButton = new Button("Entrar");
		sairButton = new Button("Sair");
		
		anchorPane.getChildren().addAll(loginInputBox, passwordInputBox, entrarButton, sairButton);
	}
	
	private void initLayout(){
		anchorPane.getStyleClass().add("pane");
				
		loginInputBox.setPromptText("Digite aqui seu login");
		loginInputBox.setLayoutX(middleCordinates(loginInputBox.getWidth(), ANCHOR_PANE_WIDTH));
		loginInputBox.setLayoutY(50);

		passwordInputBox.setPromptText("Digite aqui sua senha");
		passwordInputBox.setLayoutX(middleCordinates(passwordInputBox.getWidth(), ANCHOR_PANE_WIDTH));
		passwordInputBox.setLayoutY(100);

		entrarButton.setLayoutX(middleCordinates(entrarButton.getWidth(), ANCHOR_PANE_WIDTH));
		entrarButton.setLayoutY(150);
		entrarButton.getStyleClass().add("btEntrar");

		sairButton.setLayoutX(middleCordinates(sairButton.getWidth(), ANCHOR_PANE_WIDTH));
		sairButton.setLayoutY(200);
		sairButton.getStyleClass().add("btSair");
	}
	
	private void initListeners(){
		this.entrarButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logar();		
			}
		});
		
		this.sairButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				fecharAplicacao();
			}
		});
	}
	
	private void logar(){
		boolean correctLogin = loginInputBox.getText().equals("admin");
		boolean correctPassword = passwordInputBox.getText().equals("123");
		
		if(correctLogin && correctPassword){
			try {
				new VitrineApplication().start(new Stage());
				LoginApplication.stage.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(FAILED_EXIT);
			}
		} else {
			JOptionPane.showMessageDialog(null, FAILED_LOGIN_MESSAGE);
		}
	}

	private void fecharAplicacao(){
		JOptionPane.showMessageDialog(null, FAREWELL_MESSAGE);
		System.exit(SUCCESSFUL_EXIT);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			initComponents();
			initListeners();
			
			Scene scene = new Scene(anchorPane);
			scene.getStylesheets().add("resources/css/application.css");
			

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.show();
						
			initLayout();

			LoginApplication.stage = primaryStage;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
