# Wood Floor

A Fabric mod that adds thin, decorative wooden flooring blocks in every vanilla wood type: both a planks-based floor and a log-end ("log top") floor variant that shows the log's end grain, for interior decoration.

## Features

- Two flooring styles per wood type: a standard plank-textured floor and a log-end floor showing the log's cross-section texture
- Available in all vanilla wood types, including Nether woods and Bamboo
- Thin profile (two pixels thick) so it reads as flooring rather than a full block
- Lies on the floor or against the ceiling, chosen by where you click, as a slab does; a second board of the same floor in the block takes the other surface, leaving the block hollow between them, and breaks for two
- Waterloggable, whichever half the board is in; placing a second board into a block displaces its water
- Burnable, like other wood blocks
- Mined fastest with an axe
- Added to their own "Wood Floors" creative tab

### Available Floors

**Plank Floors (crafted from slabs):**
Oak, Spruce, Birch, Jungle, Acacia, Dark Oak, Mangrove, Cherry, Pale Oak, Poplar, Bamboo, Bamboo Mosaic, Crimson, Warped

**Log Top Floors (crafted from logs/stems):**
Oak, Spruce, Birch, Jungle, Acacia, Dark Oak, Mangrove, Cherry, Pale Oak, Poplar, Crimson Stem, Warped Stem

## Crafting

Plank floors are crafted from three of their corresponding slabs in a row, for 9 floors. Log top floors are crafted from three of the corresponding log or stem in a row, for 9 floors.

Every floor also cuts on the stonecutter: a slab gives 3 plank floors, a plank block (or a Bamboo Mosaic block) 6, and a log or stem 3 of its log top floor.

## Pandorical

Wood Floor runs server-side, and Pandorical is required: the server will not load this mod without it. It registers the floors' block and item models through Pandorical's content API and syncs their textures.

Clients are the optional half. A player on a Pandorical client sees the flooring; a player on a vanilla client sees the blocks place and behave correctly without their custom appearance.

## Development

Installing is in [DEVELOPMENT.md](DEVELOPMENT.md).

## License

MIT, see [LICENSE](LICENSE).
