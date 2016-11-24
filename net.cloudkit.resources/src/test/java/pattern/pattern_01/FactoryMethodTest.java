package pattern.pattern_01;

public class FactoryMethodTest {

    public static void main(String[] args) {
        AbstractAnimalFactory af = new DogFactory();
        Animal a = af.getAnimal();
    }

}
