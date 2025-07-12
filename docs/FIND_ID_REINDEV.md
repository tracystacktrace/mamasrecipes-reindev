## How can I find an ID/metadata of an item?

### I. Getting info from game

First of all, launch a `ReIndev` instance. Open any world where you can open a creative menu, and take an item you want to get data about.

Let's assume, you want to get information about `Orange Stucco` with a count of `2`. You take the corresponding item from creative menu and hover a mouse around it **while holding** `CTRL` button on a keyboard.

The tooltip should show an extended info:

![Image of tooltip 1](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/tooltip_1.png)

The last line here is `#tile.stucco.orange:190,14`. Let's break down it into several parts:
- `tile.stucco.orange`: is a string identifier of this item
- `190`: is an integer identifier of this item
- `14`: is a metadata of this item

Sometimes the tooltip may not show the metadata value (see image below). This basically means the metadata is `0` or is ignored.

![Image of tooltip 2](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/tooltip_2.png)

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

### Other examples

![Example 1](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/example_tooltip_1.png)

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

![Example 2](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/example_tooltip_2.png)

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

![Example 3](https://github.com/tracystacktrace/mamasrecipes-reindev/raw/main/docs/example_tooltip_3.png)

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