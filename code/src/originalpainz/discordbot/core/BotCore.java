package originalpainz.discordbot.core;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import originalpainz.discordbot.listener.DiscordListener;
import originalpainz.discordbot.listener.SpigotListener;

public class BotCore extends JavaPlugin {
	
	private JDA jda;
	
	private long channelId=372147627749408771L; 
	
	
	@Override
	public void onLoad() {
		try {
			jda = new JDABuilder(AccountType.BOT).setToken("_ReplaceByYourToken_").addEventListener(new DiscordListener(this)).buildAsync();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (RateLimitedException e) {
			e.printStackTrace();
		}
		super.onLoad();
	}
	
	@Override
	public void onEnable() 
	{
		Bukkit.getPluginManager().registerEvents(new SpigotListener(this), this);
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		jda.shutdown();
		super.onDisable();
	}
	
	public JDA getJda() {
		return jda;
	}
	
	public long getChannelId() {
		return channelId;
	}

	
	public void sendMessageToMinecraft(Message message) {
		Bukkit.broadcastMessage("[Discord] " + message.getAuthor().getName()+" : "+message.getContent());
	}
	
	public void sendMessageToDiscord(Player player, String message) {
		jda.getTextChannelById(channelId).sendMessage("[Minecraft] "+player.getName()+" > "+message).queue();
	}

}
