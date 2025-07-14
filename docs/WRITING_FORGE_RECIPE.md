## Writing a forge recipe

### I. Getting known with the scheme

At first, let's look at the scheme of the `.json` file that contains a **forge** recipe:
```json5
{
  "type": "forge",
  "name": "<recipe name>", //the name of recipe, NOT NEEDED, just to describe the recipe
  "input": { /* item descriptor */ },
  "result": { /* item descriptor */ }
}
```

The best way to explain the way to implement a recipe is to show an example!

### II. Realistic example

Let's assume we need to create a recipe for `5x Iron Ingot` which is obtained via smelting full durability `Iron Helmet`:

![](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/forge_1.png)

### III. Getting item descriptor for each item

Assuming you've already read previous guides and [know how to obtain an item descriptor](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/FIND_ID_REINDEV.md), I'll just provide ready one:

**For `Iron Ingot`:**
```json5
{
  "item": "item.iron_ingot",
  "count": 5
}
```

**For `Stone`:**
```json5
{
  "item": "item.iron_helmet"
}
```

### IV. Combining everything into `.json`

Next thing is to simply add everything we created to a combined json file:

```json5
{
  "type": "forge",
  "input": {
    "item": "item.iron_helmet"
  },
  "result": {
    "item": "item.iron_ingot",
    "count": 5
  }
}
```

That's all! Now you can test it by putting it in `config/mamasrecipes` folder [(see how)](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/LOADING_RECIPE.md).

[Get the `.json` file of this example!](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/examples/recycling_iron_helmet.json)

![Preview image](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/forge_2.png)
