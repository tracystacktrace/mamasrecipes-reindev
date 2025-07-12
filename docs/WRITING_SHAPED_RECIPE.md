## Writing a shaped crafting recipe

### I. Getting known with scheme

At first, let's look at the scheme of the `.json` file that contains a **shaped** recipe:
```json5
{
  "type": "crafting_shaped", //the type of recipe
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

_Ingredients: 4x Iron Ingot, 2x Lava Fish, 1x Iron Sword_

### III. Creating a pattern

Now we need to write a plain-text pattern for this recipe:
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

**For `Molten Sword` with custom name `The Destroyer of Fish`:**
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
  "type": "crafting_shaped", //the type of the recipe
  "pattern": [" XO", "XOX", "WX "], //pattern of the recipe
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

That's all! Now you can test it by putting it in `config/mamasrecipes` folder [(see how)](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/WRITING_SHAPED_RECIPE.md).

![Preview image](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/shaped_crafting_2.png)