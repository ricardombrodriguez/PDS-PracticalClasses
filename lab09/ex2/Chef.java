public abstract class Chef {
    
    private Chef nextChef = null;

    public void parse(String meal, int minutes) {
        if (nextChef != null) {
            nextChef.parse(meal,minutes);
        } else {
            System.out.printf("\n!!! NO CHEF COULD PROCESS THAT ORDER !!! (%s)\n", meal);
        }
    }

    protected boolean canCookMeal(String meal) {
        String chef_type = this.getClass().getSimpleName().replaceAll("Chef","").toLowerCase();
        if (meal.toLowerCase().contains(chef_type)) {
            return true;
        } else {
            return false;
        }
    }

    public Chef nextChef(Chef nextChef) {
        this.nextChef = nextChef;
        return this;
    }

}
