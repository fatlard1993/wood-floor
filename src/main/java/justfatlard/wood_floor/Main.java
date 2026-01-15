package justfatlard.wood_floor;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
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

	// Log top floors - Overworld
	public static final WoodFloor OAK_LOG_TOP_FLOOR = new WoodFloor(createSettings("oak_log_top_floor"));
	public static final WoodFloor SPRUCE_LOG_TOP_FLOOR = new WoodFloor(createSettings("spruce_log_top_floor"));
	public static final WoodFloor BIRCH_LOG_TOP_FLOOR = new WoodFloor(createSettings("birch_log_top_floor"));
	public static final WoodFloor JUNGLE_LOG_TOP_FLOOR = new WoodFloor(createSettings("jungle_log_top_floor"));
	public static final WoodFloor ACACIA_LOG_TOP_FLOOR = new WoodFloor(createSettings("acacia_log_top_floor"));
	public static final WoodFloor DARK_OAK_LOG_TOP_FLOOR = new WoodFloor(createSettings("dark_oak_log_top_floor"));
	public static final WoodFloor MANGROVE_LOG_TOP_FLOOR = new WoodFloor(createSettings("mangrove_log_top_floor"));
	public static final WoodFloor CHERRY_LOG_TOP_FLOOR = new WoodFloor(createSettings("cherry_log_top_floor"));
	public static final WoodFloor PALE_OAK_LOG_TOP_FLOOR = new WoodFloor(createSettings("pale_oak_log_top_floor"));

	// Log top floors - Nether (stems)
	public static final WoodFloor CRIMSON_STEM_TOP_FLOOR = new WoodFloor(createSettings("crimson_stem_top_floor"));
	public static final WoodFloor WARPED_STEM_TOP_FLOOR = new WoodFloor(createSettings("warped_stem_top_floor"));

	private static void register(String name, Block block){
		Identifier id = Identifier.of(MOD_ID, name);

		Registry.register(Registries.BLOCK, id, block);

		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
		Registry.register(Registries.ITEM, id, new WoodFloorItem(block, new Item.Settings().registryKey(itemKey), name));
	}

	private static void setupPolymerModel(String name, WoodFloor floor) {
		Identifier modelId = Identifier.of(MOD_ID, "block/" + name);
		BlockState polymerState = PolymerBlockResourceUtils.requestBlock(
			BlockModelType.TRIPWIRE_BLOCK_FLAT,
			PolymerBlockModel.of(modelId)
		);

		if (polymerState != null) {
			floor.setPolymerBlockState(polymerState);
		} else {
			System.err.println("[wood-floor] Failed to request polymer model for " + name + " - no slots available");
		}
	}

	@Override
	public void onInitialize(){
		// Register mod assets with Polymer resource pack system
		PolymerResourcePackUtils.addModAssets(MOD_ID);
		PolymerResourcePackUtils.markAsRequired();

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

		// Log top floors - Overworld
		register("oak_log_top_floor", OAK_LOG_TOP_FLOOR);
		register("spruce_log_top_floor", SPRUCE_LOG_TOP_FLOOR);
		register("birch_log_top_floor", BIRCH_LOG_TOP_FLOOR);
		register("jungle_log_top_floor", JUNGLE_LOG_TOP_FLOOR);
		register("acacia_log_top_floor", ACACIA_LOG_TOP_FLOOR);
		register("dark_oak_log_top_floor", DARK_OAK_LOG_TOP_FLOOR);
		register("mangrove_log_top_floor", MANGROVE_LOG_TOP_FLOOR);
		register("cherry_log_top_floor", CHERRY_LOG_TOP_FLOOR);
		register("pale_oak_log_top_floor", PALE_OAK_LOG_TOP_FLOOR);

		// Log top floors - Nether (stems)
		register("crimson_stem_top_floor", CRIMSON_STEM_TOP_FLOOR);
		register("warped_stem_top_floor", WARPED_STEM_TOP_FLOOR);

		// Setup Polymer models for server-side rendering
		setupPolymerModel("acacia_floor", ACACIA_FLOOR);
		setupPolymerModel("birch_floor", BIRCH_FLOOR);
		setupPolymerModel("dark_oak_floor", DARK_OAK_FLOOR);
		setupPolymerModel("jungle_floor", JUNGLE_FLOOR);
		setupPolymerModel("oak_floor", OAK_FLOOR);
		setupPolymerModel("spruce_floor", SPRUCE_FLOOR);
		setupPolymerModel("crimson_floor", CRIMSON_FLOOR);
		setupPolymerModel("warped_floor", WARPED_FLOOR);
		setupPolymerModel("mangrove_floor", MANGROVE_FLOOR);
		setupPolymerModel("cherry_floor", CHERRY_FLOOR);
		setupPolymerModel("bamboo_floor", BAMBOO_FLOOR);
		setupPolymerModel("bamboo_mosaic_floor", BAMBOO_MOSAIC_FLOOR);
		setupPolymerModel("pale_oak_floor", PALE_OAK_FLOOR);

		// Log top floors - Overworld
		setupPolymerModel("oak_log_top_floor", OAK_LOG_TOP_FLOOR);
		setupPolymerModel("spruce_log_top_floor", SPRUCE_LOG_TOP_FLOOR);
		setupPolymerModel("birch_log_top_floor", BIRCH_LOG_TOP_FLOOR);
		setupPolymerModel("jungle_log_top_floor", JUNGLE_LOG_TOP_FLOOR);
		setupPolymerModel("acacia_log_top_floor", ACACIA_LOG_TOP_FLOOR);
		setupPolymerModel("dark_oak_log_top_floor", DARK_OAK_LOG_TOP_FLOOR);
		setupPolymerModel("mangrove_log_top_floor", MANGROVE_LOG_TOP_FLOOR);
		setupPolymerModel("cherry_log_top_floor", CHERRY_LOG_TOP_FLOOR);
		setupPolymerModel("pale_oak_log_top_floor", PALE_OAK_LOG_TOP_FLOOR);

		// Log top floors - Nether (stems)
		setupPolymerModel("crimson_stem_top_floor", CRIMSON_STEM_TOP_FLOOR);
		setupPolymerModel("warped_stem_top_floor", WARPED_STEM_TOP_FLOOR);

		// Create Polymer item group for wood floors (access via /polymer creative)
		ItemGroup woodFloorGroup = PolymerItemGroupUtils.builder()
			.displayName(Text.literal("Wood Floors"))
			.icon(() -> new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "oak_floor"))))
			.entries((context, entries) -> {
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "oak_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "spruce_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "birch_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "jungle_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "acacia_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "dark_oak_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "mangrove_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "cherry_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "pale_oak_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "bamboo_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "bamboo_mosaic_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "crimson_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "warped_floor"))));
				// Log top floors
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "oak_log_top_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "spruce_log_top_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "birch_log_top_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "jungle_log_top_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "acacia_log_top_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "dark_oak_log_top_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "mangrove_log_top_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "cherry_log_top_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "pale_oak_log_top_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "crimson_stem_top_floor"))));
				entries.add(new ItemStack(Registries.ITEM.get(Identifier.of(MOD_ID, "warped_stem_top_floor"))));
			})
			.build();
		PolymerItemGroupUtils.registerPolymerItemGroup(Identifier.of(MOD_ID, "wood_floors"), woodFloorGroup);

		System.out.println("[wood-floor] Loaded wood-floor (server-side with Polymer)");
	}
}
