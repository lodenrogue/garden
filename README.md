## Garden

### Creating a bed

``` java
int numberOfSquares = 10;
Bed bed = new Bed(numberOfSquares);

// Or with a given rotation period. Default is 3 years
int rotationPeriodInYears = 4;
Bed bed = new Bed(numberOfSquares, rotationPeriodInYears);
```

### Getting number of squares in bed

``` java
int numOfSquares = bed.getNumberOfSquares();
```

### Sowing a crop

Since we're using the square foot gardening method size should be 16 divided by how many of that crop we can sow in a
square. For example, square foot gardening says we can only sow 1 tomato in a square foot so the size would be 16 / 1
which equals 16.

``` java
String name = "tomato";
String family = "nightshade";
int daysToHarvest = 60;
int size = 16;

Crop tomato = new Crop(name, family, daysToHarvest, size);
int squareToSow = 0;

// Sow now
bed.sow(squareToSow, tomato);

// Or with a given sowing date
LocalDate sowingDate = LocalDate.now().plusDays(10);
bed.sow(squareToSow, tomato, sowingDate);
```

### Getting sowing date

Note: Sowing date will only exist if a crop has been sown

``` java
// Directly from the crop
LocalDate sowingDate = tomato.getSowingDate();

// or from the crops sown in the bed
List<Crop> sownCrops = bed.getSownCrops(squareToSow);
sownCrops.forEach(crop -> System.out.println(crop.getSowingDate()));
```

### Getting harvest date

Note: Harvest date will only exist if a crop has been sown

``` java
// Directly from the crop
LocalDate harvestDate = tomato.getHarvestDate();

// or from the crops sown in the bed
List<Crop> sownCrops = bed.getSownCrops(squareToSow);
sownCrops.forEach(crop -> System.out.println(crop.getHarvestDate()));
```

### Checking whether crop can be sown in square

This is useful since we're using the square foot gardening method and there are a limited number of crops we can plant
per square foot.

``` java
int square = 0;
boolean canSow = bed.hasSpaceToSow(square, crop);
```

### Harvesting a crop

Note: Harvested date will only exist if a crop has been harvested

``` java
// Harvest now
bed.harvest(tomato);

// or at a given harvest date
LocalDate harvestDate = LocalDate.now().minusDays(10);
bed.harvest(tomato, harvestDate);

LocalDate harvestedDate = tomato.getHarvestedDate();
```

### Getting history of a square

A crop will be in the history of a square after it has been harvested. This is useful knowledge for crop rotation.
History is sorted oldest harvested crop first.

``` java
List<Crop> history = bed.getHistory(square);
```

### Checking whether a bed is past a rotation period for a given crop

Return true if the rotation period has passed since the last harvest of this crop's family.

``` java
boolean isPastRotationPeriod = bed.isPastRotationPeriodFor(crop);
```

### Getting rotation end date for a given crop

Returns the end date of the current rotation period for a given crop's family. If there is no history for a given crop's
family then null is returned.

``` java
LocalDate rotationEndDate = bed.getRotationEndDateFor(crop);
```

### Removing a crop

We may want to remove a crop from sown crops or history

``` java
bed.remove(crop);
```

### Saving and loading a bed

``` java
String fileName = "bed.json";

SaveStrategy saveStrategy = new JsonFileSaveStrategy(fileName);
saveStrategy.save(bed);

LoadStrategy loadStrategy = new JsonFileLoadStrategy(fileName);
Bed loadedBed = loadStrategy.load();
```