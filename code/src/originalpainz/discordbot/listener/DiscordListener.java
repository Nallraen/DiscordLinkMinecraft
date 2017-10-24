package originalpainz.discordbot.listener;

import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import originalpainz.discordbot.core.BotCore;

public class DiscordListener extends ListenerAdapter {
	
	private final BotCore bot;
	
	public DiscordListener(BotCore bot){
		this.bot = bot;
	}

	@Override
	public void onReady(ReadyEvent event){
		System.out.println("Le Bot est bien connecté.");
		event.getJDA().getPresence().setGame(Game.of("Développé par OriginalPainZ"));
		super.onReady(event);
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event){
		if(event.getAuthor().isBot()) return;
		if(event.getGuild() ==null) return;
		if(event.getTextChannel().getIdLong() != bot.getChannelId()) return;
		bot.sendMessageToMinecraft(event.getMessage());
		super.onMessageReceived(event);
	}
	
}
