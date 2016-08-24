package br.ufrn.imd.application;

import br.ufrn.imd.model.Pokemon;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ItemVitrineApplication extends Application {

	private AnchorPane anchorPane;
	private static Stage stage;

	private ImageView imageArea;

	private Label nomeLabel;
	private Label tipoLabel;
	private Label forcaLabel;
	private Label defesaLabel;
	private Label saudeLabel;
	private Label staminaLabel;
	private Label vantagemLabel;
	private Label desvantagemLabel;

	private Button addCarrinhoButton;

	private static Pokemon pokemon;
	private static int index;
	private static String pokemonPhotos[];

	private int FAILED_EXIT;

	private double WINDOW_WIDTH;
	private double WINDOW_HEIGHT;
	private String WINDOW_TITLE;

	private void initLayout() {
		double IMAGE_WIDTH = 250;
		double IMAGE_X_POSITION = 30;
		double IMAGE_Y_POSITION = 30;

		imageArea.setFitWidth(IMAGE_WIDTH);
		imageArea.setPreserveRatio(true);
		imageArea.setLayoutX(IMAGE_X_POSITION);
		imageArea.setLayoutY(IMAGE_Y_POSITION);
		imageArea.setEffect(new Reflection());

		double SPACE = 30;
		double NOME_LABEL_X_POSITION = IMAGE_X_POSITION + imageArea.getFitWidth() + SPACE;
		double NOME_LABEL_Y_POSITION = IMAGE_Y_POSITION;

		nomeLabel.setLayoutX(NOME_LABEL_X_POSITION);
		nomeLabel.setLayoutY(NOME_LABEL_Y_POSITION);
		
		double TIPO_LABEL_X_POSITION = NOME_LABEL_X_POSITION;
		double TIPO_LABEL_Y_POSITION = NOME_LABEL_Y_POSITION + SPACE;

		tipoLabel.setLayoutX(TIPO_LABEL_X_POSITION);
		tipoLabel.setLayoutY(TIPO_LABEL_Y_POSITION);

		double FORCA_LABEL_X_POSITION = NOME_LABEL_X_POSITION;
		double FORCA_LABEL_Y_POSITION = TIPO_LABEL_Y_POSITION + SPACE;

		forcaLabel.setLayoutX(FORCA_LABEL_X_POSITION);
		forcaLabel.setLayoutY(FORCA_LABEL_Y_POSITION);
		
		double DEFESA_LABEL_X_POSITION = NOME_LABEL_X_POSITION;
		double DEFESA_LABEL_Y_POSITION = FORCA_LABEL_Y_POSITION + SPACE;

		defesaLabel.setLayoutX(DEFESA_LABEL_X_POSITION);
		defesaLabel.setLayoutY(DEFESA_LABEL_Y_POSITION);
		
		double SAUDE_LABEL_X_POSITION = NOME_LABEL_X_POSITION;
		double SAUDE_LABEL_Y_POSITION = DEFESA_LABEL_Y_POSITION + SPACE;

		saudeLabel.setLayoutX(SAUDE_LABEL_X_POSITION);
		saudeLabel.setLayoutY(SAUDE_LABEL_Y_POSITION);
		
		double STAMINA_LABEL_X_POSITION = NOME_LABEL_X_POSITION;
		double STAMINA_LABEL_Y_POSITION = SAUDE_LABEL_Y_POSITION + SPACE;

		staminaLabel.setLayoutX(STAMINA_LABEL_X_POSITION);
		staminaLabel.setLayoutY(STAMINA_LABEL_Y_POSITION);
		
		double VANTAGEM_LABEL_X_POSITION = NOME_LABEL_X_POSITION;
		double VANTAGEM_LABEL_Y_POSITION = STAMINA_LABEL_Y_POSITION + SPACE;

		vantagemLabel.setLayoutX(VANTAGEM_LABEL_X_POSITION);
		vantagemLabel.setLayoutY(VANTAGEM_LABEL_Y_POSITION);
		
		double DESVANTAGEM_LABEL_X_POSITION = NOME_LABEL_X_POSITION;
		double DESVANTAGEM_LABEL_Y_POSITION = VANTAGEM_LABEL_Y_POSITION + SPACE;

		desvantagemLabel.setLayoutX(DESVANTAGEM_LABEL_X_POSITION);
		desvantagemLabel.setLayoutY(DESVANTAGEM_LABEL_Y_POSITION);
		
		double BUTTON_X_POSITION = NOME_LABEL_X_POSITION;
		double BUTTON_Y_POSITION = DESVANTAGEM_LABEL_Y_POSITION + SPACE;

		addCarrinhoButton.setLayoutX(BUTTON_X_POSITION);
		addCarrinhoButton.setLayoutY(BUTTON_Y_POSITION);
		
		InnerShadow innerShadowEffect = new InnerShadow(); 
		innerShadowEffect.setColor(Color.GREEN);
		
		addCarrinhoButton.setEffect(innerShadowEffect);

		WINDOW_TITLE = "Detalhe Pokemon";
		WINDOW_WIDTH = 600;
		WINDOW_HEIGHT = 400;

		this.anchorPane.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	private void initListeners() {
		this.addCarrinhoButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				VitrineApplication.getCarrinho().addProdutos(pokemon);
				try {
					new CarrinhoApplication().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initComponents() {
		FAILED_EXIT = 1;

		this.anchorPane = new AnchorPane();

		initItems();

		this.imageArea = new ImageView(pokemonPhotos[index]);
		this.nomeLabel = new Label("Nome: " + pokemon.getNome());
		this.tipoLabel = new Label("Tipo: " + pokemon.getTipo());
		this.forcaLabel = new Label("Forca: " + pokemon.getForca());
		this.defesaLabel = new Label("Defesa: " + pokemon.getDefesa());
		this.saudeLabel = new Label("Saúde: " + pokemon.getSaude());
		this.staminaLabel = new Label("Stamina: " + pokemon.getStamina());
		this.vantagemLabel = new Label("Vantagem: " + pokemon.getVantagem());
		this.desvantagemLabel = new Label("Vantagem: " + pokemon.getDesvantagem());

		this.addCarrinhoButton = new Button("Adicionar ao carrinho");

		this.anchorPane.getChildren().addAll(this.imageArea, this.nomeLabel, this.tipoLabel, this.forcaLabel, 
				this.defesaLabel, this.saudeLabel, this.staminaLabel, this.vantagemLabel, this.desvantagemLabel, 
				this.addCarrinhoButton);

	}

	private void initItems() {
		pokemonPhotos = new String[] { "resources/imgs/snorlax.png", "resources/imgs/blastoise.png" };
	}
	
	private void initTransitions(){
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), this.imageArea);
		fadeTransition.setFromValue(0.0);
		fadeTransition.setToValue(1.0);
		fadeTransition.play();
		
		ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(2000), addCarrinhoButton);
		scaleTransition.setToX(1.5);
		scaleTransition.setToY(1.5);
		scaleTransition.setCycleCount(2);
		scaleTransition.play();
		
		SequentialTransition transitions = new SequentialTransition();
		transitions.getChildren().addAll(fadeTransition, scaleTransition);
		transitions.play();
	}
	
	private void initTimeline(){
		Timeline timeline = new Timeline();
		
		KeyValue keyValue = new KeyValue(imageArea.opacityProperty(), 0.0);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(2000), keyValue);
		
		timeline.getKeyFrames().add(keyFrame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setPokemon(Pokemon pokemon) {
		ItemVitrineApplication.pokemon = pokemon;
	}

	public static void setIndex(int index) {
		ItemVitrineApplication.index = index;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			initComponents();
			initListeners();
			initTransitions();
			initTimeline();
			initLayout();
			
			Scene scene = new Scene(this.anchorPane);

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.show();

			ItemVitrineApplication.stage = primaryStage;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(FAILED_EXIT);
		}
	}

}
