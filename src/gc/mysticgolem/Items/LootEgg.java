package gc.mysticgolem.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LootEgg implements Listener{

	private ItemStack lootEgg = new ItemStack(Material.EGG, 4);
	private Player p;
	private String eggName;
	private Random rand = new Random();
	private int randNum;
	private List<ItemStack> droppable = new ArrayList<ItemStack>();
	
	public LootEgg(){
		
		setMetaData();
		
	}
	
	public void setMetaData(){
		
		ItemMeta meta = getMetaData();
		List<String> lore = new ArrayList<String>();
		
		lore.add(ChatColor.DARK_PURPLE + "Gives random item from dungeon chest");
		lore.add(ChatColor.MAGIC + "lootEgg");
		
		meta.setDisplayName(ChatColor.GOLD + "Loot Egg");
		meta.setLore(lore);
		
		lootEgg.setItemMeta(meta);
		
	}
	
	public ItemMeta getMetaData(){
		ItemMeta met = lootEgg.getItemMeta();
		return met;
	}

	public ItemStack getLootEgg() {
		return lootEgg;
	}
	
	public void setPlayer(Player player){
		
		p = player;
		
	}
	
	public Player getThrower(){
		
		return p;
		
	}
	
	public void setEggName(String name){
		eggName = name;
	}
	
	public String getEggName(){
		return eggName;
	}
	
	public void dropItem(Location loc){ //Tells egg what items to drop
		
		Player p = getThrower();
		ItemStack droppedItem;
		
		droppable.clear();
		
		addItem(Material.IRON_INGOT, 8, 8, 3); //Item, weight, maximum stack size, minimum stack size
		addItem(Material.GOLD_INGOT, 5, 5, 1);
		addItem(Material.DIAMOND, 3, 3, 1);
		addItem(Material.GOLDEN_APPLE, 1, 1, 1);
		addItem(Material.ROTTEN_FLESH, 10, 5, 1);
		addItem(Material.BREAD, 15, 32, 15);
		addItem(Material.BONE, 10, 5, 1);
		addItem(Material.SULPHUR, 10, 5, 1);
		addItem(Material.SADDLE, 1, 1, 1);
		
		int Size = droppable.size();
		randNum = ThreadLocalRandom.current().nextInt(Size);
		
		droppedItem = droppable.get(randNum);
		
		p.getWorld().dropItem(loc, droppedItem);
		p.getWorld().dropItem(loc, new ItemStack(Material.GOLD_NUGGET, rand.nextInt(10) + 1));
		
	}
	
	public void addItem(Material item, int weight, int maxstack, int minstack){
		
		if(weight != 0 || maxstack < 1 || minstack < 1){
			
			if(maxstack == minstack){
				for(int w = 1; w <= weight; w++){
					droppable.add(new ItemStack(item, maxstack));
				}
			}else{
				
				for(int w = 1; w <= weight; w++){
					randNum = ThreadLocalRandom.current().nextInt(minstack, maxstack+1);
					droppable.add(new ItemStack(item, randNum));
					
				}
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e){
		
		Player player = e.getPlayer();
		setPlayer(player);
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEggLaunch(ProjectileLaunchEvent e){
		
		Entity egg = e.getEntity();
		Player player = getThrower();
		ItemStack item = player.getItemInHand(); 
		ItemMeta meta;
		List<String> lor;
		
		if(item.hasItemMeta()){
			meta = item.getItemMeta();
			if(meta.hasLore()){
				lor = meta.getLore();
				if(lor.contains(ChatColor.MAGIC + "lootEgg")){
					egg.setCustomName(ChatColor.MAGIC + "lootEgg");
					egg.setGlowing(true);
				}
			}
		}
		
	}

	
	@EventHandler
	public void onEggThrown(PlayerEggThrowEvent e){
		
		Entity egg = e.getEgg();
		
		if(e.getEgg().getCustomName().contains(ChatColor.MAGIC + "lootEgg")){
			e.setHatching(false);
			
			Location loc = egg.getLocation();
			
			dropItem(loc);
			
		}
		
	}
	
}