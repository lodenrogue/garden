## Garden

### Creating a bed

``` java
int numberOfSquares = 10;
Bed bed = new Bed(numberOfSquares);
```

### Sowing a vegetable

Since we're using the square foot gardening method size should be 16 divided by how many of that vegetable we can sow in
a square. For example, square foot gardening says we can only sow 1 tomato in a square foot so the size would be 16 / 1
which equals 16.

``` java
String name = "tomato";
int daysToHarvest = 60;
int size = 16;

Vegetable tomato = new Vegetable(name, daysToHarvest, size);
int squareToSow = 0;

// Sow now
bed.sow(squareToSow, tomato);

// Or with a given sowing date
LocalDate sowingDate = LocalDate.now().plusDays(10);
bed.sow(squareToSow, tomato, sowingDate);
```

### Getting sowing date

Note: Sowing date will only exist if a vegetable has been sown

``` java
// Directly from the vegetable
LocalDate sowingDate = tomato.getSowingDate();

// or from the vegetables sown in the bed
List<Vegetable> sownVegetables = bed.getSownVegetables(squareToSow);
sownVegetables.forEach(veg -> System.out.println(veg.getSowingDate()));
```

### Getting harvest date

Note: Harvest date will only exist if a vegetable has been sown

``` java
// Directly from the vegetable
LocalDate harvestDate = tomato.getHarvestDate();

// or from the vegetables sown in the bed
List<Vegetable> sownVegetables = bed.getSownVegetables(squareToSow);
sownVegetables.forEach(veg -> System.out.println(veg.getHarvestDate()));
```

### Checking whether vegetable can be sown in square

This is useful since we're using the square foot gardening method and there are a limited number of vegetables we can
plant per square foot.

``` java
int square = 0;
boolean canSow = bed.canSow(square, vegetable);
```

### Harvesting a vegetable

Note: Harvested date will only exist if a vegetable has been harvested

``` java
// Harvest now
bed.harvest(tomato);

// or at a given harvest date
LocalDate harvestDate = LocalDate.now().minusDays(10);
bed.harvest(tomato, harvestDate);

LocalDate harvestedDate = tomato.getHarvestedDate();
```

### Getting number of squares in bed

``` java
int numOfSquares = bed.getNumberOfSquares();
```
