package gc.customplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import gc.customplugin.Items.LootEgg;
import gc.customplugin.Items.SafariNet;
import gc.customplugin.events.LayEggEvent;

public class CustomCrafting extends JavaPlugin{

	public static final double VERSION = 1.0;
	
	public ShapedRecipe lootEggRec;
	public ShapedRecipe safariNetRec;
	
	@Override
	public void onEnable(){
		getLogger().info("Custom Plugin by MysticGolem and TheZoq2 has been enabled");
		
		registerEvents();
		registerRecipes();
		
	}
	
	@Override
	public void onDisable(){
		getLogger().info("Custom Crafting by MysticGolem and TheZoq2 has been disabled");
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("customplugin") && sender instanceof Player){
			
			Player player = (Player) sender;
			
			player.sendMessage("Custom Plugin is enabled and currently in version " + VERSION);
			
			return true;
			
		}
		
		return false;
	}

	public void registerEvents(){
		
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new LootEgg(), this);
		pm.registerEvents(new SafariNet(), this);
		pm.registerEvents(new LayEggEvent(), this);

	}
	
	public void registerRecipes(){
		
		CraftingMethods cMeth = new CraftingMethods();
		LootEgg lEgg = new LootEgg();
		SafariNet sNet = new SafariNet();
		
		ItemStack lootEgg = lEgg.getLootEgg();
		ItemStack safariNet = sNet.getSafariNet();
		
		ShapedRecipe lootEggRecipe = cMeth.createRecipe(lootEgg, lootEggRec, cMeth.lootEggRec, cMeth.lootEggChars, cMeth.lEggMat);
		ShapedRecipe safariNetRecipe = cMeth.createRecipe(safariNet, safariNetRec, cMeth.safariNetRec, cMeth.safariNetChars, cMeth.safariNetMat);
		
		getServer().addRecipe(lootEggRecipe);
		getServer().addRecipe(safariNetRecipe);
		
	}
	
}
