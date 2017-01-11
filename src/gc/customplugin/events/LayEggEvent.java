package gc.customplugin.events;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

import gc.customplugin.Items.LootEgg;

public class LayEggEvent implements Listener{
	
	private boolean fromDispenser = false;
	LootEgg lootEgg = new LootEgg();
	
	@EventHandler
	public void onLayEgg(ItemSpawnEvent e){
		
		Item item = e.getEntity();
		ItemStack stack = item.getItemStack();
		Material mat = stack.getType();
		boolean change = false;
		int randNum;
		
		if(mat == Material.EGG && !fromDispenser){

			List<Entity> nearbyEnt = new ArrayList<Entity>();
			
			change = checkNearbyEntities(nearbyEnt, item, 10.0);
			
			if(change){
				change = checkNearbyEntities(nearbyEnt, item, 0.001);
				
				if(change){
					randNum = ThreadLocalRandom.current().nextInt(1, 100+1);
					if(randNum == 1){
						item.setItemStack(lootEgg.getLootEgg());						
					}
				}
				
			}
			
		}
		
		if(fromDispenser){
			fromDispenser = false;
		}
		
	}
	
	@EventHandler
	public void checkIfDispensed(BlockDispenseEvent e){
		
		ItemStack item = e.getItem();
		Material mat = item.getType();
		
		if(mat == Material.EGG){
			fromDispenser = true;
		}else{
			fromDispenser = false;
		}
		
	}
	
	public boolean checkNearbyEntities(List<Entity> entities, Item item, double radius){
		entities.clear();
		entities.addAll(item.getNearbyEntities(radius, radius, radius));
		int nChick = 0;
		for(int i = 0; i < entities.size(); i++){
			
			if(entities.get(i) instanceof Chicken){
				nChick++;
				if(nChick > 1){
					break;
				}
			}
			
		}
		
		if(nChick == 1){
			return true;
		}else{
			return false;
		}
		
	}
	
}
