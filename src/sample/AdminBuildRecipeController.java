package sample;
import Builder.Recipe;
import Builder.RecipeBuilder;
import Command_Factory.*;
import Connector.ConnectionToMYSQLDB;
import Memento.AdminOriginator;
import Memento.AdminCareTaker;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class AdminBuildRecipeController {
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    private Alert information = new Alert(Alert.AlertType.INFORMATION);
    boolean flag = false;
    private List<String> directionList = new ArrayList<>();
    private List<String> ingredientType = new ArrayList<>();
    private List<String> ingredientName = new ArrayList<>();
    private List<String> ingredientQuantity = new ArrayList<>();
    private String title;
    private String authorRec;
    private CommandManager commandManager = new CommandManager();
    private AdminOriginator originator = AdminOriginator.getInstance();
    private AdminCareTaker caretaker = AdminCareTaker.getInstance();
    @FXML
    TextField recipeTitle, author;
    @FXML
    TextArea authorArea, titleArea;
    @FXML
    TextField quant, ingName, directions;
    @FXML
    ListView ingredientListView, directionView;
    @FXML
    CheckBox Protein, Fruits_veg, Carbs, Sauce_other;
    @FXML
    MenuButton commandDirection, commandIngredient;
    @FXML
    public void addRAToFlow(){
        String raRecipeTitle = recipeTitle.getText();
        String raAuthor = author.getText();
        if(raAuthor.equals("") || raRecipeTitle.equals("")){
            errorAlert.setHeaderText("Invalid option!");
            errorAlert.setContentText("Both fields must be full.");
            errorAlert.showAndWait();
        }
        authorArea.setText(raAuthor);
        titleArea.setText(raRecipeTitle);
        authorArea.setFont(Font.font("cambria", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        titleArea.setFont(Font.font("cambria", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        title = raRecipeTitle;
        authorRec = raAuthor;
    }
    @FXML
    public void addToIngFlow(){
        int check = 0;
        String typeIng = "";
        if(Protein.isSelected()){
            check+=1;
            typeIng = Protein.getId();
        }
        if(Fruits_veg.isSelected()){
            check+=1;
            typeIng = Fruits_veg.getId();
        }
        if(Carbs.isSelected()){
            check+=1;
            typeIng = Carbs.getId();
        }
        if(Sauce_other.isSelected()){
            check+=1;
            typeIng = Sauce_other.getId();
        }
        if(check > 1 || check == 0){
            errorAlert.setHeaderText("Invalid option!");
            errorAlert.setContentText("Must only have one checkbox displayed.");
            errorAlert.showAndWait();
        }else{
            if(ingName.getText().equals("") || quant.getText().equals("")){
                errorAlert.setHeaderText("Invalid option!");
                errorAlert.setContentText("Cannot add empty name or quantity.");
                errorAlert.showAndWait();
            }else{
                //Create the CommandFactoryIF object
                CommandFactoryIF utility = Utility_Factory_Method.createCommandFactoryObject();
                //Create an arraylist to pass in to the arguments of type, name and quant.
                ArrayList<String> ingL = new ArrayList<>();
                ingL.add(typeIng); ingL.add(ingName.getText()); ingL.add(quant.getText());
                //Get your command object masked as an commandIF class, pass in your arguments, regarding what object your requesting
                //Here we want an ingredient object
                CommandIF commandIF = utility.createCommand("Ingredient", ingL);
                //Create new menu item (The string for the add command we just created)
                MenuItem menuItem = new MenuItem();
                //Set the text of the menu item to the toString of the command
                menuItem.setText(commandIF.toString());
                //Need to set the current menu item to access the index later on for deletion from list
                commandIF.setMenuItem(menuItem);
                //Set an action listener for on click, call the undo command method
                menuItem.setOnAction(event -> {
                    //update the command manager to remove the items up the index the user requests
                    /*
                    **********************************
                     */
                    //This might need to change because the client or controller now knows we are removing from the ingredient list
                    commandManager.updateIngredientCommandList(commandIngredient.getItems().indexOf(menuItem));
                    commandIF.undo(commandIngredient, ingredientListView);
                    //We need to make sure every time we add or delete anything or direction list corresponds with out changes
                    //This way we can have a check that ensures the client does not add any empty data set to the DB
                    ingredientName = commandManager.getNameForIngredient();
                    ingredientType = commandManager.getTypeForIngredient();
                    ingredientQuantity = commandManager.getQuanForIngredient();
                });
                //Add the current command we just added to the command manager
                commandManager.addIngredientCommand(commandIF);
                //Add the command string to the list view
                //directionView.getItems().add(directions.getText());
                ingredientListView.getItems().add("Ing. type: " + typeIng + "\t\tIng. name: " + ingName.getText() + "\t\tIng. quant: " + quant.getText());
                //Finally add to the menu item the command we just created (since this is the most recent)
                commandIngredient.getItems().add(0, menuItem);
                //We need to make sure every time we add or delete anything or direction list corresponds with out changes
                //This way we can have a check that ensures the client does not add any empty data set to the DB
                ingredientName = commandManager.getNameForIngredient();
                ingredientType = commandManager.getTypeForIngredient();
                ingredientQuantity = commandManager.getQuanForIngredient();
            }
        }
    }
    @FXML
    public void addToDirectionView(){
        if(directions.getText().equals("")){
            errorAlert.setHeaderText("Invalid option!");
            errorAlert.setContentText("Cannot add empty direction");
            errorAlert.showAndWait();
        }else{
            //Section pretty much mirrors what is happening above, except for the direction now instead of ingredient
            CommandFactoryIF utility = Utility_Factory_Method.createCommandFactoryObject();
            ArrayList<String> dList = new ArrayList<>();
            dList.add(directions.getText());
            CommandIF commandIF = (CommandIF) utility.createCommand("Direction", dList);
            //Create new menu item (The string for the add command we just created)
            MenuItem menuItem = new MenuItem();
            //Set the text of the menu item to the toString of the command
            menuItem.setText(commandIF.toString());
            //Need to set the current menu item to access the index later on for deletion from list
            commandIF.setMenuItem(menuItem);
            //Set an action listener for on click, call the undo command method
            menuItem.setOnAction(event -> {
                commandManager.updateAddDirectionCommandList(commandDirection.getItems().indexOf(menuItem));
                commandIF.undo(commandDirection, directionView);
                //We need to make sure every time we add or delete anything or direction list corresponds with out changes
                //This way we can have a check that ensures the client does not add any empty data set to the DB
                directionList = commandManager.getDirectionList();
            });
            //Add the current command we just added to the command manager
            commandManager.addDirectionCommand(commandIF);
            //Add the command string to the list view
            directionView.getItems().add(directions.getText());
            //Finally add to the menu item the command we just created (since this is the most recent)
            commandDirection.getItems().add(0, menuItem);
            //We need to make sure every time we add or delete anything our direction list corresponds with out changes
            //This way we can have a check that ensures the client does not add any empty data set to the DB
            directionList = commandManager.getDirectionList();
        }
    }
    @FXML
    public void addToDB() throws Exception {
        //Check if all fields are not empty i.e. no empty insertions into DB
        if (authorRec == null || title == null || directionList.isEmpty() || ingredientType.isEmpty()) {
            //When this block executes it means one of the above fields were empty
            errorAlert.setHeaderText("Invalid option!");
            errorAlert.setContentText("Cannot add finalize results with empty fields");
            errorAlert.showAndWait();
        } else {
            //Get the max primary keys, since they are auto incremented this works
            ArrayList<Integer> maxList = ConnectionToMYSQLDB.getMaxes();
            //Just add 1 to each to satisfy the new values
            int primaryRecipeDirections = maxList.get(0) + 1;
            int primaryRecipeID = maxList.get(1) + 1;
            int primaryIngredientID = maxList.get(2) + 1;
            //Build the recipeBuilder object
            Recipe recipeBuilder = new RecipeBuilder().setAuthor(authorRec).setTitle(title).
                    setDirectionList(directionList).setIngredientTypeList(ingredientType).setIngredientNameList(ingredientName).setIngredientQuantityList(ingredientQuantity).
                    setPrimaryRecipeDirections(primaryRecipeDirections).setPrimaryIngredientID(primaryIngredientID).setPrimaryRecipeID(primaryRecipeID).build();
            //Confirm with the user if they would like to continue
            //This is the last check before adding to DB
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you would like to add this?" + "\n");
            alert.setContentText(recipeBuilder.toString());
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            //Final step for inserting after administrator confirms
            if (button == ButtonType.OK) {
                prepDataForDatabase(recipeBuilder);
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                errorAlert.setHeaderText("Successfully added to the database");
                errorAlert.showAndWait();
            }
        }
    }
    public boolean prepDataForDatabase(Recipe builtRecipe){
        try{
            ConnectionToMYSQLDB.insertIntoRecipesAdmin(builtRecipe.getPrimaryRecipeID(), builtRecipe.getTitle(), builtRecipe.getAuthor());
            List<String> ingQList = builtRecipe.getIngredientQuantityList();
            List<String> ingNList = builtRecipe.getIngredientNameList();
            List<String> ingTList = builtRecipe.getIngredientTypeList();
            List<String> dList = builtRecipe.getDirectionList();
            int primaryIngredientID = builtRecipe.getPrimaryIngredientID();
            int primaryRecipeDirections = builtRecipe.getPrimaryRecipeDirections();
            Thread insertIngredients = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < ingQList.size(); i++){
                        try {
                            ConnectionToMYSQLDB.insertIntoRecipeIngredientsAdmin(primaryIngredientID + i, ingTList.get(i), ingQList.get(i), ingNList.get(i), builtRecipe.getPrimaryRecipeID());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            Thread insertDirections = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < dList.size(); i++){
                        try {
                            ConnectionToMYSQLDB.insertIntoRecipeDirectionsAdmin(i+1, dList.get(i), primaryRecipeDirections + i, builtRecipe.getTitle());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            insertIngredients.start();
            insertDirections.start();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    @FXML
    public void signOut(){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Save progress?");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            //Final step for inserting after administrator confirms
            if (button == ButtonType.OK) {
                saveProgress();
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                errorAlert.setHeaderText("Successfully saved");
                errorAlert.showAndWait();
            }
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
            ((Stage)recipeTitle.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void saveProgress() {
        flag = true;
        originator.setFlag(flag);
        originator.setTitle(title);
        originator.setAuthorRec(authorRec);
        originator.setIngredientType(ingredientType);
        originator.setIngredientName(ingredientName);
        originator.setIngredientQuantity(ingredientQuantity);
        originator.setDirectionList(directionList);
        originator.setCommandManager(commandManager);
        caretaker.add(originator.saveStateToMemento());
    }
    @FXML
    public void restoreProgress() {
        if(originator.getFlag() == true) {
            originator.getStateFromMemento(caretaker.getMemento());
            String raRecipeTitle = originator.getTitle();
            String raAuthor = originator.getAuthorRec();
            authorArea.setText(raAuthor);
            titleArea.setText(raRecipeTitle);
            authorArea.setFont(Font.font("cambria", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            titleArea.setFont(Font.font("cambria", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            title = raRecipeTitle;
            authorRec = raAuthor;

            //Create the CommandFactoryIF object
            CommandFactoryIF utility1 = Utility_Factory_Method.createCommandFactoryObject();
            //Create an arraylist to pass in to the arguments of type, name and quant.
            ArrayList<String> ingL = new ArrayList<>();
            String ingType;
            String ingName;
            String ingQuant;
            for(int i = 0; i < originator.getIngredientType().size(); i++) {
                ingType = originator.getIngredientType().get(i);
                ingName = originator.getIngredientName().get(i);
                ingQuant = originator.getIngredientQuantity().get(i);
                ingL.add(ingType); ingL.add(ingName); ingL.add(ingQuant);
                //Get your command object masked as an commandIF class, pass in your arguments, regarding what object your requesting
                //Here we want an ingredient object
                CommandIF commandIF = utility1.createCommand("Ingredient", ingL);
                //Create new menu item (The string for the add command we just created)
                MenuItem menuItem = new MenuItem();
                //Set the text of the menu item to the toString of the command
                menuItem.setText(originator.getIngredientType().get(i));
                //Need to set the current menu item to access the index later on for deletion from list
                commandIF.setMenuItem(menuItem);
                //Set an action listener for on click, call the undo command method
                menuItem.setOnAction(event -> {
                    //update the command manager to remove the items up the index the user requests
                    /*
                    **********************************
                     */
                    //This might need to change because the client or controller now knows we are removing from the ingredient list
                    commandManager.updateIngredientCommandList(commandIngredient.getItems().indexOf(menuItem));
                    commandIF.undo(commandIngredient, ingredientListView);
                    //We need to make sure every time we add or delete anything or direction list corresponds with out changes
                    //This way we can have a check that ensures the client does not add any empty data set to the DB
                    ingredientName = commandManager.getNameForIngredient();
                    ingredientType = commandManager.getTypeForIngredient();
                    ingredientQuantity = commandManager.getQuanForIngredient();
                });
                //Add the current command we just added to the command manager
                commandManager.addIngredientCommand(commandIF);
                //Add the command string to the list view
                //directionView.getItems().add(directions.getText());
                ingredientListView.getItems().add("Ing. type: " + ingType + "\t\t\tIng. name: " + ingName + "\t\t\tIng. quant: " + ingQuant);
                //Finally add to the menu item the command we just created (since this is the most recent)
                commandIngredient.getItems().add(0, menuItem);
                //We need to make sure every time we add or delete anything or direction list corresponds with out changes
                //This way we can have a check that ensures the client does not add any empty data set to the DB
                ingredientName = commandManager.getNameForIngredient();
                ingredientType = commandManager.getTypeForIngredient();
                ingredientQuantity = commandManager.getQuanForIngredient();
                ingL.clear();
            }

            CommandFactoryIF utility2 = Utility_Factory_Method.createCommandFactoryObject();
            ArrayList<String> dList = new ArrayList<>();
            String eachdirections;
            for(int i = 0; i < originator.getDirectionList().size(); i++) {
                eachdirections = originator.getDirectionList().get(i);
                dList.add(eachdirections);
                CommandIF commandIF = utility2.createCommand("Direction", dList);
                //Create new menu item (The string for the add command we just created)
                MenuItem menuItem = new MenuItem();
                //Set the text of the menu item to the toString of the command
                menuItem.setText(originator.getDirectionList().get(i));
                //Need to set the current menu item to access the index later on for deletion from list
                commandIF.setMenuItem(menuItem);
                //Set an action listener for on click, call the undo command method
                menuItem.setOnAction(event -> {
                    commandManager.updateAddDirectionCommandList(commandDirection.getItems().indexOf(menuItem));
                    commandIF.undo(commandDirection, directionView);
                    //We need to make sure every time we add or delete anything or direction list corresponds with out changes
                    //This way we can have a check that ensures the client does not add any empty data set to the DB
                    directionList = commandManager.getDirectionList();
                });
                //Add the current command we just added to the command manager
                commandManager.addDirectionCommand(commandIF);
                //add the command string to the list view
                directionView.getItems().add(eachdirections);
                //Finally add to the menu item the command we just created (since this is the most recent)
                commandDirection.getItems().add(0, menuItem);
                //We need to make sure every time we add or delete anything our direction list corresponds with out changes
                //This way we can have a check that ensures the client does not add any empty data set to the DB
                directionList = commandManager.getDirectionList();
                dList.clear();
            }
            originator.setFlag(false);
        } 
    }
    public void restoreProgressProcess(){
        if(!originator.getFlag()){
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Restore previous progress? ");
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        //Final step for inserting after administrator confirms
        if (button == ButtonType.OK) {
            restoreProgress();
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setHeaderText("Successfully restored");
            errorAlert.showAndWait();
        }
    }
}
