package controller;

import dto.Store;
import service.DisplayService;

public class DisplayController {
    private static DisplayController displayController;
    private final DisplayService displayService;

    public DisplayController() {
        displayService = new DisplayService();
    }

    public static DisplayController getInstance() {
        if (displayController == null) {
            displayController = new DisplayController();
        }
        return displayController;
    }

    public void start(Store store) {
        displayService.controlStorage(store);
    }
}