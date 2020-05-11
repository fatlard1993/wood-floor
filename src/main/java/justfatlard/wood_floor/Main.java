package justfatlard.wood_floor;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
	public static final String MOD_ID = "wood-floor-justfatlard";

	public static final WoodFloor ACACIA_FLOOR = new WoodFloor();
	public static final WoodFloor BIRCH_FLOOR = new WoodFloor();
	public static final WoodFloor DARK_OAK_FLOOR = new WoodFloor();
	public static final WoodFloor JUNGLE_FLOOR = new WoodFloor();
	public static final WoodFloor OAK_FLOOR = new WoodFloor();
	public static final WoodFloor SPRUCE_FLOOR = new WoodFloor();

	private static void register(String name, Block block){
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, name), new BlockItem(block, new Item.Settings().group(ItemGroup.DECORATIONS)));

		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, name), block);
	}

	@Override
	public void onInitialize(){
		register("acacia_floor", ACACIA_FLOOR);
		register("birch_floor", BIRCH_FLOOR);
		register("dark_oak_floor", DARK_OAK_FLOOR);
		register("jungle_floor", JUNGLE_FLOOR);
		register("oak_floor", OAK_FLOOR);
		register("spruce_floor", SPRUCE_FLOOR);

		System.out.println("Loaded wood-floor");
	}
}
