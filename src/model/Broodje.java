package model;

public class Broodje {
    private String name;
    private double salePrice;
    private int actualStock;
    private int soldAmount;

    public Broodje(String name, double salePrice, int actualStock, int soldAmount) {
        setName(name);
        setSalePrice(salePrice);
        setActualStock(actualStock);
        setSoldAmount(soldAmount);
    }

    public Broodje() {
        this("",0.0,0,0);
    }

    private void setActualStock(int actualStock) {
        if (actualStock < 0) {
            throw new IllegalArgumentException("The actual stock cannot be less than 0.");
        }
        this.actualStock = actualStock;
    }

    private void setSoldAmount(int soldAmount) {
        if (soldAmount < 0) {
            throw new IllegalArgumentException("The sold amount cannot be less than 0.");
        }
        this.soldAmount = soldAmount;
    }

    private void setSalePrice(double salePrice) {
        if (salePrice < 0) {
            throw new IllegalArgumentException("The actual stock cannot be less than 0.");
        }
        this.salePrice = salePrice;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public int getActualStock() {
        return actualStock;
    }

    public int getSoldAmount() {
        return soldAmount;
    }

    @Override
    public boolean equals (Object o) {
        Broodje broodje = (Broodje) o;
        return this.getName().equals(broodje.getName());
    }

    @Override
    public String toString() {
        return this.getName() + " - " + this.getSalePrice() + "€ - " + this.getActualStock() + " - " + this.getSoldAmount();
    }
}
