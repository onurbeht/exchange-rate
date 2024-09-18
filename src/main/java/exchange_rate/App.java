package exchange_rate;

import java.io.IOException;

import java.util.Scanner;
import java.util.InputMismatchException;

import exchange_rate.domain.ResponseDto;
import exchange_rate.services.ExchangeRateService;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExchangeRateService erService = new ExchangeRateService();

        Scanner sc = new Scanner(System.in);

        String from = "";
        String to = "";
        float amount = 0;

        int option;
        System.out.println();
        while (true) {

            System.out.println("========== Escolha uma da opções abaixo para conversão de moeda: ");
            System.out.println("""
                        [0] - BRL(Brasil) para USD(USA).
                        [1] - BRL(Brasil) para EUR(EURO).
                        [2] - USD(USA) para BRL(Brasil).
                        [3] - USD(USA) para EUR(EURO).
                        [4] - EUR(EURO) para USD(USA).
                        [5] - EUR(EURO) para BRL(Brasil).
                        [6] - SAIR.
                    """);

            System.out.print("OPÇÃO:");
            try {
                option = sc.nextInt();

                if (option >= 7 | option < 0) {
                    System.out.println("========== OPÇÃO INVALIDA!!! - TENTE NOVAMENTE ==========");
                    continue;
                }

                if (option == 6)
                    break;

                switch (option) {
                    case 0:
                        from = "BRL";
                        to = "USD";
                        break;
                    case 1:
                        from = "BRL";
                        to = "EUR";
                        break;
                    case 2:
                        from = "USD";
                        to = "BRL";
                        break;
                    case 3:
                        from = "USD";
                        to = "EUR";
                        break;
                    case 4:
                        from = "EUR";
                        to = "USD";
                        break;
                    case 5:
                        from = "EUR";
                        to = "BRL";
                        break;

                    default:
                        break;
                }

                System.out.println("Convertendo de " + from + " para " + to);
                System.out.print("Qual valor deseja fazer a conversão: ");
                amount = sc.nextFloat();

                if (amount < 0) {
                    System.out.println("========== OPÇÃO INVALIDA!!! - TENTE NOVAMENTE ==========");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#");
                System.out.println("Valor inserido no formato invalido! Verifique e tente novamente.");
                System.out.println("=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#");
                sc.nextLine();
                continue;
            }

            ResponseDto response = erService.send(from, to, amount);

            System.out.println(
                    "Convertido: " + amount + from + " para " + to + " = " + response.conversion_result() + to);
            System.out.println("============================#============================");
        }

        sc.close();

        System.out.println("========== PROGRAMA FINALIZADO ==========");

    }
}
