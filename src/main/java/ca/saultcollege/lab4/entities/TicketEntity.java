package ca.saultcollege.lab4.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Ticket")
public class TicketEntity extends ProductEntity {
//    @Column(nullable = false)
    private String description;

    public TicketEntity() {
    }

    public TicketEntity(String name, String description, double price) {
        super(name, price);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void sellItem() {
        System.out.println("Selling ticket: " + description);
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "description='" + description + '\'' +
                ", price=" + getPrice() +
                '}';
    }

}