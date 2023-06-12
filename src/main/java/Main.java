import controllers.FileManager;
import controllers.UserInterface;
import controllers.UserManager;
import models.User;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        FileManager fileManager = new FileManager(userManager);

        fileManager.loadData();

        UserInterface userInterface = new UserInterface(userManager, fileManager);
        userInterface.start();
    }
}



