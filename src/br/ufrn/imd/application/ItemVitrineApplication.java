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

	private int WINDOW_WIDTH;
	private int WINDOW_HEIGHT;
	private String WINDOW_TITLE;

	private void initLayout() {
		// TODO Auto-generated method stub

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
		WINDOW_TITLE = "Detalhe Pokemon";
		WINDOW_WIDTH = 600;
		WINDOW_HEIGHT = 400;
		
		FAILED_EXIT = 1;

		this.anchorPane = new AnchorPane();
		this.anchorPane.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);

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

			Scene scene = new Scene(this.anchorPane);

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.show();

			ItemVitrineApplication.stage = primaryStage;
			initLayout();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(FAILED_EXIT);
		}
	}

}
