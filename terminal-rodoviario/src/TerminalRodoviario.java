import entity.Passageiro;
import service.Bilhete;
import service.Economica;
import service.Executiva;
import service.PrimeiraClasse;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TerminalRodoviario {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Random random = new Random();

        Passageiro passageiro = new Passageiro("Ricardo", "Avenida dos Cubanos", LocalDate.of(1994, 07, 03));
        Passageiro passageiro1 = new Passageiro("Carlos", "Rua Albion", LocalDate.of(2002, 01, 12));
        Passageiro passageiro2 = new Passageiro("Eduardo", "Rua professor Cristiano Fischer", LocalDate.of(2006, 04, 18));

        List<Bilhete> bilhetes = new ArrayList<>();
        Economica bilhete1 = new Economica(1, 134986234, "42", passageiro);
        Economica bilhete2 = new Economica(2, 127532124, "13", passageiro1);
        PrimeiraClasse bilhete3 = new PrimeiraClasse(3, 126812335, "22");
        PrimeiraClasse bilhete4 = new PrimeiraClasse(4, 912639216, "16");
        Executiva bilhete5 = new Executiva(5, 192839128, "33");
        Executiva bilhete6 = new Executiva(6, 124154123, "02");
        bilhetes.add(bilhete1);
        bilhetes.add(bilhete2);
        bilhetes.add(bilhete3);
        bilhetes.add(bilhete4);
        bilhetes.add(bilhete5);
        bilhetes.add(bilhete6);

        while (true) {
            System.out.println("    1 - Reservar Bilhete    ");
            System.out.println("    2 - Verificar lista de reservas de Bilhete    ");
            System.out.println("    3 - Verificar disponibilidade por Bilhete");
            int opcao = s.nextInt();
            s.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println(" [1] ECONOMICA");
                    System.out.println(" [2] Executiva");
                    System.out.println(" [3] PRIMEIRA CLASSE");

                    int opcaoBilhete = s.nextInt();
                    ;

                    switch (opcaoBilhete) {
                        case 1: {
                            int id = random.nextInt(7, 100);
                            int numero = random.nextInt(100000);
                            String assento = random.ints(1, 44).toString();

                            Passageiro passageiroBilhete = cadastrarPassageiro();

                            Economica bilheteEconomico = new Economica(id, numero, assento, passageiroBilhete);

                            Bilhete.verificarIdBilhete(bilhetes, bilheteEconomico);

                            System.out.println("Reserva efetuada! \n" +
                                    bilheteEconomico);
                            bilhetes.add(bilheteEconomico);
                            break;
                        }
                        case 2: {
                            int id = random.nextInt(7, 100);
                            int numero = random.nextInt(100000);
                            String assento = random.ints(1, 44).toString();

                            Executiva bilheteExecutivo = new Executiva(id, numero, assento);

                            Bilhete.verificarIdBilhete(bilhetes, bilheteExecutivo);

                            System.out.println("Reserva efetuada! \n" +
                                    bilheteExecutivo);
                            bilhetes.add(bilheteExecutivo);
                            break;
                        }
                        case 3: {
                            int id = random.nextInt(7, 100);
                            int numero = random.nextInt(100000);
                            String assento = random.ints(1, 44).toString();

                            PrimeiraClasse bilhetePrimeiraClasse = new PrimeiraClasse(id, numero, assento);

                            Bilhete.verificarIdBilhete(bilhetes, bilhetePrimeiraClasse);

                            System.out.println("Reserva efetuada! \n" +
                                    bilhetePrimeiraClasse);
                            bilhetes.add(bilhetePrimeiraClasse);
                            break;
                        }

                    }

                }

                case 2 -> {
                    System.out.println("Insira o ID do bilhete: ");
                    int id = s.nextInt();
                    s.nextLine();
//                    verificarDisponibilidadeBilhete(bilhetes, id);
                    break;
                }
            }

        }

    }

    public static Passageiro cadastrarPassageiro() {
        Scanner s = new Scanner(System.in);
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            try {
                System.out.println("Insira seu nome:");
                String nome = s.nextLine();
                System.out.println("Insira seu endereco:");
                String endereco = s.nextLine();
                System.out.println("Insira sua data de nascimento: \n EXEMPLO(02/04/2004)");
                String dataNasc = s.nextLine();
                LocalDate dataNascimento = LocalDate.parse(dataNasc, formatador);
                Passageiro passageiro = new Passageiro(nome, endereco, dataNascimento);
                return passageiro;
            } catch (DateTimeException ex) {
                System.out.println("Erro na composição de data de nascimento");
            }
        }
    }

//    public static boolean verificarDisponibilidadeBilhete(List<Bilhete> bilhetes, Integer id) {
//        AtomicBoolean disponivel = new AtomicBoolean(true);
//        AtomicBoolean indisponivel = new AtomicBoolean(false);
//      bilhetes.stream().forEach(bilhete -> {
//            if (bilhete.id == id) {
//                indisponivel.set(false);
//            } else {
//                disponivel.set(true);
//            }
//        });
//
//    }


}