package justfatlard.wood_floor;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

public class WoodFloorItem extends BlockItem implements PolymerItem {
	private final Identifier modelId;

	public WoodFloorItem(Block block, Item.Settings settings, String modelName) {
		super(block, settings);
		// Store the model identifier for this item (points to items/<name>.json)
		this.modelId = Identifier.of(Main.MOD_ID, modelName);
	}

	@Override
	public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
		// Use paper as the base item (any item works)
		return Items.PAPER;
	}

	@Override
	public Identifier getPolymerItemModel(ItemStack itemStack, PacketContext context) {
		// Return our custom item model
		return this.modelId;
	}
}
