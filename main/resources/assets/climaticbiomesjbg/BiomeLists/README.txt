Using Biome List

Inside the folders you find a variety of files.  The names explain themselves.  These are for adding biomes from other mods, and are not hard to add biome to, though you must becareful as a typo could crash Minecraft.  "Minecraft" folder is vanilla biomes and those from this mod.  Once you have run the mod there should also be file called BiomeList.txt; this is where you can find the full resource location names of all biome installed as of the last time you ran the game.  (Writing this can be turned off if you know you haved added any or don't want to update the config).

To add biome you must edit the relevant files in the biomes folder.  Each adds biomes to a specific climate area.  There are four tag you can use to add biomes: biome, noise, seed, temp, and wetness.  Each is followed by biome information in parentheses.  In the exaples below modid:name means the full resource location and n means a whole number.  To avoid mistakes its a good idea to cut-and-paste from the BiomeList.txt.

The biome tag will simply add a biome:

biome(modid:name)

The noise tage will add two biome which will be selected based on a noise function.  This is usually used to add things like mesas and plataeus.

noise(modid:name, n, modid:name)

The first biome will be selected if the noise level is less than n, otherwise the second.  Noise will range from 0 to 9. and 5 is a good choise for a mesa biome where one is the flat land and the other is the plataeu.

The seed tag look similar but with it the first biome has a 1 in n chance of appearing, otherwise you will get the second.  This is used for rare biomes or unusual variants of a biome.

seed(modid:name, n, modid:name)

The temp tag again looks similar, but here the first biome appear at a lower temperature and the second at a higher, while n the number below which the colder biome will appear.  This is used for things like seperating snowy from non-snowy taigas or push ice formation to the coldest areas.  More about temperatures appears later.

temp(modid:name, n, modid:name)

The taiga tag is special version of the tmep tag.  I does not take a temperature variable; instead this will be be set based on which climate table you are using.  The first biome is warmer (usually snowy), the second is warmer.

taiga(modid:name, modid:name)

The wetness tag is similar but here the first biome will appear in dryer areas, with n being the cutoff wetness.  This is usually used to for very wet forests such as temporate rain forests to only appear in very wet areas.

wetness(modid:name, n, modid:name)

An example of how this can be used is in this file, which adds biome from the Traverse mod:
http://www.mediafire.com/file/r7ehfjs5ir6r8by/biomelists-traverse.zip/file


********************************

About Temperature, Wetness, and the Lists

Each list shows biome to appear at a different levels of temperature and wetness.  In general, there are five climate zone spread evenly accross five climate zone; each covers a range of five temperature levels:

0-4: Arctic (called “tundra,” it includes all the ice biomes)
5-9: Subarctic: Taiga and cold plains
10-14: Temporate: Forest and plains
15-19: Subtropical: Warm Forest, plains / savannas, scrub, and deserts
20-24: Tropical: Jungles and savannas

If you have Biome O'Plenty installed (and turned on in the config) things are different because BoP adds many biomes between the temperatures usual for subarctic and temporate, with BoP you have this:

0-3: Arctic
4-7: Subarctic
8-11: Cool Temporate: Cool forest and cool plains
12-15: Temporate
16-20: Subtropical
21-24: Tropical

Note that there are no vanilla biomes in the cool zone, and if BoP is not used this zone will not exist.

Oceans and special islands are different:

Frozen: Based on a noise function
Cold: temperature < 6
Cool: temperature < 12; vanilla ocean (pre-1.13) is here
Warm: temperature < 18
Hot: temperature at least 18

For swamps the temperature works like this:

Cold: temperature < 12
Cool: temperature < 16
Warm: temperature < 21
Hot: temperature at least 21

If ocean list are empty the lists will spread out from cool.  If a swamp list is emply it will spread out from warm.  If a special islands list is empty it will use normal islands instead; that is, it will mainland biomes.

Wetness is on a scale of one to ten, but the actual meaning is relative to the climate zone it appears in.  Specifically, since most real-world deserts are in the subtropics low wetness means a drier climate than the same number somewhere else.

There are few special lists.  Alpine is for mountain and is devided into dry and wet, with treed mountains treat as wet.  ChaparralScrub is the list for scrub, bush, and chaparral biomes that appear between plains and desests (mostly in the subtropics).  Parkland refers to an area between forest and plains biomes in the temperate zone; it may pick either a forest or plains biome, but is also where open forests go.  The special islands list are for biome that should usually exists as islands; if a biome has “island” or “archepeligo” in its name it probably belongs here.
