import app.MenuManager;
import app.MenuUI;

public class Main {
    public static void main(String[] args) {
        MenuUI menuUI = new MenuUI();
        MenuManager menuManager = new MenuManager(menuUI);
        menuManager.run();
    }
}
