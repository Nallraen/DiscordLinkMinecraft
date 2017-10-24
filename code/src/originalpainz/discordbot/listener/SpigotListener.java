package originalpainz.discordbot.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import originalpainz.discordbot.core.BotCore;

public class SpigotListener implements Listener {
	
	private final BotCore bot;
	
	public SpigotListener(BotCore bot){
		this.bot = bot;
	}
	
	@EventHandler
	private void onPlayerChat(AsyncPlayerChatEvent event){
		bot.sendMessageToDiscord(event.getPlayer(), event.getMessage());
	}

}
