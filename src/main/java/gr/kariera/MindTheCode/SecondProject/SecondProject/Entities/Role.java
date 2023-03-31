package gr.kariera.MindTheCode.SecondProject.SecondProject.Entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Role {

    @Id
    private String type;

    public Role(String type) {
        this.type = type;
    }

    public Role() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return type.equals(role.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
