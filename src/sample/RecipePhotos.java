package sample;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/*
    Recipe photos class is used to display the images stored in the resource folder
    The string representation of the recipe is passed into the class methods
    Based on what was passed in, the correct relative path is selected
    and either the image view or the image is passed back
*/
public class RecipePhotos{
    private static String medSaladPhoto = "Resources/MedSalad.png";
    private static String sTacoPhoto = "Resources/Staco.png";
    private static String sBoil = "Resources/Sboil.png";
    private static String vEnch = "Resources/vegetarian-enchiladas.jpg";
    private static String cCurry = "Resources/Chickpea-Curry.jpg";
    private static String tPadThai = "Resources/tofupadthai.jpg";
    private static String pVodka = "Resources/pennevodka.jpg";
    private static Image image;
    private static ImageView img;
    private static FileInputStream input;
    public static ImageView passBackImageView(String str) throws FileNotFoundException {
        if(str.equalsIgnoreCase("Mediterranean Grilled Chicken Salad")){
            input = new FileInputStream(medSaladPhoto);
        }
        //Spicy shrimp tacos with garlic cilantro lime slaw
        else if(str.equalsIgnoreCase("Spicy shrimp tacos with garlic cilantro lime slaw")){
            input = new FileInputStream(sTacoPhoto);
        }else if(str.equalsIgnoreCase("Seafood Boil")){
            input = new FileInputStream(sBoil);
        }else if(str.equalsIgnoreCase("Veggie Black Bean Enchiladas")){
            input = new FileInputStream(vEnch);
        }else if(str.equalsIgnoreCase("Chickpea Curry")){
            input = new FileInputStream(cCurry);
        }else if(str.equalsIgnoreCase("Tofu Pad Thai")){
            input = new FileInputStream(tPadThai);
        }else if(str.equalsIgnoreCase("Penne Alla Vodka")){
            input = new FileInputStream(pVodka);
        }

        image = new Image(input);
        img = new ImageView(image);
        return img;
    }
    public static Image passBackImage(String str) throws FileNotFoundException{
        if(str.equalsIgnoreCase("Mediterranean Grilled Chicken Salad")){
            input = new FileInputStream(medSaladPhoto);
        }
        //Spicy shrimp tacos with garlic cilantro lime slaw
        else if(str.equalsIgnoreCase("Seafood Boil")){
            input = new FileInputStream(sBoil);
        }else if(str.equalsIgnoreCase("Spicy shrimp tacos with garlic cilantro lime slaw")){
            input = new FileInputStream(sTacoPhoto);
        }else if(str.equalsIgnoreCase("Veggie Black Bean Enchiladas")){
            input = new FileInputStream(vEnch);
        }else if(str.equalsIgnoreCase("Chickpea Curry")){
            input = new FileInputStream(cCurry);
        }else if(str.equalsIgnoreCase("Tofu Pad Thai")){
            input = new FileInputStream(tPadThai);
        }else if(str.equalsIgnoreCase("Penne Alla Vodka")){
            input = new FileInputStream(pVodka);
        }
        image = new Image(input);
        return image;
    }
}
