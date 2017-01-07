package gc.customplugin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import gc.customplugin.Items.LootEgg;

public class CustomCrafting extends JavaPlugin{

	public static final double VERSION = 1.0;
	
	public ShapedRecipe lootEggRec;
	public ShapedRecipe diamondRec;
	
	@Override
	public void onEnable(){
		getLogger().info("Custom Crafting by MysticGolem has been enabled");
		
		registerEvents();
		registerRecipes();
		
	}
	
	@Override
	public void onDisable(){
		getLogger().info("Custom Crafting by MysticGolem has been disabled");
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("customcrafting") && sender instanceof Player){
			
			Player player = (Player) sender;
			
			player.sendMessage("Custom Crafting is enabled and currently in version " + VERSION);
			
			return true;
			
		}
		
		return false;
	}

	public void registerEvents(){
		
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new LootEgg(), this);
	}
	
	public void registerRecipes(){
		
		CraftingMethods cMeth = new CraftingMethods();
		LootEgg lEgg = new LootEgg();
		
		ItemStack diamond = new ItemStack(Material.DIAMOND);
		ItemStack lootEgg = lEgg.getLootEgg();
		
		ShapedRecipe diamondRecipe = cMeth.createRecipe(diamond, diamondRec, cMeth.diamondRec, cMeth.diamondRecChars, cMeth.dRecMat);
		ShapedRecipe lootEggRecipe = cMeth.createRecipe(lootEgg, lootEggRec, cMeth.lootEggRec, cMeth.lootEggChars, cMeth.lEggMat);
		
		getServer().addRecipe(diamondRecipe);
		getServer().addRecipe(lootEggRecipe);
		
	}
	
}
