package gc.customplugin.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

public class LayEggEvent implements Listener{
	
	private Entity ent;
	
	@EventHandler
	public void onLayEgg(ItemSpawnEvent e){
		
		ent = e.getEntity();
		
	}
	
	public Entity getEnt(){
		return ent;
	}
	
}
