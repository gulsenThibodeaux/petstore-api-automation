package gulproject.models;

public class Pet {
    private long id;
    private String name;
    private String status;

    // Constructor
    public Pet(long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    // Getters (Jackson needs these to convert to JSON)
    public long getId() { return id; }
    public String getName() { return name; }
    public String getStatus() { return status; }

    // Setters (in case we want to update fields later)
    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setStatus(String status) { this.status = status; }
}