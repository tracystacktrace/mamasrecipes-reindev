## Writing a refridgifreezer recipe

### I. Getting known with the scheme

At first, let's look at the scheme of the `.json` file that contains a **refridgifreezer** recipe:
```json5
{
  "type": "refridgifreezer",
  "name": "<recipe name>", //the name of recipe, NOT NEEDED, just to describe the recipe
  "input": { /* item descriptor */ },
  "result": { /* item descriptor */ }
}
```

The best way to explain the way to implement a recipe is to show an example!

### II. Realistic example

Let's assume we need to create a recipe for `Snowball` obtained via freezing `Slimeball`:

![](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/refridgifreezer_1.png)

### III. Getting item descriptor for each item

Assuming you've already read previous guides and [know how to obtain an item descriptor](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/FIND_ID_REINDEV.md), I'll just provide ready one:

**For `Snowball`:**
```json5
{
  "item": "item.snowball"
}
```

**For `Slimeball`:**
```json5
{
  "item": "item.slimeball"
}
```

### IV. Combining everything into `.json`

Next thing is to simply add everything we created to a combined json file:

```json5
{
  "type": "refridgifreezer",
  "input": {
    "item": "item.slimeball"
  },
  "result": {
    "item": "item.snowball"
  }
}
```

That's all! Now you can test it by putting it in `config/mamasrecipes` folder [(see how)](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/LOADING_RECIPE.md).

[Get the `.json` file of this example!](https://github.com/tracystacktrace/mamasrecipes-reindev/blob/main/docs/examples/freezing_slimeball.json)

![Preview image](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/refridgifreezer_2.png)
