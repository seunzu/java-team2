import controller.DisplayController;
import controller.StoreController;
import dto.Store;
import controller.CustomerController;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        new StoreController().start();
    }
}