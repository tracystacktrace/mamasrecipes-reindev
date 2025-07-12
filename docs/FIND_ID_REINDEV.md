## What's item descriptor? How can I get data about the item and write an item descriptor of it?

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

### I. Getting data from the game

First of all, launch a `ReIndev` instance. Open any world where you can open a creative menu, and take an item you want to get data about.

Let's assume, you want to get information about `Orange Stucco` with a count of `2`. You take the corresponding item from creative menu and hover a mouse around it **while holding** `CTRL` button on a keyboard.

The tooltip should show an extended info:

![Image of tooltip 1](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/tooltip_1.png)

The last line here is `#tile.stucco.orange:190,14`. Let's break down it into several parts:
- `tile.stucco.orange`: is a string identifier of this item
- `190`: is an integer identifier of this item
- `14`: is a metadata of this item

Sometimes the tooltip may not show the metadata value (see image below). This basically means the metadata is `0` or is ignored.

![Image of tooltip 2](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/tooltip_2.png)

### II. Writing an item description

There's a schema for item description json:
```json5
{
  "item": "<item identifier>", //item identifier
  "meta": 0, //item metadata
  "count": 1, //item count
}
```

For the identifier part (`"item"`), you can choose either using the string or the id integer.
The `"meta"` and `"count"` parts act a little bit different: you can leave them ignored (not add into the json at all), and they'd be defaulted to `0` (for `"meta"`) and `1` (for `"count"`).
In this example, I'll put `2` for `"count"` and `14` for `"meta"`.
```json5
{
  "item": "tile.stucco.orange", //the string identifier
  "meta": 14, //the given metadata
  "count": 2 //assume we need 2 of it
}
```
```json5
{
  "item": 190, //the int identifier
  "meta": 14, //the given metadata
  "count": 2 //assume we need 2 of it
}
```

### III. EXTRA! Adding custom display names

If you want to add a custom display name for your craft result item, you can add it via `"displayName"` attribute:

```json5
{
  "item": "item.diamond_sword",
  "displayName": "Cool Diamond Sword"
}
```

## Other examples

---

![Example 1](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/example_tooltip_1.png)

```json5
{
  "item": "item.bed"
}
```
```json5
{
  "item": 355
}
```

---

![Example 2](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/example_tooltip_2.png)

```json5
{
  "item": "item.fish.sakurakoi",
  "meta": 14
}
```
```json5
{
  "item": 414,
  "meta": 14
}
```

---

![Example 3](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/images/example_tooltip_3.png)

```json5
{
  "item": "tile.glowing_crimson_planks",
  "count": 5
}
```
```json5
{
  "item": 1324,
  "count": 5
}
```