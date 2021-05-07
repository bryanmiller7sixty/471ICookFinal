package sample;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/*
    Controls the functionality of the ingredient selection menu
    Updates the view based on user selection of ingredients
*/
public class MainMenuGUIController implements Initializable {
    UserIngredients ingredientsObject = new UserIngredients();
    //All FXML elements which will be altered after the stage is already created
    @FXML
    Pane p1;
    @FXML
    private ListView<String> pro;
    @FXML
    private ListView<String> veg;
    @FXML
    private ListView<String> carbs;
    @FXML
    private ListView<String> sauces;
    @FXML
    FlowPane proteinFlowPane;
    @FXML
    FlowPane vegFlowPane;
    @FXML
    FlowPane carbsFlowPane;
    @FXML
    FlowPane saucesFlowPane;
    //Sets username, which makes it possible for the user to navigate to their pantry and have it populated
    public void setUserName(String user){
        ingredientsObject.setUser(user);
    }
    //Updates the text flow pane based on any radio button that was selected
    @FXML
    public void updateIngredients () {
        ObservableList<Node> list1 = proteinFlowPane.getChildren();
        ObservableList<Node> list2 = vegFlowPane.getChildren();
        ObservableList<Node> list3 = carbsFlowPane.getChildren();
        ObservableList<Node> list4 = saucesFlowPane.getChildren();
        //create list of nodes for all ingredient flow panes
        //delete all of the previous objects data and clear the entire list view containing all ingredients
        ingredientsObject.deleteAll();
        clearTable();
        //loop through each corresponding list casting each node element to a radio button
        for (int i = 0; i < list1.size(); i++) {
            Node n1 = list1.get(i);
            RadioButton c1 = (RadioButton) n1;
            if (c1.isSelected()) {
                //if the button is selected
                //adds the id of the ingredients to the users current ingredient list
                //as well as adds to the list view containing the text representation of all the current ingredients
                pro.getItems().add(String.valueOf(list1.get(i).getId()));
                ingredientsObject.addIngredient(String.valueOf(list1.get(i).getId()));
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            Node n1 = list2.get(i);
            RadioButton c1 = (RadioButton) n1;
            if (c1.isSelected()) {
                veg.getItems().add(String.valueOf(list2.get(i).getId()));
                ingredientsObject.addIngredient(String.valueOf(list2.get(i).getId()));
            }
        }
        for (int i = 0; i < list3.size(); i++) {
            Node n1 = list3.get(i);
            RadioButton c1 = (RadioButton) n1;
            if (c1.isSelected()) {
                carbs.getItems().add(String.valueOf(list3.get(i).getId()));
                ingredientsObject.addIngredient(String.valueOf(list3.get(i).getId()));
            }
        }
        for (int i = 0; i < list4.size(); i++) {
            Node n1 = list4.get(i);
            RadioButton c1 = (RadioButton) n1;
            if (c1.isSelected()) {
                sauces.getItems().add(String.valueOf(list4.get(i).getId()));
                ingredientsObject.addIngredient(String.valueOf(list4.get(i).getId()));
            }
        }
    }
    //clear table method in case user doesn't want to uncheck every radio button
    //Also used as a utility method
    @FXML
    private void clearTable () {
        pro.getItems().clear();
        veg.getItems().clear();
        carbs.getItems().clear();
        sauces.getItems().clear();
        ingredientsObject.deleteAll();
    }
    //Method to deselect all radio buttons
    @FXML
    private void clearTable2 () {
        ObservableList<Node> list1 = proteinFlowPane.getChildren();
        ObservableList<Node> list2 = vegFlowPane.getChildren();
        ObservableList<Node> list3 = carbsFlowPane.getChildren();
        ObservableList<Node> list4 = saucesFlowPane.getChildren();
        for (int i = 0; i < list1.size(); i++) {
            Node n1 = list1.get(i);
            RadioButton c1 = (RadioButton) n1;
            c1.setSelected(false);
        }
        for (int i = 0; i < list2.size(); i++) {
            Node n1 = list2.get(i);
            RadioButton c1 = (RadioButton) n1;
            c1.setSelected(false);
        }
        for (int i = 0; i < list3.size(); i++) {
            Node n1 = list3.get(i);
            RadioButton c1 = (RadioButton) n1;
            c1.setSelected(false);
        }
        for (int i = 0; i < list4.size(); i++) {
            Node n1 = list4.get(i);
            RadioButton c1 = (RadioButton) n1;
            c1.setSelected(false);
        }
        clearTable();
    }
    //Closes this scene and opens the overview recipes page, and will populate it based in the information
    //captured by the user specified ingredients
    public void loadSecond() throws IOException {
        try {
            //Load second scene
            populate();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OverviewRecipesGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            OverviewRecipesGUIController scene2Controller = loader.getController();
            scene2Controller.setUserName(ingredientsObject.getUser());
            //Pass whatever data you want. You can have multiple method calls here
            //sets the recipe object and all the data contained in the object
            //which will be utilized in the next scene
            scene2Controller.setRecipeObject(ingredientsObject);
            //page name is to keep track of pages being navigated to-from
            //so the data being displayed to the user is correct
            scene2Controller.setPageName("MainMenuGUIController");
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            scene2Controller.test();
            //reference an element and close the scene
            ((Stage)p1.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Goes back to the main menu screen i.e. sign out, about us etc.
    @FXML
    public void goBack(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfterLoginGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            AfterLoginGUIController firstSceneController = loader.getController();
            firstSceneController.setUserName(ingredientsObject.getUser());
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            ((Stage)pro.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void populate() throws Exception {
//        pass chosen ingredients to the sort titles method, the sort titles method then calls the database and compares the ingredients
//        which we have passed with the ingredients from each recipe, if there's a match then we add the recipe_id to another list.
//        That list then calls the database again, and returns the recipe_name which will then be displayed
        ControllersDAO obj = new ControllersDAO();
        ingredientsObject.setChosenRecipes(obj.getRecipeTitles(ingredientsObject.getIngredients()));
    }
    //Even though was never used, still need to implement, when implementing intializable
    @Override
    public void initialize (URL url, ResourceBundle rb){
    }
}
