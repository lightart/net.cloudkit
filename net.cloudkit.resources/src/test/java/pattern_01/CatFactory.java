package pattern_01;

public class CatFactory extends AbstractAnimalFactory {

    public Animal getAnimal(){
        System.out.println("Cat");
        return new Cat();
    }
}
