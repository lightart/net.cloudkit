package pattern.pattern_01;

public class DogFactory extends AbstractAnimalFactory {

    public Animal getAnimal(){
        System.out.println("Dog");
        return new Dog();
    }
}
