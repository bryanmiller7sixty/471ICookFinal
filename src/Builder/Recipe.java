package Builder;
import java.util.List;
public class Recipe {
    private final List<String> directionList;
    private final List<String> ingredientTypeList;
    private final List<String> ingredientNameList;
    private final List<String> ingredientQuantityList;
    private final String title;
    private final String author;
    private final int primaryRecipeDirections;
    private final int primaryRecipeID;
    private final int primaryIngredientID;
    public Recipe(RecipeBuilder builder){
        this.directionList = builder.getDirectionList();
        this.ingredientTypeList = builder.getIngredientTypeList();
        this.ingredientNameList = builder.getIngredientNameList();
        this.ingredientQuantityList = builder.getIngredientQuantityList();
        this.title = builder.getTitle();
        this.author = builder.getAuthor();
        this.primaryRecipeDirections = builder.getPrimaryRecipeDirections();
        this.primaryRecipeID = builder.getPrimaryRecipeID();
        this.primaryIngredientID = builder.getPrimaryIngredientID();
    }
    public List<String> getDirectionList(){
        return directionList;
    }
    public List<String> getIngredientTypeList(){
        return ingredientTypeList;
    }

    public List<String> getIngredientQuantityList() {
        return ingredientQuantityList;
    }

    public List<String> getIngredientNameList() {
        return ingredientNameList;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrimaryRecipeID() {
        return primaryRecipeID;
    }

    public int getPrimaryRecipeDirections() {
        return primaryRecipeDirections;
    }

    public int getPrimaryIngredientID() {
        return primaryIngredientID;
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Title: " + title + "\t\t");
        builder.append("Author: " + author + "\t\t");
        builder.append("Recipe ID: " + primaryRecipeID + "\n");
        builder.append("Ingredients: " + "\n \n");
        //ConnectionToMYSQLDB.insertIntoRecipesAdmin
        //Does not matter what list we use, they will all be of the same length
        String buildString = "";
        for(int i = 0; i < ingredientQuantityList.size(); i++){
            buildString += ingredientTypeList.get(i);
            buildString += " ";
            buildString += ingredientNameList.get(i);
            buildString += " ";
            buildString += ingredientQuantityList.get(i) + "\n";
            builder.append(buildString);
            buildString = "";
        }
        builder.append("\nDirections: " + "\n \n");
        for(int i = 0; i < directionList.size(); i++){
            builder.append("Step number: " + (i+1));
            builder.append("\t\t" + (directionList.get(i)));
            builder.append("\n");
        }
        return builder.toString();
    }
}
