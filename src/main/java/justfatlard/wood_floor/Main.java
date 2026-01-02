package justfatlard.wood_floor;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {
	public static final String MOD_ID = "wood-floor-justfatlard";

	// Helper to create block settings with registry key
	private static AbstractBlock.Settings createSettings(String name) {
		RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, name));
		return AbstractBlock.Settings.create()
			.registryKey(key)
			.strength(2.0f, 3.0f)
			.sounds(BlockSoundGroup.WOOD)
			.burnable();
	}

	// Original woods
	public static final WoodFloor ACACIA_FLOOR = new WoodFloor(createSettings("acacia_floor"));
	public static final WoodFloor BIRCH_FLOOR = new WoodFloor(createSettings("birch_floor"));
	public static final WoodFloor DARK_OAK_FLOOR = new WoodFloor(createSettings("dark_oak_floor"));
	public static final WoodFloor JUNGLE_FLOOR = new WoodFloor(createSettings("jungle_floor"));
	public static final WoodFloor OAK_FLOOR = new WoodFloor(createSettings("oak_floor"));
	public static final WoodFloor SPRUCE_FLOOR = new WoodFloor(createSettings("spruce_floor"));

	// Nether woods (1.16)
	public static final WoodFloor CRIMSON_FLOOR = new WoodFloor(createSettings("crimson_floor"));
	public static final WoodFloor WARPED_FLOOR = new WoodFloor(createSettings("warped_floor"));

	// Mangrove (1.19)
	public static final WoodFloor MANGROVE_FLOOR = new WoodFloor(createSettings("mangrove_floor"));

	// Cherry & Bamboo (1.20)
	public static final WoodFloor CHERRY_FLOOR = new WoodFloor(createSettings("cherry_floor"));
	public static final WoodFloor BAMBOO_FLOOR = new WoodFloor(createSettings("bamboo_floor"));
	public static final WoodFloor BAMBOO_MOSAIC_FLOOR = new WoodFloor(createSettings("bamboo_mosaic_floor"));

	// Pale Oak (1.21.4)
	public static final WoodFloor PALE_OAK_FLOOR = new WoodFloor(createSettings("pale_oak_floor"));

	private static void register(String name, Block block){
		Identifier id = Identifier.of(MOD_ID, name);

		Registry.register(Registries.BLOCK, id, block);

		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
		Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings().registryKey(itemKey)));
	}

	@Override
	public void onInitialize(){
		// Original woods
		register("acacia_floor", ACACIA_FLOOR);
		register("birch_floor", BIRCH_FLOOR);
		register("dark_oak_floor", DARK_OAK_FLOOR);
		register("jungle_floor", JUNGLE_FLOOR);
		register("oak_floor", OAK_FLOOR);
		register("spruce_floor", SPRUCE_FLOOR);

		// Nether woods (1.16)
		register("crimson_floor", CRIMSON_FLOOR);
		register("warped_floor", WARPED_FLOOR);

		// Mangrove (1.19)
		register("mangrove_floor", MANGROVE_FLOOR);

		// Cherry & Bamboo (1.20)
		register("cherry_floor", CHERRY_FLOOR);
		register("bamboo_floor", BAMBOO_FLOOR);
		register("bamboo_mosaic_floor", BAMBOO_MOSAIC_FLOOR);

		// Pale Oak (1.21.4)
		register("pale_oak_floor", PALE_OAK_FLOOR);

		// Add all floors to creative tab
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
			entries.add(ACACIA_FLOOR);
			entries.add(BIRCH_FLOOR);
			entries.add(DARK_OAK_FLOOR);
			entries.add(JUNGLE_FLOOR);
			entries.add(OAK_FLOOR);
			entries.add(SPRUCE_FLOOR);
			entries.add(CRIMSON_FLOOR);
			entries.add(WARPED_FLOOR);
			entries.add(MANGROVE_FLOOR);
			entries.add(CHERRY_FLOOR);
			entries.add(BAMBOO_FLOOR);
			entries.add(BAMBOO_MOSAIC_FLOOR);
			entries.add(PALE_OAK_FLOOR);
		});

		System.out.println("Loaded wood-floor");
	}
}
