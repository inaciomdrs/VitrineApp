package br.ufrn.imd.application;

import java.util.List;

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
		// TODO Auto-generated method stub
		
	}

	private void initListeners() {
		voltarVitrineButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CarrinhoApplication.getStage().close();
				ItemVitrineApplication.getStage().close();
			}
		});		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		
	}
	
	private void initItems() {
		List<Pokemon> pokemons = VitrineApplication.getCarrinho().getPokemons();
		
		itemsList.clear();
		
		for(Pokemon pokemon : pokemons){
			itemsList.add(pokemon);
		}
		
		this.vitrineTable.setItems(itemsList);
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

			CarrinhoApplication.stage = primaryStage;
			initLayout();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(FAILED_EXIT);
		}
	}

}
