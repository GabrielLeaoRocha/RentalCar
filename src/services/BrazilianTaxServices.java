package services;

import interfaces.Tax;

public abstract class BrazilianTaxServices implements Tax {

    public Double value;

    public void calcTax(double value){
        double aux;
        if(value > 100){
            aux = value * 0.15;
        }
        else{
            aux = value * 0.2;
        }
        this.value = aux;
    }
}
