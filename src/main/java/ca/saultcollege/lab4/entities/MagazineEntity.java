package ca.saultcollege.lab4.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@DiscriminatorValue("Magazine")
public class MagazineEntity extends PublicationEntity {
//    @Column(nullable = false)
    private int orderQty;

    private LocalDate currentIssue;

    public MagazineEntity() {
    }

    public MagazineEntity(int orderQty, LocalDate currentIssue, String title, double price, int copies) {
        super(title, price, copies);
        this.orderQty = orderQty;
        this.currentIssue = currentIssue;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public LocalDate getCurrentIssue() {
        return currentIssue;
    }

    public void setCurrentIssue(LocalDate currentIssue) {
        this.currentIssue = currentIssue;
    }

    public void sellItem() {
        System.out.println("Selling magazine: " + getTitle());
    }

    @Override
    public String toString() {
        return "MagazineEntity{" +
                "title='" + getTitle() + '\'' +
                ", price=" + getPrice() +
                ", copies=" + getCopies() +
                ", orderQty=" + orderQty +
                ", currentIssue=" + currentIssue +
                '}';
    }
}