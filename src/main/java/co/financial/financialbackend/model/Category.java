package co.financial.financialbackend.model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Category {
    private final String name;
    private final String description;

    @Contract("_ -> new")
    public static @NotNull
    Category of(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("Category name cannot be null or empty.");
        }

        return new Category(name, null);
    }

    @Contract("_, _ -> new")
    public static @NotNull Category of(String name, String description) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("Category name cannot be null or empty.");
        }

        return new Category(name, description);
    }

    private Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Category category = (Category) o;
        return name.equals(category.name) && description.equals(category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
