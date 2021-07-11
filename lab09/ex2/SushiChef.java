public class SushiChef extends Chef  {

    public void parse(String meal, int minutes) {
        if (canCookMeal(meal)) {
            System.out.printf("\n%s: Starting to cook %s. Out in %d minutes!",this.getClass().getSimpleName(),meal,minutes);
            System.out.println();
        } else {
            System.out.printf("\n%s: I can't cook that.",this.getClass().getSimpleName());
            super.parse(meal,minutes);
        }
    }
}
