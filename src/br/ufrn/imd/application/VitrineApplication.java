package br.ufrn.imd.application;

import br.ufrn.imd.model.Carrinho;
import br.ufrn.imd.model.Pokemon;
import br.ufrn.imd.model.Vitrine;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VitrineApplication extends Application {

	private AnchorPane anchorPane;	
	private TextField termoPesquisaTextField;
	private TableView<Pokemon> vitrineTable;
	private TableColumn<Pokemon, String> nomeColumn;
	private TableColumn<Pokemon, Double> forcaColumn;
	private TableColumn<Pokemon, Double> saudeColumn;
	private TableColumn<Pokemon, Double> defesaColumn;
	private TableColumn<Pokemon, Double> staminaColumn;
	private TableColumn<Pokemon, String> vantagemColumn;
	private TableColumn<Pokemon, String> desvantagemColumn;

	private static ObservableList<Pokemon> itemsList = FXCollections.observableArrayList();
	private static Stage stage;
	private static Carrinho carrinho;

	public static Carrinho getCarrinho() {
		return carrinho;
	}

	private int FAILED_EXIT;
	
	private int    WINDOW_WIDTH;
	private int    WINDOW_HEIGHT;
	private String WINDOW_TITLE;
	private String WINDOW_STYLE;
	
	private int TABLE_WIDTH;
	private int TABLE_HEIGHT;

	private void initComponents() {
		FAILED_EXIT = 1;
		
		WINDOW_WIDTH = 1200;
		WINDOW_HEIGHT = 600;
		WINDOW_TITLE = "Loja Pokemon - Vitrine";
		WINDOW_STYLE = "-fx-background-color: linear-gradient(" + "from 0% 0% to 100% 100%, blue 0%, silver 100%);";

		TABLE_WIDTH = 1200;
		TABLE_HEIGHT = 600;
		
		this.anchorPane = new AnchorPane();
		this.anchorPane.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.anchorPane.setStyle(WINDOW_STYLE);

		this.termoPesquisaTextField = new TextField();
		this.termoPesquisaTextField.setPromptText("Digite o item para pesquisa:");

		this.vitrineTable = new TableView<>();
		this.vitrineTable.setPrefSize(TABLE_WIDTH, TABLE_HEIGHT);

		this.nomeColumn = new TableColumn<>("Pokemon");
		this.nomeColumn.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("Nome"));

		this.forcaColumn = new TableColumn<>("Força");
		this.forcaColumn.setCellValueFactory(new PropertyValueFactory<Pokemon, Double>("Forca"));
		
		this.saudeColumn = new TableColumn<>("Saúde");
		this.saudeColumn.setCellValueFactory(new PropertyValueFactory<Pokemon, Double>("Saúde"));
		
		this.defesaColumn = new TableColumn<>("Defesa");
		this.defesaColumn.setCellValueFactory(new PropertyValueFactory<Pokemon, Double>("Defesa"));
		
		this.staminaColumn = new TableColumn<>("Stamina");
		this.staminaColumn.setCellValueFactory(new PropertyValueFactory<Pokemon, Double>("Stamina"));
		
		this.vantagemColumn = new TableColumn<>("Vantagem");
		this.vantagemColumn.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("Vantagem"));
		
		this.desvantagemColumn = new TableColumn<>("Desvantagem");
		this.desvantagemColumn.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("Desvantagem"));
								
		VitrineApplication.carrinho = Carrinho.getInstance();
		
		this.vitrineTable.getColumns().add(nomeColumn);
		this.vitrineTable.getColumns().add(forcaColumn);
		this.vitrineTable.getColumns().add(saudeColumn);
		this.vitrineTable.getColumns().add(defesaColumn);
		this.vitrineTable.getColumns().add(staminaColumn);
		this.vitrineTable.getColumns().add(vantagemColumn);
		this.vitrineTable.getColumns().add(desvantagemColumn);
		
		this.anchorPane.getChildren().addAll(termoPesquisaTextField, vitrineTable);
		
		initItems();
	}
	
	private void initListeners(){
				
		this.termoPesquisaTextField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!termoPesquisaTextField.getText().equals("")){
					vitrineTable.setItems(findItems());
				} else {
					vitrineTable.setItems(itemsList);
				}
			}
		});
		
		this.vitrineTable.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.isPrimaryButtonDown() && event.getClickCount() == 2){
					int selectedPokemonIndex = vitrineTable.getSelectionModel().getSelectedIndex();
					
					ItemVitrineApplication.setPokemon(itemsList.get(selectedPokemonIndex));					
					ItemVitrineApplication.setIndex(selectedPokemonIndex);
					
					try {
						new ItemVitrineApplication().start(new Stage());
					} catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	private void initLayout(){
		double VITRINE_TABLE_X_POSITION = 0;
		double VITRINE_TABLE_Y_POSITION = this.termoPesquisaTextField.getHeight();
		
		double SEARCH_BAR_X_POSITION = WINDOW_WIDTH - this.termoPesquisaTextField.getWidth();
		double SEARCH_BAR_Y_POSITION = 0;
		
		this.vitrineTable.setLayoutX(VITRINE_TABLE_X_POSITION);
		this.vitrineTable.setLayoutY(VITRINE_TABLE_Y_POSITION);
		
		this.nomeColumn.prefWidthProperty().bind(this.vitrineTable.widthProperty().multiply(0.1));
		this.forcaColumn.prefWidthProperty().bind(this.vitrineTable.widthProperty().multiply(0.1));
		this.saudeColumn.prefWidthProperty().bind(this.vitrineTable.widthProperty().multiply(0.1));
		this.defesaColumn.prefWidthProperty().bind(this.vitrineTable.widthProperty().multiply(0.1));
		this.staminaColumn.prefWidthProperty().bind(this.vitrineTable.widthProperty().multiply(0.1));
		this.vantagemColumn.prefWidthProperty().bind(this.vitrineTable.widthProperty().multiply(0.1));
		this.desvantagemColumn.prefWidthProperty().bind(this.vitrineTable.widthProperty().multiply(0.1));
		
		this.termoPesquisaTextField.setLayoutX(SEARCH_BAR_X_POSITION);
		this.termoPesquisaTextField.setLayoutY(SEARCH_BAR_Y_POSITION);
		
		DropShadow dropShadowEffect = new DropShadow();
		dropShadowEffect.setSpread(0.5);
		dropShadowEffect.setColor(Color.RED);
		
		this.termoPesquisaTextField.setEffect(dropShadowEffect);
	}
	
	private void initItems(){
		Vitrine vitrine = Vitrine.getInstance();
		
		vitrine.addProdutos(new Pokemon("Snorlax", "Normal", "Luta", "Fantasma", 500, 1000, 1500, 800),
							new Pokemon("Blastoise", "Água", "Fogo", "Terra", 600, 800, 700, 900)
							);
		
		for(Pokemon pokemon : vitrine.getPokemons()){
			itemsList.add(pokemon);
		}
		
		this.vitrineTable.setItems(itemsList);
	}
	
	private ObservableList<Pokemon> findItems(){
		ObservableList<Pokemon> foundItems = FXCollections.observableArrayList();
		
		for(Pokemon pokemon : VitrineApplication.itemsList){
			if(pokemon.getNome().contains(this.termoPesquisaTextField.getText())){
				foundItems.add(pokemon);
			}
		}
		
		return foundItems;
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
			
			initLayout();
			
			VitrineApplication.stage = primaryStage;
		} catch(Exception e){
			e.printStackTrace();
			System.exit(FAILED_EXIT);
		}
	}

}
