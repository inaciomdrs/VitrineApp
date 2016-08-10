package br.ufrn.imd.application;



import br.ufrn.imd.model.Carrinho;
import br.ufrn.imd.model.Pokemon;
import br.ufrn.imd.model.Vitrine;
import br.ufrn.imd.properties.ItemProperty;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VitrineApplication extends Application {

	private AnchorPane anchorPane;	
	private TextField termoPesquisaTextField;
	private TableView<ItemProperty> vitrineTable;
	private TableColumn<ItemProperty, String> produtoColumn;
	private TableColumn<ItemProperty, Double> precoColumn;

	private static ObservableList<ItemProperty> itemsList = FXCollections.observableArrayList();
	private static Stage stage;
	private Carrinho carrinho;

	private int FAILED_EXIT;
	
	private int    WINDOW_WIDTH;
	private int    WINDOW_HEIGHT;
	private String WINDOW_TITLE;
	private String WINDOW_STYLE;
	
	private int TABLE_WIDTH;
	private int TABLE_HEIGHT;

	private void initComponents() {
		FAILED_EXIT = 1;
		
		WINDOW_WIDTH = 800;
		WINDOW_HEIGHT = 600;
		WINDOW_TITLE = "Loja Pokemon - Vitrine";
		WINDOW_STYLE = "-fx-background-color: linear-gradient(" + "from 0% 0% to 100% 100%, blue 0%, silver 100%);";

		TABLE_WIDTH = 780;
		TABLE_HEIGHT = 750;
		
		this.anchorPane = new AnchorPane();
		this.anchorPane.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.anchorPane.setStyle(WINDOW_STYLE);

		this.termoPesquisaTextField = new TextField();
		this.termoPesquisaTextField.setPromptText("Digite o item para pesquisa:");

		this.vitrineTable = new TableView<ItemProperty>();
		this.vitrineTable.setPrefSize(TABLE_WIDTH, TABLE_HEIGHT);

		this.produtoColumn = new TableColumn<ItemProperty, String>("Pokemon");
		this.produtoColumn.setCellValueFactory(new PropertyValueFactory<ItemProperty, String>("Nome"));

		this.precoColumn = new TableColumn<ItemProperty, Double>("Força");
		this.precoColumn.setCellValueFactory(new PropertyValueFactory<ItemProperty, Double>("Forca"));
				
		this.carrinho = Carrinho.getInstance();
		
		this.vitrineTable.getColumns().addAll(produtoColumn, precoColumn);
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
	}
	
	private void initLayout(){
		double VITRINE_TABLE_X_POSITION = 0;
		double VITRINE_TABLE_Y_POSITION = this.termoPesquisaTextField.getHeight();
		
		double SEARCH_BAR_X_POSITION = WINDOW_WIDTH - this.termoPesquisaTextField.getWidth();
		double SEARCH_BAR_Y_POSITION = 0;
		
		this.vitrineTable.setLayoutX(VITRINE_TABLE_X_POSITION);
		this.vitrineTable.setLayoutY(VITRINE_TABLE_Y_POSITION);
		
		this.termoPesquisaTextField.setLayoutX(SEARCH_BAR_X_POSITION);
		this.termoPesquisaTextField.setLayoutY(SEARCH_BAR_Y_POSITION);
	}
	
	private void initItems(){
		Vitrine vitrine = Vitrine.getInstance();
		
		vitrine.addProdutos(new Pokemon("Pikachu", 12.00),
							new Pokemon("Charmander", 19.00),
							new Pokemon("Zubat", 9.00),
							new Pokemon("Ratata", 11.00),
							new Pokemon("Pidgey", 10.00));
		
		for(Pokemon pokemon : vitrine.getPokemons()){
			itemsList.add(new ItemProperty(pokemon.getNome(), pokemon.getForca()));
		}
		
		this.vitrineTable.setItems(itemsList);
	}
	
	private ObservableList<ItemProperty> findItems(){
		ObservableList<ItemProperty> foundItems = FXCollections.observableArrayList();
		
		for(ItemProperty items : VitrineApplication.itemsList){
			if(items.getNome().contains(this.termoPesquisaTextField.getText())){
				foundItems.add(items);
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
