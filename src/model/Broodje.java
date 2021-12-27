package model;

import java.util.ArrayList;

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

    // De voorraad van het broodje wordt aangepast met de ingegeven waarden.
    public void aanpassenVoorraad (int i) {
        this.actualStock += i;
    }

    // De voorraad van het broodje wordt geset.
    public void setActualStock(int actualStock) {
        if (actualStock < 0) {
            throw new IllegalArgumentException("The actual stock cannot be less than 0.");
        }
        this.actualStock = actualStock;
    }
    // De hoeveelheid verkochte broodjes voor dit type broodje wordt geset.
    private void setSoldAmount(int soldAmount) {
        if (soldAmount < 0) {
            throw new IllegalArgumentException("The sold amount cannot be less than 0.");
        }
        this.soldAmount = soldAmount;
    }

    // De verkoopprijs van het type broodje wordt geset.
    private void setSalePrice(double salePrice) {
        if (salePrice < 0) {
            throw new IllegalArgumentException("The actual stock cannot be less than 0.");
        }
        this.salePrice = salePrice;
    }

    // De naam van het type broodje wordt geset.
    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty.");
        }
        this.name = name;
    }

    // Maak een arraylist van strings van de volgende variabelen:
    // a) Naam
    // b) Verkoopprijs
    // c) De actual Stock
    // d) De verkoop hoeveelheid
    // En geef deze terug.
    public ArrayList<String> getArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(this.name);
        list.add(String.valueOf(this.salePrice));
        list.add(String.valueOf(this.actualStock));
        list.add(String.valueOf(this.soldAmount));
        return list;
    }
    // Geef de naam van het type broodje terug.
    public String getName() {
        return name;
    }
    // Geef de verkoopprijs van het type broodje terug
    public double getSalePrice() {
        return salePrice;
    }
    // Geef de huidige hoeveelheid voorraad terug van dit type broodje.
    public int getActualStock() {
        return actualStock;
    }
    // Geef de verkochte hoeveelheid van dit type broodje terug.
    public int getSoldAmount() {
        return soldAmount;
    }

    // Geeft een boolean terug wanneer een (Broodje) Object gelijk is aan dit broodje
    @Override
    public boolean equals (Object o) {
        Broodje broodje = (Broodje) o;
        return this.getName().equals(broodje.getName());
    }

    // Geef een string met de naam, verkoopprijs, huidige voorraad en verkochte hoeveelheid
    @Override
    public String toString() {
        return this.getName() + " - " + this.getSalePrice() + "€ - " + this.getActualStock() + " - " + this.getSoldAmount();
    }
}
