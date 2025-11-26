package ca.saultcollege.lab4.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class DiscMagEntity extends MagazineEntity {
//    @Column(nullable = false)
    private boolean hasDisc;

    public DiscMagEntity() {
    }

    public DiscMagEntity(boolean hasDisc, int orderQty, Date currentIssue, String title, double price, int copies) {
        super(orderQty, currentIssue, title, price, copies);
        this.hasDisc = hasDisc;
    }

    public boolean isHasDisc() {
        return hasDisc;
    }

    public void setHasDisc(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    @Override
    public void sellItem() {
        System.out.println("Selling disc magazine: " + getTitle() + " (Has disc: " + hasDisc + ")");
    }

    @Override
    public String toString() {
        return "DiscMagEntity{" +
                "title='" + getTitle() + '\'' +
                ", price=" + getPrice() +
                ", copies=" + getCopies() +
                ", orderQty=" + getOrderQty() +
                ", currentIssue=" + getCurrentIssue() +
                ", hasDisc=" + hasDisc +
                '}';
    }
}