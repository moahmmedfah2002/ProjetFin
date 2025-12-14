package com.university.finance;

import com.university.finance.controller.AccountController;
import com.university.finance.controller.TransactionController;
import com.university.finance.controller.UserController;
import com.university.finance.pattern.observer.AuditLogger;
import com.university.finance.pattern.observer.NotificationService;
import com.university.finance.repository.AccountRepository;
import com.university.finance.repository.TransactionRepository;
import com.university.finance.repository.UserRepository;
import com.university.finance.service.AccountService;
import com.university.finance.service.BankingService;
import com.university.finance.service.TransactionService;
import com.university.finance.service.UserService;

import java.util.Scanner;

public class MainApp {

    // 1. Initialisation des Repositories
    private static final AccountRepository accountRepository = new AccountRepository();
    private static final UserRepository userRepository = new UserRepository();
    private static final TransactionRepository transactionRepository = new TransactionRepository();

    // 2. Initialisation des Services
    private static final AccountService accountService = new AccountService(accountRepository, transactionRepository);
    private static final TransactionService transactionService = new TransactionService(transactionRepository);
    private static final UserService userService = new UserService(userRepository, accountRepository);
    private static final BankingService bankingService = new BankingService(accountService, accountRepository, transactionRepository);

    // 3. Initialisation des Controllers
    private static final AccountController accountController = new AccountController(accountService);
    private static final UserController userController = new UserController(userService);
    private static final TransactionController transactionController = new TransactionController(bankingService, transactionService);

    public static void main(String[] args) {

        bankingService.attach(new AuditLogger());
        bankingService.attach(new NotificationService());

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();

            try {
                if (!scanner.hasNextInt()) {
                    System.err.println(" Entrée invalide. Veuillez saisir un numéro de menu.");
                    scanner.next();
                    continue;
                }

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Nom utilisateur: ");
                        String userBalance = scanner.next();
                        accountController.displayBalance(userBalance);
                        break;

                    case 2:
                        System.out.print("Utilisateur: ");
                        String depositUser = scanner.next();
                        System.out.print("Montant: ");
                        double amount = scanner.nextDouble();
                        transactionController.handleDeposit(depositUser, amount);
                        break;

                    case 3:
                        System.out.print("Utilisateur: ");
                        String withdrawUser = scanner.next();
                        System.out.print("Montant: ");
                        double withdrawAmount = scanner.nextDouble();
                        transactionController.handleWithdrawal(withdrawUser, withdrawAmount);
                        break;

                    case 4:
                        System.out.print("De l'utilisateur: ");
                        String fromUser = scanner.next();
                        System.out.print("Vers utilisateur: ");
                        String toUser = scanner.next();
                        System.out.print("Montant: ");
                        double transferAmount = scanner.nextDouble();
                        transactionController.handleTransfer(fromUser, toUser, transferAmount);
                        break;

                    case 5:
                        System.out.print("Utilisateur: ");
                        String historyUser = scanner.next();
                        transactionController.displayHistory(historyUser);
                        break;

                    case 6:
                        System.out.print("Nouvel utilisateur: ");
                        String newUser = scanner.next();
                        System.out.print("Mot de passe: ");
                        String password = scanner.next();
                        System.out.print("Dépôt initial: ");
                        double initialDeposit = scanner.nextDouble();
                        userController.registerNewUser(newUser, password, initialDeposit);
                        break;

                    case 0:
                        running = false;
                        break;

                    default:
                        System.out.println("Choix invalide.");
                }
            } catch (java.util.InputMismatchException e) {
                System.err.println("Erreur de format: Veuillez entrer un nombre valide pour le montant ou le choix du menu.");
                scanner.nextLine();
            } catch (Exception e) {
                System.err.println(" Erreur d'opération: " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Fermeture du système bancaire refactorisé. Au revoir!");
    }

    private static void displayMenu() {
        System.out.println("\n===================================");
        System.out.println("=== Système Bancaire Refactorisé ===");
        System.out.println("===================================");
        System.out.println("1. Afficher solde");
        System.out.println("2. Déposer argent");
        System.out.println("3. Retirer argent");
        System.out.println("4. Transfert");
        System.out.println("5. Historique des transactions");
        System.out.println("6. Ajouter utilisateur");
        System.out.println("0. Quitter");
        System.out.print("Votre choix: ");
    }
}