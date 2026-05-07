package main;

 class ContaBancaria {
 public String titular;
 public double saldo;
}
public class SemEncaps {
 public static void main(String[] args) {
     ContaBancaria conta = new ContaBancaria();
     conta.titular = "João";
     conta.saldo = 100.0;
     conta.saldo = -5000.0; 

     System.out.println("Titular: " + conta.titular);
     System.out.println("Saldo: " + conta.saldo);
 }
}
