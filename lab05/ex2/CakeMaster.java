package ex2;

public class CakeMaster {

    private CakeBuilder cakeBuilder;

    
    public void createCake(Shape shape,int camadas, String m){

        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(shape);
        cakeBuilder.addMessage(m);
        cakeBuilder.getCake().setNumCakeLayers(camadas);

    }

    public void createCake(int camadas, String m){

        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(Shape.Circular);
        cakeBuilder.addMessage(m);
        cakeBuilder.getCake().setNumCakeLayers(camadas);
    }

    public void createCake(String m){

        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(Shape.Circular);
        cakeBuilder.addMessage(m);
    }

    public void setCakeBuilder(CakeBuilder cakeBuilder){
        this.cakeBuilder = cakeBuilder;
    }

    public Cake getCake(){
        return this.cakeBuilder.getCake();
    }

}
