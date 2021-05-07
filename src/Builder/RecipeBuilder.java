package Builder;

import java.util.List;

public class RecipeBuilder {
    private List<String> directionList;
    private List<String> ingredientTypeList;
    private List<String> ingredientNameList;
    private List<String> ingredientQuantityList;
    private String title;
    private String author;
    private int primaryRecipeDirections;
    private int primaryRecipeID;
    private int primaryIngredientID;

    public RecipeBuilder setDirectionList(List<String> directionList) {
        this.directionList = directionList;
        return this;
    }

    public RecipeBuilder setIngredientTypeList(List<String> ingredientTypeList) {
        this.ingredientTypeList = ingredientTypeList;
        return this;
    }

    public RecipeBuilder setIngredientNameList(List<String> ingredientNameList) {
        this.ingredientNameList = ingredientNameList;
        return this;
    }

    public RecipeBuilder setIngredientQuantityList(List<String> ingredientQuantityList) {
        this.ingredientQuantityList = ingredientQuantityList;
        return this;
    }

    public List<String> getDirectionList() {
        return directionList;
    }

    public List<String> getIngredientTypeList() {
        return ingredientTypeList;
    }

    public List<String> getIngredientNameList() {
        return ingredientNameList;
    }

    public List<String> getIngredientQuantityList() {
        return ingredientQuantityList;
    }

    public String getAuthor() {
        return author;
    }

    public RecipeBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public RecipeBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RecipeBuilder setPrimaryIngredientID(int primaryIngredientID) {
        this.primaryIngredientID = primaryIngredientID;
        return this;
    }

    public int getPrimaryIngredientID() {
        return primaryIngredientID;
    }

    public RecipeBuilder setPrimaryRecipeDirections(int primaryRecipeDirections) {
        this.primaryRecipeDirections = primaryRecipeDirections;
        return this;
    }

    public int getPrimaryRecipeDirections() {
        return primaryRecipeDirections;
    }

    public RecipeBuilder setPrimaryRecipeID(int primaryRecipeID) {
        this.primaryRecipeID = primaryRecipeID;
        return this;
    }

    public int getPrimaryRecipeID() {
        return primaryRecipeID;
    }

    public Recipe build(){
        return new Recipe(this);
    }
}
