# Mama's Recipes (FoxLoader)

A FoxLoader (ReIndev) modification designed to provide fetch-able JSON recipes support!

Uses [own core library](https://github.com/tracystacktrace/mamasrecipes) to provide JSON recipes parse and load functions.

## Creating Recipes

### Quick Introduction

So, this mod currently support 5 recipe types for `ReIndev`:
- `crafting_shaped`: shaped crafting on workbench
- `crafting_shapeless`: shapeless crafting on workbench
- `furnace`: smelting items in furnace
- `forge`: smelting items in forge
- `refridgifreezer`: smelting items in refridgifreezer

Each one has similar `.json` structures, but end up having different stuff within

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

**[Please refer to this guide to obtain data and write own item descriptors!](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/FIND_ID_REINDEV.md#how-can-i-find-an-idmetadata-of-an-item)**

## License

The mod is licensed under [Apache License 2.0](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/LICENSE).

The core library `mamasrecipes` is licensed under [GNU Lesser General Public License Version 2.1](https://github.com/tracystacktrace/mamasrecipes/blob/master/LICENSE).