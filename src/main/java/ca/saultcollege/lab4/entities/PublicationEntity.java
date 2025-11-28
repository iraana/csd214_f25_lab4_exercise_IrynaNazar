package ca.saultcollege.lab4.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
@DiscriminatorValue("Publication")
public abstract class PublicationEntity extends ProductEntity {

//    @Column(nullable = false)
    private String title;

    @Column(name = "copies")
    private int copies;

    public PublicationEntity() {

    }
    public PublicationEntity(String title, double price, int copies) {
        super(title, price);
        this.title = title;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public String toString() {
        return "PublicationEntity{" +
                "title='" + title + '\'' +
                ", copies=" + copies +
                ", price=" + getPrice() +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublicationEntity)) return false;
        if (!super.equals(o)) return false;
        PublicationEntity that = (PublicationEntity) o;
        return copies == that.copies && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, copies);
    }
}