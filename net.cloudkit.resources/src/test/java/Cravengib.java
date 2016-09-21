// 静态方法不应被重写
class Base{
    static void amethod(){
        System.out.println("Base.amethod");
    }
}
public class Cravengib extends Base{
    public static void main(String arg[]){
        Base cg = new Cravengib();
        // Cravengib cg = new Cravengib();
        cg.amethod();
    }
    static void amethod(){
        System.out.println("Cravengib.amethod");
    }
}
