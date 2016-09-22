public class Light {

    enum LightStatus {
        LIGHTEN, EXTINGUISH
    }

    public void setState(LightStatus lightStatus) {
        System.out.println(lightStatus.name());
    }

}
