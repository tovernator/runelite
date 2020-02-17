package net.runelite.client.plugins.gemrocks;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

import javax.inject.Inject;
import java.awt.*;

public class GemRocksOverlay extends Overlay
{

	private GemRocksPlugin plugin;
	private PanelComponent panelComponent = new PanelComponent();

	@Inject
	public GemRocksOverlay(GemRocksPlugin plugin)
	{
		super(plugin);
		setPosition(OverlayPosition.TOP_LEFT);
		setLayer(OverlayLayer.ABOVE_SCENE);
		this.plugin = plugin;
	}

	private float calcPercentage(int total, int part)
	{
		if (total == 0)
		{
			return 0;
		}

		return (100.f * ((float) part / (float) total));
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{

		if (plugin.getGems().cTotal == 0)
		{
			return null;
		}

		panelComponent.getChildren().clear();

		panelComponent.getChildren().add(LineComponent.builder()
			.left("Opal")
			.right(plugin.getGems().cOpal + " (" + String.format("%.2f", calcPercentage(plugin.getGems().cTotal, plugin.getGems().cOpal)) + "%)").build());
		panelComponent.getChildren().add(LineComponent.builder()
			.left("Jade")
			.right(plugin.getGems().cJade + " (" + String.format("%.2f", calcPercentage(plugin.getGems().cTotal, plugin.getGems().cJade)) + "%)").build());
		panelComponent.getChildren().add(LineComponent.builder()
			.left("Red Topaz")
			.right(plugin.getGems().cRedTopaz + " (" + String.format("%.2f", calcPercentage(plugin.getGems().cTotal, plugin.getGems().cRedTopaz)) + "%)").build());
		panelComponent.getChildren().add(LineComponent.builder()
			.left("Sapphire")
			.right(plugin.getGems().cSapphire + " (" + String.format("%.2f", calcPercentage(plugin.getGems().cTotal, plugin.getGems().cSapphire)) + "%)").build());
		panelComponent.getChildren().add(LineComponent.builder()
			.left("Emerald")
			.right(plugin.getGems().cEmerald + " (" + String.format("%.2f", calcPercentage(plugin.getGems().cTotal, plugin.getGems().cEmerald)) + "%)").build());
		panelComponent.getChildren().add(LineComponent.builder()
			.left("Ruby")
			.right(plugin.getGems().cRuby + " (" + String.format("%.2f", calcPercentage(plugin.getGems().cTotal, plugin.getGems().cRuby)) + "%)").build());
		panelComponent.getChildren().add(LineComponent.builder()
			.left("Diamond")
			.right(plugin.getGems().cDiamond + " (" + String.format("%.2f", calcPercentage(plugin.getGems().cTotal, plugin.getGems().cDiamond)) + "%)").build());
		panelComponent.getChildren().add(LineComponent.builder()
			.left("Total Gems")
			.right(Integer.toString(plugin.getGems().cTotal)).build());

		return panelComponent.render(graphics);
	}
}
