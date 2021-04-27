package entities;

import services.BrazilianTaxServices;

import java.util.Date;

public class Contract {

    private Car car;
    private Date withdrawal;
    private Date delivery;
    private Double pricePerHour;
    private Double pricePerDay;
    private Double paymentWithoutTax;
    private BrazilianTaxServices tax;
    private Double totalAmount;

    public Contract(){
    }

    public Contract(Car car, Date withdrawal, Date delivery, Double pricePerHour, Double pricePerDay) {
        this.car = car;
        this.withdrawal = withdrawal;
        this.delivery = delivery;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.paymentWithoutTax = paymentWithoutTax();
        tax.calcTax(paymentWithoutTax);
        this.totalAmount = totalAmount();
    }



    //get & set
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Date withdrawal) {
        this.withdrawal = withdrawal;
    }

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }

    public Double getPaymentWithoutTax() {
        return paymentWithoutTax;
    }

    public void setPaymentWithoutTax(Double paymentWithoutTax) {
        this.paymentWithoutTax = paymentWithoutTax;
    }

    public Double getTax() {
        return tax.value;
    }

    public void setTax(Double tax) {
        this.tax.value = tax;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }



    //methods
    public double paymentWithoutTax(){
        double total;
        long time;

        time = (delivery.getTime() - withdrawal.getTime())/1000/60; //get minutes

        //720m = 12h
        if(time <= 720){
            total = this.pricePerHour * (Math.ceil((double) time/60));
        }
        else{
            total = this.pricePerDay * (Math.ceil((double) time/60/24));
        }

        return total;
    }

    public double totalAmount(){
        return this.paymentWithoutTax + this.tax.value;
    }

    @Override
    public String toString() {

        return "INVOICE " + getCar().getModel().toUpperCase() + ":\n" +
                "Basic payment: $" +
                String.format("%.2f",getPaymentWithoutTax()) + "\n" +
                "Tax: $" +
                String.format("%.2f",getTax()) + "\n" +
                "Total payment: $" +
                String.format("%.2f",getTotalAmount());
    }
}
