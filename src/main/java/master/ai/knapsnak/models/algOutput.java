package master.ai.knapsnak.models;

public class algOutput {
    private String name;
    private int weight;
    private int value;
    private boolean inbag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isInbag() {
        return inbag;
    }

    public void setInbag(boolean inbag) {
        this.inbag = inbag;
    }
}
