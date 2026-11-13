import java.util.concurrent.ThreadLocalRandom;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public record Spot(String name, double latitude, double longitude) implements Comparable<Spot> {

    public static String randomName(){
        String name = "";
        name += (char) ((Math.random() * 26) + 97);
        name += (char) ((Math.random() * 26) + 97);
        name += (char) ((Math.random() * 26) + 97);
        name += (char) ((Math.random() * 26) + 97);
        return name;
    }

    static Spot randomSpot(){
        return new Spot(randomName(),Math.asin((Math.random() * 2) - 1) , ThreadLocalRandom.current().nextInt(-180, 181));
    }

    public int compareTo(Spot other){
        if(this.longitude > other.longitude){
            return 1;
        } else if(this.longitude == other.longitude){
            return 0;
        } else {
            return -1;
        }
    }


}
