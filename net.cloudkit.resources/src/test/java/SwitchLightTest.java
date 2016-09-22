
public class SwitchLightTest {

    public static void main(String[] args) {
        Light light = new Light();
        LightSwitch lightSwitch = new LightSwitch(light);
        lightSwitch.on();
        lightSwitch.off();
    }
}
