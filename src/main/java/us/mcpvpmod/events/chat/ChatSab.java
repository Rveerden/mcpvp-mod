package us.mcpvpmod.events.chat;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import us.mcpvpmod.Main;
import us.mcpvpmod.config.sab.ConfigSabSelect;
import us.mcpvpmod.game.core.CoreSab;
import us.mcpvpmod.game.state.StateSab;
import us.mcpvpmod.game.vars.Vars;
import us.mcpvpmod.trackers.ChatTracker;
import us.mcpvpmod.triggers.ChatTrigger;

public class ChatSab {

	public static void onChat(ClientChatReceivedEvent event) {
		String message = event.message.getUnformattedText();
		
		ChatTracker.checkAll(message);
		ChatTrigger.checkAll(message);

		// Implementation of auto-sabbing.
		if (message.matches(CoreSab.reJoin)) {
			if (ConfigSabSelect.autoSab) {
				Main.mc.thePlayer.sendChatMessage("/sab");
			}
		}
		
		// Sabotage state detection relies on chat messages.
		if (message.matches(CoreSab.reWelcome) 
				|| message.matches(CoreSab.reVoting)
				|| message.matches(CoreSab.reStarting)
				|| message.matches(CoreSab.reWait)) {
			StateSab.state = StateSab.PRE;
		}
		
		if (message.matches(CoreSab.reRole) || message.matches(CoreSab.reStart)) {
			System.out.println("Game Started!");
			StateSab.state = StateSab.PLAY;
		}
		
		if (message.matches(CoreSab.reSpectate)) {
			StateSab.state = StateSab.DEAD;
		}
		
		if (message.matches(CoreSab.reWin)) {
			StateSab.state = StateSab.PRE;
		}
		
		if (message.matches(CoreSab.reRemain)) {
			System.out.println("Remain: " + message.replaceAll(CoreSab.reRemain, "$1"));
			System.out.println(Vars.get("sab:remain"));
		}
		
		if (message.matches(CoreSab.reDetective)) {
			System.out.println("Det: " + message.replaceAll(CoreSab.reDetective, "$1"));
			System.out.println(Vars.get("sab:detective"));
		}
		
		if (message.matches(CoreSab.reWin)) {
			System.out.println("Win: " + message.replaceAll(CoreSab.reWin, "$1"));
			System.out.println(Vars.get("sab:winner"));
		}
 	}
	
}
