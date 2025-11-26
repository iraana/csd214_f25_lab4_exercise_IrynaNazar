package ca.saultcollege.lab4.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Book") // 1. Specifies the value for this subclass in the discriminator column
public class BookEntity extends PublicationEntity {

    @Column(nullable = false)
    private String author;

    public BookEntity() {
    }

    public BookEntity(String author) {
        this.author = author;
    }

    public BookEntity(String author, String title, double price, int copies) {
        super(title, price, copies);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void sellItem() {
        System.out.println("Selling book: " + getTitle());
    }

    // do i need initialize() and edit()??

    // equals() and hashCode() are inherited from Product and don't need to be overridden.
    // The parent implementation using the 'id' field is sufficient.

    @Override
    public String toString() {
        return "BookEntity{" +
                "author='" + author + '\'' +
                ", title='" + getTitle() + '\'' +
                ", price=" + getPrice() +
                ", copies=" + getCopies() +
                '}';
    }

}