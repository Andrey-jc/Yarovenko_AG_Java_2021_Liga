package bases;

public class User {
    private String name;
    private Long id;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
