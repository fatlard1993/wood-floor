#!/usr/bin/env python3
"""
Give every floor a top and a double model, and a blockstate that picks between them.

A floor used to be one thing: a two-pixel board lying on the bottom of its block. It is a slab
now, in the sense that matters - it fills the bottom of a block, or the top, or both - so it
needs the two models it never had.

  <name>.json          unchanged, the board at the bottom
  <name>_top.json      the board at the very top of the block, a ceiling plank
  <name>_double.json   both boards, each at its own surface: a floor and a ceiling with the
                       block hollow between them, which is what the two faces of one storey
                       boundary look like
  <name>_snapped.json  the top board resting on a half-height slab beneath it, for when a floor
                       shares a block with an ordinary slab and would otherwise hang at the
                       ceiling with nothing under it

Derived from each floor's existing model rather than written out per wood type, so the two new
models cannot drift from the one they came from and a new wood type needs nothing here.

Usage: python3 generate_slab_assets.py
"""

import json
import pathlib

HERE = pathlib.Path(__file__).parent
NAMESPACE = "wood-floor-justfatlard"
ASSETS = HERE / "src/main/resources/assets" / NAMESPACE

# The board's thickness, in pixels, read off the model rather than assumed.
SIDES = ("north", "south", "west", "east")


def raised(element, thickness):
    """The same board, lifted to sit against the top of the block."""
    lifted = json.loads(json.dumps(element))
    lifted["from"] = [element["from"][0], 16 - thickness, element["from"][2]]
    lifted["to"] = [element["to"][0], 16, element["to"][2]]

    faces = lifted["faces"]
    for side in SIDES:
        if side in faces:
            # The side strip comes off the top of the texture now, so the grain runs the way it
            # would if you were looking at the underside of the floor above.
            faces[side]["uv"] = [0, 0, 16, thickness]
    # What it hides changes with it: it covers the block above, not the one below.
    if "down" in faces:
        faces["down"].pop("cullface", None)
    if "up" in faces:
        faces["up"]["cullface"] = "up"
    return lifted


def snapped(element, thickness):
    """The board resting on top of a half-height slab, gap left above it.

    Every slab-shaped thing that can fill the lower half of a block reaches the same height -
    eight pixels - whether it is a stone slab or a fence post, so one model serves for all of
    them. Nothing is culled: it floats in the middle of its block with air on both sides.
    """
    resting = json.loads(json.dumps(element))
    resting["from"] = [element["from"][0], 8, element["from"][2]]
    resting["to"] = [element["to"][0], 8 + thickness, element["to"][2]]

    faces = resting["faces"]
    for face in faces.values():
        face.pop("cullface", None)
    return resting


def main():
    blockstates = sorted((ASSETS / "blockstates").glob("*.json"))
    if not blockstates:
        raise SystemExit(f"No blockstates under {ASSETS}")

    written = 0
    for blockstate in blockstates:
        name = blockstate.stem
        model_file = ASSETS / "models/block" / f"{name}.json"
        if not model_file.exists():
            print(f"  ! {name}: no base model, skipped")
            continue

        model = json.loads(model_file.read_text())
        elements = model.get("elements")
        if not elements or len(elements) != 1:
            print(f"  ! {name}: expected one element, skipped")
            continue

        element = elements[0]
        thickness = element["to"][1] - element["from"][1]

        for suffix, build in (("_top", raised), ("_snapped", snapped)):
            variant = json.loads(json.dumps(model))
            variant["elements"] = [build(element, thickness)]
            (ASSETS / "models/block" / f"{name}{suffix}.json").write_text(
                json.dumps(variant, indent=2) + "\n")

        # A double is simply both boards at once, each keeping its own surface and its own
        # culling, with the block hollow between them.
        both = json.loads(json.dumps(model))
        both["elements"] = [element, raised(element, thickness)]
        (ASSETS / "models/block" / f"{name}_double.json").write_text(
            json.dumps(both, indent=2) + "\n")

        # Waterlogging does not change what is drawn, so it is left out of the keys; a variant key
        # only has to name the properties it actually distinguishes.
        blockstate.write_text(json.dumps({
            "variants": {
                "type=bottom": {"model": f"{NAMESPACE}:block/{name}"},
                "type=top": {"model": f"{NAMESPACE}:block/{name}_top"},
                "type=double": {"model": f"{NAMESPACE}:block/{name}_double"},
            }
        }, indent=2) + "\n")
        written += 1

    print(f"  {written} floors given top and double models")


if __name__ == "__main__":
    main()
