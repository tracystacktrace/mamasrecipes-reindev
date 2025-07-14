## Writing a shaped crafting recipe

### I. Getting known with the scheme

At first, let's look at the scheme of the `.json` file that contains a **shaped** recipe:
```json5
{
  "type": "crafting_shaped", //the type of recipe
  "name": "<recipe name>", //the name of recipe, NOT NEEDED, just to describe the recipe
  "pattern": [/* strings */], //pattern of a recipe
  "keys": [
    { /* item descriptor WITH A KEY */ },
    { /* item descriptor WITH A KEY */ },
    { /* item descriptor WITH A KEY */ }
  ],
  "result": { /* item descriptor */ }
}
```

The best way to explain the way to implement a recipe is to show an example!

### II. Realistic example

Let's assume we need to create a recipe for a `Molten Sword` with a custom name `The Destoyer of Fish` and such craft pattern:

![](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/shaped_crafting_1.png)

_Ingredients: 4x `Iron Ingot`, 2x `Lava Fish`, 1x `Iron Sword`_

### III. Creating a pattern

Now let's represent this recipe in a text pattern so we could understand better:
```text
 XO
XOX
WX

X - Iron Ingot
O - Lava Fish
W - Iron Sword
```

You see something? Yeah, we've just created a pattern for our `.json` recipe:
```json5
{
  "pattern": [" XO", "XOX", "WX "]
}
```

For empty spaces (no item), use a space character (` `).

Please, use uppercase english alphabet character to identify each pattern key (i.e. `A`, `G`, `K`, etc.). Other characters are untested and might be unstable.

### IV. Getting item descriptor for each item

Assuming you've already read previous guides and [know how to obtain an item descriptor](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/FIND_ID_REINDEV.md), I'll just provide ready one:

**For `Iron Ingot`:**
```json5
{
  "item": "item.iron_ingot"
}
```

**For `Lava Fish`:**
```json5
{
  "item": "item.fish.lava_fish",
  "meta": 1
}
```

**For `Iron Sword`:**
```json5
{
  "item": "item.iron_sword"
}
```

**For `Molten Sword` with the custom name `The Destroyer of Fish`:**
```json5
{
  "item": "item.molten_sword",
  "displayName": "The Destroyer of Fish"
}
```

### V. Writing keys

This is an important part! We need to key each input element according to the pattern we created:
```text
X - Iron Ingot
O - Lava Fish
W - Iron Sword
```

**For `Iron Ingot` (with key `X`:**
```json5
{
  "key": "X",
  "item": "item.iron_ingot"
}
```

**For `Lava Fish` (with key `O`):**
```json5
{
  "key": "O",
  "item": "item.fish.lava_fish",
  "meta": 1
}
```

**For `Iron Sword` (with key `W`):**
```json5
{
  "key": "W",
  "item": "item.iron_sword"
}
```

### VI. Combining everything into `.json`

Next thing is to simply add everything we created to a combined json file:

```json5
{
  "type": "crafting_shaped",
  "pattern": [" XO", "XOX", "WX "],
  "keys": [
    {
      "key": "X",
      "item": "item.iron_ingot"
    },
    {
      "key": "O",
      "item": "item.fish.lava_fish",
      "meta": 1
    },
    {
      "key": "W",
      "item": "item.iron_sword"
    }
  ],
  "result": {
    "item": "item.molten_sword",
    "displayName": "The Destroyer of Fish"
  }
}
```

That's all! Now you can test it by putting it in `config/mamasrecipes` folder [(see how)](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/LOADING_RECIPE.md).

[Get the `.json` file of this example!](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/examples/the_destroyer_of_fish.json)

![Preview image](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/shaped_crafting_2.png)