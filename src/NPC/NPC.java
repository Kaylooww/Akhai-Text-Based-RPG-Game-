package NPC;

public abstract class NPC {
    protected String name;
    protected String description;

    public NPC(String name, String description) {
        this.name = name;
        this.description = description;
    }
//
    public abstract void interact();

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
