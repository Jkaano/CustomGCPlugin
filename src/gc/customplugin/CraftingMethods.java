package gc.customplugin;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftingMethods {
	
	String lootEggRec[] = {"aaa", "aba", "aaa"};
	char lootEggChars[] = {'a', 'b'};
	Material lEggMat[] = {Material.GOLD_INGOT, Material.EGG};
	
	String safariNetRec[] = {"aba", "bcb", "aba"};
	char safariNetChars[] = {'a', 'b', 'c'};
	Material safariNetMat[] = {Material.STRING, Material.IRON_INGOT, Material.LEASH};
	
	public ShapedRecipe createRecipe(ItemStack product, ShapedRecipe recipe, String[] rec, char[] recipeComp, Material[] types){
		
		int recipeComplexity = getRecipeComplexity(recipeComp); //# of different items used in recipe
		
		recipe = new ShapedRecipe(product).shape(rec);
		
		for(int i = 0; i < recipeComplexity; i++){
			
			recipe.setIngredient(recipeComp[i], types[i]);
			
		}
		
		return recipe;
		
	}
	
	private int getRecipeComplexity(char[] recipe){
		
		return recipe.length;
		
	}
	
}
