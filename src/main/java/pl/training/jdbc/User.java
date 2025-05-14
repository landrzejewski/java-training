package pl.training.jdbc;

public record User(Long id, String name, String email) {

    public User(String name, String email) {
        this(null, name, email);
    }

    public User withId(Long id) {
        return new User(id, name, email);
    }

}
