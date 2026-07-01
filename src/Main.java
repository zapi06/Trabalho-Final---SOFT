import controller.BancoController;
import view.MenuView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuView view = new MenuView(scanner);
        BancoController controller = new BancoController(view);
        controller.iniciar();
        scanner.close();
    }
}
