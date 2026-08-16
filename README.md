# Wood Floor

A Fabric mod that adds thin, decorative wooden flooring blocks in every vanilla wood type: both a planks-based floor and a log-end ("log top") floor variant that shows the log's end grain, for interior decoration.

## Features

- Two flooring styles per wood type: a standard plank-textured floor and a log-end floor showing the log's cross-section texture
- Available in all vanilla wood types, including Nether woods and Bamboo
- Thin profile (a fraction of a full block's height) so it reads as flooring rather than a full block
- Waterloggable
- Burnable, like other wood blocks
- Added to their own "Wood Floors" creative tab

### Available Floors

**Plank Floors (crafted from planks):**
Oak, Spruce, Birch, Jungle, Acacia, Dark Oak, Mangrove, Cherry, Pale Oak, Bamboo, Bamboo Mosaic, Crimson, Warped

**Log Top Floors (crafted from logs/stems):**
Oak, Spruce, Birch, Jungle, Acacia, Dark Oak, Mangrove, Cherry, Pale Oak, Crimson Stem, Warped Stem

## Crafting

Plank floors are crafted from their corresponding planks. Log top floors are crafted directly from the corresponding log or stem.

## Requirements

Targets the Minecraft, Fabric Loader, and Fabric API versions declared in this mod's `gradle.properties`. Check there for the exact currently-supported version.

## Pandorical

Wood Floor runs server-side, and Pandorical is a hard dependency (`fabric.mod.json`): the server will not load this mod without it. It registers the floors' block and item models through Pandorical's content API and syncs their textures.

Clients are the optional half. A player on a Pandorical client sees the flooring; a player on a vanilla client sees the blocks place and behave correctly without their custom appearance.

## Installation

Install alongside its declared dependencies (see `fabric.mod.json`).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
