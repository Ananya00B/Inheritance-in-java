# Inheritance-in-java
# The files attached here are to specify, how we can inherit the common code from a file and avoid rewritting the same code everytime.
Here the operations are implemented on Shop, Vegetable shop and Fruit shop.
VegetableShop class has all the methods: setPrice, addVegetable, getPrice, listItem, checkList, buyItem.
Similarly FruitShop class has all the methods: setPrice, addVegetable, getPrice, listItem, checkList, buyItem.

Shop2 parent class is designed to capture all the common members and methods used in FruitShop2 and VegetableShop2. Shop2 contains setPrice, addVegetable, getPrice, listItem, checkList, buyItem methods, and common code for constructors too. Both FruitShop2 and VegetableShop2 implement all the common methods directly from Shop2 class.
ShopMain class contains the code with objects of VegetableShop class and includes getPrice and checkItem method’s implementation to check for the exceptions.
NoProperFormatException, NoItemException, NoStockException classes handle the exception thrown by the inputs/methods.

The code insides the main in the FruitShop and FruitShop2,  VegetableShop, and  VegetableShop2 is almost similar. It shows the difference that Inheritance doesn’t affect implementation.
