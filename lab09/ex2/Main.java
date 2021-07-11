import java.util.HashMap;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) {

        HashMap<String,Integer> orders = new HashMap<String,Integer>();
        orders.put("veggie burger",19);
        orders.put("Pasta Carbonara",14);
        orders.put("PLAIN pizza, no toppings!",7);
        orders.put("sushi nigiri and sashimi",14);
        orders.put("salad with tuna",14);
        orders.put("strawberry ice cream and waffles dessert",5);
        orders.put("Pasta spaguetti",12);
        orders.put("CBO burger",4);
        orders.put("molotof dessert",8);
        orders.put("wasabi and salmon sushi",20);
        orders.put("pepperonni pizza",16);
        orders.put("bread for crazy people",2);
        orders.put("pastel de nata dessert",3);

        Chef chefParser = new BurgerChef().nextChef(
            new DessertChef().nextChef(
                new PastaChef().nextChef(
                    new PizzaChef().nextChef(
                        new SushiChef()))));

        for (Map.Entry<String,Integer> entry : orders.entrySet()) {
            chefParser.parse(entry.getKey(),entry.getValue());
        }
    }

}
