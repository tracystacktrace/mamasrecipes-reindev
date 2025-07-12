# Mama's Recipes (FoxLoader)

A FoxLoader (ReIndev) modification designed to provide fetch-able JSON recipes support!

Uses [own core library](https://github.com/tracystacktrace/mamasrecipes) to provide JSON based recipe processing functions.

## Creating Recipes

This mod currently support 5 recipe types for `ReIndev`:
- `crafting_shaped`: [shaped crafting on workbench](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/WRITING_SHAPED_RECIPE.md)
- `crafting_shapeless`: [shapeless crafting on workbench](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/WRITING_SHAPELESS_RECIPE.md)
- `furnace`: smelting items in furnace
- `forge`: smelting items in forge
- `refridgifreezer`: smelting/freezing items in refridgifreezer

Each one has similar `.json` structures, but end up having slightly different content.

**Please use these resources for additional info or for specific situations:**
- [What's item descriptor? How can I get data about the item and write an item descriptor of it?](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/FIND_ID_REINDEV.md#how-can-i-find-an-idmetadata-of-an-item)
- [How can I test my recipe or load it in game?](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/LOADING_RECIPE.md#how-can-i-test-my-recipe-or-load-it-in-game)

## License

The mod is licensed under [Apache License 2.0](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/LICENSE).

The core library `mamasrecipes` is licensed under [GNU Lesser General Public License Version 2.1](https://github.com/tracystacktrace/mamasrecipes/blob/master/LICENSE).