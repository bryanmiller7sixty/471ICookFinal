package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/*
    Controls log in portion of the application, effectively enabling the user to log in or register an account
*/
public class LoginScreenGUIController implements Initializable, Cloneable {
    //Create the encrypting object that takes in a 16 char value; the final decrypted value will have
    //a random 16 character string
    CryptographerAES cryptoObject = new CryptographerAES("abcdefghijklmnos");
    FileInputStream fstreamObj1 = new FileInputStream("Resources/iCookLogo.png");
    //Pass in the fstream object to create an image
    Image iCookLogoImage = new Image(fstreamObj1);
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    //Error message will display accordingly based on the scenario
    @FXML
    Text errCompMessage;
    @FXML
    ImageView logo;
    //Data access object to authenticate user or register a user
    ControllersDAO obj = new ControllersDAO();
    //Constructor is needed to throw the FNFE, containing the images relative path
    public LoginScreenGUIController() throws FileNotFoundException {
    }
    //Get the text entered in the user and password fields
    //Call the encrypt method from the cryptographer class instance on the password
    //If the retrieve record returns true (the username and password are correct)
    //Start the new scene calling set user on the instance of the next controller class
    //This enables the each individual user to access their unique pantry items
    //If the password isn't correct, prompt the user via error message
    public void login() throws Exception {
        errCompMessage.setText("");
        String user = username.getText();
        String pass = password.getText();
        pass = cryptoObject.encrypt(pass);
        if(obj.retrieveRecord(user,pass)){
            try {
                System.out.println("" + obj.checkAdmin(user));
                if(obj.checkAdmin(user)){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminBuildRecipe.fxml"));
                    Parent root = loader.load();
                    //Get controller of scene2
                    AdminBuildRecipeController firstSceneController = loader.getController();
                    //Pass whatever data you want. You can have multiple method calls here
                    //Show scene 2 in new window
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("iCook");
                    stage.show();
                    firstSceneController.restoreProgressProcess();
                    ((Stage)username.getScene().getWindow()).close();
                }else{
                    //Load second scene
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfterLoginGUI.fxml"));
                    Parent root = loader.load();
                    //Get controller of scene2
                    AfterLoginGUIController firstSceneController = loader.getController();
                    firstSceneController.setUserName(user);
                    //Pass whatever data you want. You can have multiple method calls here
                    //Show scene 2 in new window
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("iCook");
                    stage.show();
                    ((Stage)username.getScene().getWindow()).close();
                }
            } catch (IOException ex) {
                System.err.println(ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            errCompMessage.setText("Invalid entry: username or password incorrect.");
        }
    }
    //Get the user and password text input
    //As before encrypt the password
    //If the enter record returns true (The username isn't already taken)
    //Prompt the user the account creation was successful
    //Other wise prompt the user the username already exists
    public void register() throws Exception {
        String user = username.getText();
        String pass = password.getText();
        pass = cryptoObject.encrypt(pass);
        boolean state = obj.enterRecord(user,pass);
        errCompMessage.setText("Successfully created account, please login.");
        if(!state){
            errCompMessage.setText("Username already exists.");
        }
    }
    //Sets the image after stage has already been set
    //Also required when implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logo.setImage(iCookLogoImage);
    }
}
