package sample;
import java.util.ArrayList;
/*
    UserIngredients class is used to hold the username, recipe, directions and ingredient lists with their corresponding getters and setters
    Based on whatever the scenario is, the following data members are crucial to functionality of the application
    For future use, the class name will need to be changed throughout the java files to something more along the lines of
    "User Info"
*/
public class UserIngredients{
    private ArrayList<String> ingredients;
    private ArrayList<String> chosenRecipes;
    private ArrayList<String> pantryList;
    private String userName;
    private String tempRecipe;
    private String prevPage;
    private String imageGoingBack;
    private ArrayList<String> ingredientListGoingBack;
    private ArrayList<String> directionListGoingBack;
    protected UserIngredients(){
        this.ingredients = new ArrayList<>();
        this.chosenRecipes = new ArrayList<>();
    }
    protected void setPrevPage(String page) {
        this.prevPage = page;
    }
    protected String getPrevPage() {
        return prevPage;
    }
    protected void setTempRecipe(String recipe){
        this.tempRecipe = recipe;
    }
    protected String getTempRecipe(){
        return tempRecipe;
    }
    protected void setUser(String userName){
        this.userName = userName;
    }
    protected String getUser(){
        return userName;
    }
    protected void addIngredient(String str){
        ingredients.add(str);
    }
    protected ArrayList<String> getIngredients(){
        return ingredients;
    }
    protected void setChosenRecipes(ArrayList<String> chosenList){
        this.chosenRecipes = chosenList;
    }
    protected void setChosenRecipesForPantry(ArrayList<String> chosenList){
        this.pantryList = chosenList;
    }
    protected String getARecipe(int index){
        return chosenRecipes.get(index);
    }
    protected String getARecipeFromPantry(int index){
        return pantryList.get(index);
    }
    protected ArrayList<String> getChosenRecipesFromPantry(){
        return pantryList;
    }
    protected ArrayList<String> getChosenRecipes(){
        return chosenRecipes;
    }
    protected void deleteAll(){
        ingredients.clear();
    }
    protected void initializeBackItems(String imageGoingBack, ArrayList<String> ingredientListGoingBack, ArrayList<String> directionListGoingBack){
        this.imageGoingBack = imageGoingBack;
        this.ingredientListGoingBack = ingredientListGoingBack;
        this.directionListGoingBack = directionListGoingBack;
    }
    protected String getReturnImage(){
        return imageGoingBack;
    }
    protected ArrayList<String> getIngredientList(){
        return ingredientListGoingBack;
    }
    protected ArrayList<String> getDirections(){
        return directionListGoingBack;
    }
}
