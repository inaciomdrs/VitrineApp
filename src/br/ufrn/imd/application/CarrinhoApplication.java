package br.ufrn.imd.application;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrn.imd.model.Pokemon;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CarrinhoApplication extends Application {

	private AnchorPane anchorPane;
	private static Stage stage;
	
	private TableView<Pokemon> vitrineTable;
	private TableColumn<Pokemon, String> produtoColumn;
	private TableColumn<Pokemon, Double> precoColumn;
	
	private Button excluirItemButton;
	private Button voltarVitrineButton;
	private Button confirmarCompraButton;
	
	private static ObservableList<Pokemon> itemsList = FXCollections.observableArrayList();
	
	private int FAILED_EXIT;
	
	private int WINDOW_WIDTH;
	private int WINDOW_HEIGHT;
	private String WINDOW_TITLE;
	
	private void initLayout() {
		double VITRINE_X_POS = 0;
		double VITRINE_Y_POS = 0;
		
		this.vitrineTable.setLayoutX(VITRINE_X_POS);
		this.vitrineTable.setLayoutY(VITRINE_Y_POS);
		
		this.produtoColumn.prefWidthProperty().bind(this.vitrineTable.widthProperty().multiply(0.6));
		this.precoColumn.prefWidthProperty().bind(this.vitrineTable.widthProperty().multiply(0.4));
		
		double SPACE = 30;
		
		System.out.println(this.vitrineTable.widthProperty().doubleValue());
		
		double EXCLUIR_BUTTON_X_POS = VITRINE_X_POS + this.vitrineTable.widthProperty().doubleValue() + SPACE;
		double EXCLUIR_BUTTON_Y_POS = VITRINE_Y_POS;
		
		this.excluirItemButton.setLayoutX(EXCLUIR_BUTTON_X_POS);
		this.excluirItemButton.setLayoutY(EXCLUIR_BUTTON_Y_POS);
		
		double VOLTAR_BUTTON_X_POS = EXCLUIR_BUTTON_X_POS;
		double VOLTAR_BUTTON_Y_POS = EXCLUIR_BUTTON_Y_POS + SPACE;
		
		this.voltarVitrineButton.setLayoutX(VOLTAR_BUTTON_X_POS);
		this.voltarVitrineButton.setLayoutY(VOLTAR_BUTTON_Y_POS);
		
		double CONFIRMAR_BUTTON_X_POS = EXCLUIR_BUTTON_X_POS;
		double CONFIRMAR_BUTTON_Y_POS = VOLTAR_BUTTON_Y_POS + SPACE;
		
		this.confirmarCompraButton.setLayoutX(CONFIRMAR_BUTTON_X_POS);
		this.confirmarCompraButton.setLayoutY(CONFIRMAR_BUTTON_Y_POS);
		
	}

	private void initListeners() {
		voltarVitrineButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CarrinhoApplication.getStage().close();
				ItemVitrineApplication.getStage().close();
			}
		});
		
		excluirItemButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int selectedPokemonIndex = vitrineTable.getSelectionModel().getSelectedIndex();
				VitrineApplication.getCarrinho().getPokemons().remove(selectedPokemonIndex);
				reloadStage();
			}
		});
		
		confirmarCompraButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				JOptionPane.showMessageDialog(null, "Compra finalizada com sucesso!");
				VitrineApplication.getCarrinho().getPokemons().clear();
				reloadStage();
			}
		});
	}

	private void initComponents() {		
		this.anchorPane = new AnchorPane();
		
		WINDOW_WIDTH = 800;
		WINDOW_HEIGHT = 600;
		WINDOW_TITLE = "Loja Pokemon - Carrinho";
		
		this.anchorPane.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		this.vitrineTable = new TableView<>();
		
		this.produtoColumn = new TableColumn<>("Pokemon");
		this.produtoColumn.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("Nome"));

		this.precoColumn = new TableColumn<>("Força");
		this.precoColumn.setCellValueFactory(new PropertyValueFactory<Pokemon, Double>("Forca"));
		
		this.vitrineTable.getColumns().add(produtoColumn);
		this.vitrineTable.getColumns().add(precoColumn);
		
		this.excluirItemButton     = new Button("Excluir Item");
		this.voltarVitrineButton   = new Button("Voltar Vitrine");
		this.confirmarCompraButton = new Button("Confirmar Compra");
		
		this.anchorPane.getChildren().addAll(vitrineTable,excluirItemButton,voltarVitrineButton,confirmarCompraButton);
		
		initItems();
				
	}
	
	private void initItems() {
		List<Pokemon> pokemons = VitrineApplication.getCarrinho().getPokemons();
		
		itemsList.clear();
		
		for(Pokemon pokemon : pokemons){
			itemsList.add(pokemon);
		}
		
		this.vitrineTable.setItems(itemsList);
	}
	
	private void reloadStage(){
		CarrinhoApplication.stage.hide();
		initComponents();
		CarrinhoApplication.stage.show();
	}
	
	public static Stage getStage() {
		return stage;
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

			CarrinhoApplication.stage = primaryStage;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(FAILED_EXIT);
		}
	}

}
