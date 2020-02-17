package net.runelite.client.plugins.gemrocks;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@PluginDescriptor(
	name = "Gem Rocks",
	description = "Track mined gems of gem rocks."
)
public class GemRocksPlugin extends Plugin
{

	private static final Pattern GEMROCK_PATTERN = Pattern.compile("You just mined an?(?: piece of)? ([\\w ]+)!");
	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private GemRocksOverlay overlay;

	private Gems gems;

	@Override
	public void startUp()
	{
		overlayManager.add(overlay);
		this.gems = new Gems();
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	public Gems getGems()
	{
		return this.gems;
	}

	@Subscribe
	public void onGameStateChanged(final GameStateChanged event)
	{
		switch (event.getGameState())
		{
			case HOPPING:
				break;
			case LOADING:
				break;

			case LOGGED_IN:
				break;
		}

	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		if (event.getType() == ChatMessageType.SPAM || event.getType() == ChatMessageType.GAMEMESSAGE)
		{
			String message = event.getMessage();
			Matcher m = GEMROCK_PATTERN.matcher(message);
			if (m.matches())
			{
				String gem = m.group(1);
				switch (gem)
				{
					case "Opal":
						this.gems.cOpal++;
						this.gems.cTotal++;
						break;
					case "Jade":
						this.gems.cJade++;
						this.gems.cTotal++;
						break;
					case "Red Topaz":
						this.gems.cRedTopaz++;
						this.gems.cTotal++;
						break;
					case "Sapphire":
						this.gems.cSapphire++;
						this.gems.cTotal++;
						break;
					case "Emerald":
						this.gems.cEmerald++;
						this.gems.cTotal++;
						break;
					case "Ruby":
						this.gems.cRuby++;
						this.gems.cTotal++;
						break;
					case "Diamond":
						this.gems.cDiamond++;
						this.gems.cTotal++;
						break;
					default:
						break;
				}

			}
		}
	}
}