package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    private GestionProductos gestionProductos;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Conectar a la base de datos MySQL
        gestionProductos = new GestionProductos(ConexionMySQL.conectar());

        // Configurar la ventana principal
        primaryStage.setTitle("Tienda de Abarrotes");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Crear controles de la interfaz gráfica
        Label nombreLabel = new Label("Producto:");
        TextField nombreField = new TextField();
        Label precioLabel = new Label("Precio:");
        TextField precioField = new TextField();
        Label cantidadLabel = new Label("Cantidad:");
        TextField cantidadField = new TextField();
        Button agregarButton = new Button("Agregar Producto");

        // Agregar controles al grid
        grid.add(nombreLabel, 0, 0);
        grid.add(nombreField, 1, 0);
        grid.add(precioLabel, 0, 1);
        grid.add(precioField, 1, 1);
        grid.add(cantidadLabel, 0, 2);
        grid.add(cantidadField, 1, 2);
        grid.add(agregarButton, 0, 3);

        // Configurar el evento de clic del botón agregar
        agregarButton.setOnAction(event -> {
            String nombre = nombreField.getText();
            double precio = Double.parseDouble(precioField.getText());
            int cantidad = Integer.parseInt(cantidadField.getText());
            Producto producto = new Producto(0, nombre, precio, cantidad);
            gestionProductos.agregarProducto(producto);
        });

        // Mostrar la ventana
        primaryStage.setScene(new Scene(grid, 400, 200));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        // Cerrar la conexión a la base de datos al cerrar la aplicación
        if (gestionProductos != null) {
            gestionProductos.cerrarConexion();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

