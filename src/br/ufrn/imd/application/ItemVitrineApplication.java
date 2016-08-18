package br.ufrn.imd.application;

import br.ufrn.imd.model.Pokemon;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ItemVitrineApplication extends Application {

	private AnchorPane anchorPane;
	private static Stage stage;

	private ImageView imageArea;
	private Label nomeLabel;
	private Label forcaLabel;
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
		
		double SPACE = 30;
		double NOME_LABEL_X_POSITION = IMAGE_X_POSITION + imageArea.getFitWidth() + SPACE;
		double NOME_LABEL_Y_POSITION = IMAGE_Y_POSITION;
		
		nomeLabel.setLayoutX(NOME_LABEL_X_POSITION);
		nomeLabel.setLayoutY(NOME_LABEL_Y_POSITION);
						
		double FORCA_LABEL_X_POSITION = NOME_LABEL_X_POSITION;
		double FORCA_LABEL_Y_POSITION = NOME_LABEL_Y_POSITION + SPACE;
		
		forcaLabel.setLayoutX(FORCA_LABEL_X_POSITION);
		forcaLabel.setLayoutY(FORCA_LABEL_Y_POSITION);
		
		double BUTTON_X_POSITION = NOME_LABEL_X_POSITION;
		double BUTTON_Y_POSITION = FORCA_LABEL_Y_POSITION + SPACE;
				
		addCarrinhoButton.setLayoutX(BUTTON_X_POSITION);
		addCarrinhoButton.setLayoutY(BUTTON_Y_POSITION);
		
		WINDOW_TITLE = "Detalhe Pokemon";
		WINDOW_WIDTH =  600;
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
				} catch(Exception e){
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
		this.forcaLabel = new Label("Forca: " + pokemon.getForca());

		this.addCarrinhoButton = new Button("Adicionar ao carrinho");

		this.anchorPane.getChildren().addAll(this.imageArea, this.nomeLabel, this.forcaLabel, this.addCarrinhoButton);

	}

	private void initItems() {
		pokemonPhotos = new String[] { "resources/imgs/snorlax.png", "resources/imgs/blastoise.png" };
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
