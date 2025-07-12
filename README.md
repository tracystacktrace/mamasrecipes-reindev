# Mama's Recipes (FoxLoader)

A FoxLoader (ReIndev) modification designed to provide fetch-able JSON recipes support!

Uses [own core library](https://github.com/tracystacktrace/mamasrecipes) to provide JSON based recipe processing functions.

## Creating Recipes

**Please use these resources for additional info or for specific situations:**
- [How can I get data about the item and write an item descriptor of it?](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/FIND_ID_REINDEV.md#how-can-i-find-an-idmetadata-of-an-item)
- [How can I test my recipe or load it in game?]()

This mod currently support 5 recipe types for `ReIndev`:
- `crafting_shaped`: shaped crafting on workbench
- `crafting_shapeless`: shapeless crafting on workbench
- `furnace`: smelting items in furnace
- `forge`: smelting items in forge
- `refridgifreezer`: smelting/freezing items in refridgifreezer

Each one has similar `.json` structures, but end up having slightly different content.

### Item descriptor

What's item descriptor? It's a piece of `JSON` code that describes an item and its' properties (name, metadata, count, etc.). It is primarily used to describe **`inputs`** and **`outputs`** of a recipe.

The full item descriptor instance looks like this:
```json5
{
  "item": "item.cloth.cyan", // item identifier, could be string or int representing id
  "meta": 9, // item metadata
  "count": 1, // number of items
  "displayName": "Custom Item Name" // ReIndev exclusive: display name
}
```

For more information, please refer to [the dedicated guide page](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/FIND_ID_REINDEV.md).

## License

The mod is licensed under [Apache License 2.0](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/LICENSE).

The core library `mamasrecipes` is licensed under [GNU Lesser General Public License Version 2.1](https://github.com/tracystacktrace/mamasrecipes/blob/master/LICENSE).