package sample;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/*
    Controls the view recipe page
*/
public class DisplayFinalRecipeController implements Initializable {
    //DAO object used to send/ receive data from the connection class
    ControllersDAO daoObj = new ControllersDAO();
    //FXML elements to be changed
    @FXML
    ListView recipeIngredients;
    @FXML
    ImageView recipeImage;
    //Spinner that could be user in the future (to rate recipes)
//    @FXML
//    Spinner<Integer> spinner;
    @FXML
    TextArea directions;
    @FXML
    Hyperlink hyperlink;
    @FXML
    Text message;
//    final int initialVal = 1;
//    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,5,initialVal);
    //user ingredients object to be used
    UserIngredients obj3 = new UserIngredients();
    //This method is used when the user clicks "back" from the recipes page, they navigated to
    //The back button will take them back to this recipe page, so all the information is needed to be populated
    public void setBackObjects(String image, ArrayList<String> ingList, ArrayList<String> dirListBack){
        obj3.initializeBackItems(image, ingList, dirListBack);
    }
    //Set username again, so the pantry can be accessed via username
    public void setUserName(String userName){
        obj3.setUser(userName);
    }
    // Adds the recipe to the pantry
    // If the recipe is already in the pantry, an error message will
    // be displayed, other wise a success message
    public void addToPantry() throws Exception {
        boolean flag = daoObj.addToUserPantry(obj3.getUser(), obj3.getTempRecipe());
        if(!flag){
            message.setText("This recipe already exists in your cookcook.");
        }else{
            message.setText("Successfully added to your cookbook!");
        }
    }
    //Same concept for delete recipe
    public void deleteRecipe() throws Exception {
        boolean flag = daoObj.deleteFromUserPantry(obj3.getUser(), obj3.getTempRecipe());
        if(!flag){
            message.setText("This recipe doesn't exist in your cookbook.");
        }else{
            message.setText("Successfully deleted from the cookbook!");
        }
    }
    //Sets the relevant recipes user specified from their ingredients
    //This way when the user navigates back to the display recipes page, all of the recipes will still be there
    public void setRecipeObject(UserIngredients obj2){
        obj3.setChosenRecipes(obj2.getChosenRecipes());
    }
    public void fillIngredientsAndDirections(ArrayList<String> ingredientsList, ArrayList<String> directionSteps){
        //the hyper link will be stored in the last index of the arraylist
        hyperlink.setText(directionSteps.get(directionSteps.size() - 1));
        //Reading through the loop, the action will be clear
        //fills the directions and recipe ingredients
        try{
            for(int i = 0; i < directionSteps.size() - 1; i++){
                directions.setText(directions.getText().concat(directionSteps.get(i)));
                directions.setText(directions.getText().concat("\n"));
                System.out.println(directionSteps.get(i));
            }
            obj3.setTempRecipe(ingredientsList.get(0));
            for(int i = 1; i < ingredientsList.size(); i++){
                recipeIngredients.getItems().add(ingredientsList.get(i));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    //Called when the user clicks on "go to pantry"
    public void goToPantry() throws Exception {
        //Returns the username
        String userName = obj3.getUser();
        //DAO calls the connection class
        //searching the user table for recipes, comparing against the username
        //All relevant recipes will be stored in the arraylist
        ArrayList<String> recipeList = daoObj.getRecipeFromUser(userName);
        //Set the chosen recipes for the user in the user ingredients object
        //this way the object can be passed, and that info can be accessed via "get"
        obj3.setChosenRecipesForPantry(recipeList);
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OverviewRecipesGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            OverviewRecipesGUIController overviewRecipesGUIControllerObj = loader.getController();
            overviewRecipesGUIControllerObj.setRecipeObjectForPantry(obj3);
            //sets the username
            overviewRecipesGUIControllerObj.setUserName(obj3.getUser());
            //sets the back objects, so again when pressing back, will take the user back to this page
            overviewRecipesGUIControllerObj.setBackObjects(obj3.getReturnImage(),obj3.getIngredientList(),obj3.getDirections());
            //page name is also used in the scheme of keeping track of what data will be sent where, based on the "back" action
            overviewRecipesGUIControllerObj.setPageName("DisplayFinalRecipeController");
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            overviewRecipesGUIControllerObj.fillListWithPantryItems();
            ((Stage)recipeIngredients.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method needed open a browser with the hyperlink
    @FXML
    public void goToWebPage(){
        //need to make an instance of application in order to have the hyperlink pop up in a web browser
        Application a = new Application() {
            @Override
            public void start(Stage stage) throws Exception { }
        };
        a.getHostServices().showDocument(hyperlink.getText());
    }
    //Method that could be used in the future for the spinner
//    public void getScore(){
//        System.out.println(spinner.getValue());
//    }
    //Image to be displayed representing the recipe
    //Since the image will be corresponding with the recipe was selected
    //This method is needed
    public void initializeScene(String str) throws FileNotFoundException {
        Image img = RecipePhotos.passBackImage(str);
        recipeImage.setImage(img);
    }
    //Take the user back to the choose ingredients page
    public void chooseIngredients(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            MainMenuGUIController scene1Controller = loader.getController();
            //set the username
            scene1Controller.setUserName(obj3.getUser());
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            //get the element and close the scene
            ((Stage)recipeIngredients.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //same concept here as the above method
    public void goToMainMenu(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfterLoginGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            AfterLoginGUIController scene4Controller = loader.getController();
            scene4Controller.setUserName(obj3.getUser());
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            ((Stage)recipeIngredients.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goToRecipesPage(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OverviewRecipesGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            OverviewRecipesGUIController scene3Controller = loader.getController();
            scene3Controller.setUserName(obj3.getUser());
            //back objects are needed here, if the user chooses to click "back" after navigating to the
            //that recipes page, and want to navigate back to this page
            scene3Controller.setBackObjects(obj3.getReturnImage(),obj3.getIngredientList(),obj3.getDirections());
            //Pass whatever data you want. You can have multiple method calls here
            scene3Controller.setRecipeObject(obj3);
            scene3Controller.setPageName("DisplayFinalRecipeGUIController");
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            scene3Controller.test();
            //close the scene
            ((Stage)recipeIngredients.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //initialize method is needed if changing elements of the FXML file after the stage has been set
    //and changing directly from the controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        spinner.setValueFactory(svf);
    }
}
