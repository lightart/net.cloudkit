public class LightSwitch {

    private Light light;

    public LightSwitch(Light light) {
        this.light = light;
    }

    public void on() {
        System.out.println("On");
        light.setState(Light.LightStatus.LIGHTEN);
    }

    public void off() {
        System.out.println("Off");
        light.setState(Light.LightStatus.EXTINGUISH);
    }
}
