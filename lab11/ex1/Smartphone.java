public class Smartphone {
    
    private String model;
    private String processor;
    private Double price;
    private Double memory;
    private String camera;

    public Smartphone(String model, String processor, Double price, Double memory, String camera) {
        this.model = model;
        this.processor = processor;
        this.price = price;
        this.memory = memory;
        this.camera = camera;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    @Override
    public String toString() {
        return "[" + model + "] Processor: " + processor + " / Camera: " + camera + " / Memory (GB): " + memory + " / Price: " + price + "$.";
    }    

}
