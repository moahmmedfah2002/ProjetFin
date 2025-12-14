# Application de Finance Refactorisée

Ceci est une version refactorisée de l'application Spaghetti Finance avec une architecture plus propre suivant les patrons de conception.

## Structure du Projet

```
refactored-finance-app/
├── src/main/java/com/university/finance/
│   ├── pattern/strategy/
│   │   ├── TransactionStrategy.java
│   │   ├── DepositStrategy.java
│   │   ├── WithdrawStrategy.java
│   │   └── TransferStrategy.java
│   ├── pattern/factory/
│   │   ├── AccountFactory.java
│   │   └── UserFactory.java
│   ├── pattern/observer/
│   │   ├── TransactionObserver.java
│   │   ├── AuditLogger.java
│   │   └── NotificationService.java
│   ├── model/
│   │   ├── Account.java
│   │   ├── User.java
│   │   └── Transaction.java
│   ├── service/
│   │   ├── BankingService.java
│   │   └── TransactionService.java
│   └── MainApp.java
├── src/test/java/
│   └── (tests unitaires et d'intégration)
├── pom.xml
├── Jenkinsfile
├── sonar-project.properties
└── README.md
```

## Patrons de Conception Utilisés

1. **Patron Stratégie**: Pour les différents types de transactions (dépôt, retrait, transfert)
2. **Patron Fabrique**: Pour la création de comptes et d'utilisateurs
3. **Patron Observateur**: Pour la surveillance des transactions et les notifications

## Construction et Exécution

Ce projet utilise Maven pour la gestion des dépendances et l'automatisation de la construction.