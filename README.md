### Garden

Usage

#### Creating a bed

``` java
int numberOfSquares = 10;
Bed bed = new Bed(numberOfSquares);
```

#### Sowing a vegetable

``` java
String name = "tomato";
int daysToHarvest = 60;
int size = 16;

Vegetable tomato = new Vegetable(name, daysToHarvest, size);

int squareToSow = 0;
bed.sow(0, tomato);
```

#### Checking harvest date

``` java
// Directly from the vegetable
LocalDate harvestDate = tomato.getHarvestDate();

// or from the vegetables sown in the bed
List<Vegetable> sownVegetables = bed.getSownVegetables(squareToSow);
sownVegetables.forEach(veg -> System.out.println(veg.getHarvestDate()));
```

#### Checking whether vegetable can be sown in square

This is useful since we're using the square foot gardening method and there are a limited number of vegetables we can
plant per square foot.

``` java
int square = 0;
boolean canSow = bed.canSow(square, vegetable);
```