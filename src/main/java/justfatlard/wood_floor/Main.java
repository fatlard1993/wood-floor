package justfatlard.wood_floor;

import justfatlard.pandorical.api.BlockRegistration;
import justfatlard.pandorical.api.ItemRegistration;
import justfatlard.pandorical.api.PandoricalApi;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Main implements ModInitializer {
	public static final String MOD_ID = "wood-floor-justfatlard";

	// Helper to create block settings with registry key
	private static BlockBehaviour.Properties createSettings(String name) {
		ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, name));
		return BlockBehaviour.Properties.of()
			.setId(key)
			.strength(2.0f, 3.0f)
			.sound(SoundType.WOOD)
			.ignitedByLava();
	}

	// Original woods
	public static final WoodFloor ACACIA_FLOOR = new WoodFloor(createSettings("acacia_floor"));
	public static final WoodFloor BIRCH_FLOOR = new WoodFloor(createSettings("birch_floor"));
	public static final WoodFloor DARK_OAK_FLOOR = new WoodFloor(createSettings("dark_oak_floor"));
	public static final WoodFloor JUNGLE_FLOOR = new WoodFloor(createSettings("jungle_floor"));
	public static final WoodFloor OAK_FLOOR = new WoodFloor(createSettings("oak_floor"));
	public static final WoodFloor SPRUCE_FLOOR = new WoodFloor(createSettings("spruce_floor"));

	// Nether woods
	public static final WoodFloor CRIMSON_FLOOR = new WoodFloor(createSettings("crimson_floor"));
	public static final WoodFloor WARPED_FLOOR = new WoodFloor(createSettings("warped_floor"));

	// Mangrove
	public static final WoodFloor MANGROVE_FLOOR = new WoodFloor(createSettings("mangrove_floor"));

	// Cherry & Bamboo
	public static final WoodFloor CHERRY_FLOOR = new WoodFloor(createSettings("cherry_floor"));
	public static final WoodFloor BAMBOO_FLOOR = new WoodFloor(createSettings("bamboo_floor"));
	public static final WoodFloor BAMBOO_MOSAIC_FLOOR = new WoodFloor(createSettings("bamboo_mosaic_floor"));

	// Pale Oak
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
		Identifier id = Identifier.fromNamespaceAndPath(MOD_ID, name);

		Registry.register(BuiltInRegistries.BLOCK, id, block);

		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);
		Registry.register(BuiltInRegistries.ITEM, id, new WoodFloorItem(block, new Item.Properties().setId(itemKey)));
	}

	@Override
	public void onInitialize(){
		// Register with Pandorical if available
		if (PandoricalApi.isAvailable()) {
			PandoricalApi.content().registerModAssets(MOD_ID);
		}

		// Original woods
		register("acacia_floor", ACACIA_FLOOR);
		register("birch_floor", BIRCH_FLOOR);
		register("dark_oak_floor", DARK_OAK_FLOOR);
		register("jungle_floor", JUNGLE_FLOOR);
		register("oak_floor", OAK_FLOOR);
		register("spruce_floor", SPRUCE_FLOOR);

		// Nether woods
		register("crimson_floor", CRIMSON_FLOOR);
		register("warped_floor", WARPED_FLOOR);

		// Mangrove
		register("mangrove_floor", MANGROVE_FLOOR);

		// Cherry & Bamboo
		register("cherry_floor", CHERRY_FLOOR);
		register("bamboo_floor", BAMBOO_FLOOR);
		register("bamboo_mosaic_floor", BAMBOO_MOSAIC_FLOOR);

		// Pale Oak
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

		// Register Pandorical block/item models
		if (PandoricalApi.isAvailable()) {
			String[] allFloors = {
				"acacia_floor", "birch_floor", "dark_oak_floor", "jungle_floor", "oak_floor", "spruce_floor",
				"crimson_floor", "warped_floor", "mangrove_floor", "cherry_floor", "bamboo_floor", "bamboo_mosaic_floor",
				"pale_oak_floor",
				"oak_log_top_floor", "spruce_log_top_floor", "birch_log_top_floor", "jungle_log_top_floor",
				"acacia_log_top_floor", "dark_oak_log_top_floor", "mangrove_log_top_floor", "cherry_log_top_floor",
				"pale_oak_log_top_floor", "crimson_stem_top_floor", "warped_stem_top_floor"
			};
			for (String name : allFloors) {
				PandoricalApi.content().registerBlock(MOD_ID + ":" + name, new BlockRegistration()
					.model(MOD_ID + ":block/" + name));
				PandoricalApi.content().registerItem(MOD_ID + ":" + name, new ItemRegistration()
					.model(MOD_ID + ":item/" + name));
			}
		}

		// Create creative tab
		ResourceKey<CreativeModeTab> tabKey = ResourceKey.create(
			Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(MOD_ID, "wood_floors"));
		CreativeModeTab woodFloorGroup = FabricCreativeModeTab.builder()
			.title(Component.literal("Wood Floors"))
			.icon(() -> new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "oak_floor"))))
			.displayItems((context, entries) -> {
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "oak_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "spruce_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "birch_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "jungle_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "acacia_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "dark_oak_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "mangrove_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "cherry_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "pale_oak_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "bamboo_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "bamboo_mosaic_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "crimson_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "warped_floor"))));
				// Log top floors
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "oak_log_top_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "spruce_log_top_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "birch_log_top_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "jungle_log_top_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "acacia_log_top_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "dark_oak_log_top_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "mangrove_log_top_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "cherry_log_top_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "pale_oak_log_top_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "crimson_stem_top_floor"))));
				entries.accept(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(MOD_ID, "warped_stem_top_floor"))));
			})
			.build();
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, tabKey, woodFloorGroup);

		System.out.println("[wood-floor] Loaded wood-floor (server-side with Pandorical)");
	}
}
