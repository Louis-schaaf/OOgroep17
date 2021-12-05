package model;

public class BelegSoort {
    private String name;
    private double salePrice;
    private int actualStock;
    private int soldAmount;

    public BelegSoort(String name, double salePrice, int actualStock, int soldAmount) {
        setName(name);
        setSalePrice(salePrice);
        setActualStock(actualStock);
        setSoldAmount(soldAmount);
    }

    public BelegSoort() {
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
        BelegSoort beleg = (BelegSoort) o;
        return this.getName().equals(beleg.getName());
    }

    @Override
    public String toString() {
        return this.getName() + " - " + this.getSalePrice() + "€ - " + this.getActualStock() + " - " + this.getSoldAmount();
    }
}
