## Writing a shapeless crafting recipe

### I. Getting known with the scheme

At first, let's look at the scheme of the `.json` file that contains a **shapeless** recipe:
```json5
{
  "type": "crafting_shapeless", //the type of recipe
  "name": "<recipe name>", //the name of recipe, NOT NEEDED, just to describe the recipe
  "keys": [
    { /* item descriptor */ },
    { /* item descriptor */ },
    { /* item descriptor */ }
  ],
  "result": { /* item descriptor */ }
}
```

The best way to explain the way to implement a recipe is to show an example!

### II. Realistic example

Let's imagine a recipe where we get 1x `Obsidian Pickaxe` by combining `Diamond Pickaxe` and `Obsidian Tool Repair Kit`.

![Preview image](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/shapeless_crafting_1.png)

_Ingredients: 1x `Diamond Pickaxe`, 1x `Obsidian Tool Repair Kit`_

### III. Getting item descriptor for each item

Assuming you've already read previous guides and [know how to obtain an item descriptor](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/FIND_ID_REINDEV.md), I'll just provide ready one:

**For `Diamond Pickaxe`:**
```json5
{
  "item": "item.diamond_pickaxe"
}
```

**For `Obsidian Tool Repair Kit`:**
```json5
{
  "item": "item.obsidian_tool_repair_kit"
}
```

**For `Obsidian Pickaxe`:**
```json5
{
  "item": "item.obsidian_pickaxe"
}
```

### IV. Combining everything into `.json`

Next thing is to simply add everything we created to a combined json file:

```json5
{
  "type": "crafting_shapeless",
  "keys": [
    {
      "item": "item.diamond_pickaxe"
    },
    {
      "item": "item.obsidian_tool_repair_kit"
    }
  ],
  "result": {
    "item": "item.obsidian_pickaxe"
  }
}
```

That's all! Now you can test it by putting it in `config/mamasrecipes` folder [(see how)](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/WRITING_SHAPED_RECIPE.md).

[Get the `.json` file of this example!](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/examples/obsidian_pickaxe.json)

![Preview image](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/shapeless_crafting_2.png)
