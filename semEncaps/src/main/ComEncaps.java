package main;

 public class ContaBancaria {
 private String titular;
 private double saldo;

 public ContaBancaria(String titular, double saldoInicial) {
     this.titular = titular;
     if (saldoInicial >= 0) {
         this.saldo = saldoInicial;
     }
 }
 public double getSaldo() {
     return saldo;
 }
 public void depositar(double valor) {
     if (valor > 0) {
         this.saldo += valor;
     }
 }
 public String getTitular() {
     return titular;
 }
}


public class ComEncaps {
 public static void main(String[] args) {
     ContaBancaria conta = new ContaBancaria("Maria", 100.0);
     conta.depositar(50.0);

     System.out.println("Titular: " + conta.getTitular());
     System.out.println("Saldo Atual: " + conta.getSaldo());
 }
}
