package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/*
    Controls the main menu page, where the user can choose to sign out, view the about us, enter ingredients
    or view the pantry
*/
public class AfterLoginGUIController implements Initializable {
    //File input stream variable is required to pass into parameters of the image.
    //File input stream variable will utilize the image relative path, located in the resource folder
    FileInputStream fstreamObj1 = new FileInputStream("Resources/About_iCook_final.png");
    FileInputStream fstreamObj2 = new FileInputStream("Resources/Enter_Ingredients_final.png");
    FileInputStream fstreamObj3 = new FileInputStream("Resources/View_Cookbook_final.png");
    FileInputStream fstreamObj4 = new FileInputStream("Resources/Main_Menu_logo.png");
    //As previously stated pass in the fstream objects
    Image topImage = new Image(fstreamObj1);
    Image middleImage = new Image(fstreamObj2);
    Image bottomImage = new Image(fstreamObj3);
    Image menuLogoImage = new Image(fstreamObj4);
    ControllersDAO dataAO = new ControllersDAO();
    UserIngredients uiObj = new UserIngredients();
    //@FXML references the FXML elements
    //All declared elements will be altered after scene is launched
    @FXML
    ImageView aboutICook;
    @FXML
    ImageView enterIngredients;
    @FXML
    ImageView viewPantry;
    @FXML
    ImageView menuLogo;
    //Constructor is needed to throw exception from file input variables
    public AfterLoginGUIController() throws FileNotFoundException {
    }
    public void setUserName(String userName){
        uiObj.setUser(userName);
    }
    public void signOut(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScreenGUI.fxml"));
            //FXMLLoader loader = new FXMLLoader();
            loader.getClassLoader().getResource("LoginScreenGUI.fxml");
            Parent root = loader.load();
            //Get controller of scene2
            LoginScreenGUIController loginScreenGUI = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            //Use any FXML object to reference the scene and close it, otherwise the window will stay open while the other is
            //being used.
            ((Stage)aboutICook.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goToViewPantry() throws Exception {
        //Pass the username through the java files via RecipeIngredients object
        //to ensure the pantry can be accessed regardless of what page the user previously navigated from
        String userName = uiObj.getUser();
        //Store the recipe strings retrieved from DAO in an arraylist
        //the username is passed in to scan the user info table comparing against the username
        ArrayList<String> recipeList = dataAO.getRecipeFromUser(userName);
        //Set the chosen recipes for the user passing in the recipe list received from the DAO
        uiObj.setChosenRecipesForPantry(recipeList);
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OverviewRecipesGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            OverviewRecipesGUIController overviewRecipesGUIControllerObj = loader.getController();
            //Use the controller object to set the recipes which will be displayed according data query results
            //Set the username as specified previously
            overviewRecipesGUIControllerObj.setRecipeObjectForPantry(uiObj);
            overviewRecipesGUIControllerObj.setUserName(uiObj.getUser());
            //The page name is passed through java files to ensure the correct page is displayed when pressing the back button
            //As multiple pages navigate to the pantry/display recipe page
            overviewRecipesGUIControllerObj.setPageName("AfterLoginGUIController");
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            overviewRecipesGUIControllerObj.fillListWithPantryItems();
            //Use any FXML object to reference the scene and close it, otherwise the window will stay open while the other is
            //being used.
            ((Stage)aboutICook.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goToEnterIngredients(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            MainMenuGUIController firstSceneController = loader.getController();
            //passing username is discussed above
            firstSceneController.setUserName(uiObj.getUser());
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            //Use any FXML object to reference the scene and close it, otherwise the window will stay open while the other is
            //being used.
            ((Stage)aboutICook.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goToAboutICook(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutUsGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            AboutUsGUIController aboutICookController = loader.getController();
            //passing username discussed above
            aboutICookController.setUser(uiObj.getUser());
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            //Use any FXML object to reference the scene and close it, otherwise the window will stay open while the other is
            //being used.
            ((Stage)aboutICook.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Initialize method will set the images after the scene has already been established.
    //Need to use this method to initialize image view in real time.
    //Allows the implementing class to perform any necessary post-processing on the content.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aboutICook.setImage(topImage);
        enterIngredients.setImage(middleImage);
        viewPantry.setImage(bottomImage);
        menuLogo.setImage(menuLogoImage);
    }
}
