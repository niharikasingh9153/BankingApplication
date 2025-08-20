import com.hdfcLife.Exceptions.InsufficientBalanceException;
import com.hdfcLife.entity.*;
import com.hdfcLife.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Main {
    private static final Map<String, Customer> customer = new HashMap<>();
    private static final Map<String, Account> account = new HashMap<>();
    private static final List<Transaction> transaction = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    private static final DateTimeFormatter dateFromatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static void main(String[] args) {
        System.out.println("Welcome to HDFC Banking application");
        try{
            while (true){
                showMainMenu();
            }
        }catch (Exception e){
            System.out.println("Error : "+e.getMessage());;
        }finally {
            scanner.close();
        }
    }
    private static void showMainMenu(){
        System.out.println("\nMain Menu");
        System.out.println("1. Register new Customer");
        System.out.println("2. Create Account");
        System.out.println("3. Perform Transaction");
        System.out.println("4. View Account Details");
        System.out.println("5. View Transaction History");
        System.out.println("6. Exit");

        System.out.println("Enter your choice");
        int choice = getInput();

        switch (choice){
            case 1:
                registerCustomer();
                break;

            case 2:
//                createAccount();
        }


    }

    private static int getInput(){
        while (true){
            try{
                return Integer.parseInt(scanner.nextLine().trim());
            }catch (NumberFormatException e){
                System.out.println("Please enter a valid number");
            }
        }
    }

    public static void registerCustomer(){
        System.out.println("\n==== Customer Registration====");
        System.out.println("Enter the Customer Id");
        String customerId = scanner.nextLine().trim();

        if (customer.containsKey(customerId)){
            System.out.println("Customer Already Exists");
            return;
        }

        System.out.println("Enter name ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter email");
        String email = scanner.nextLine().trim();

        System.out.println("Enter mobile number");
        String phone = scanner.nextLine().trim();

        System.out.println("Enter DOB in format in yyyy-mm-dd");
        String dobStr = scanner.nextLine().trim();

        LocalDate dateOfBirth;
        try{
            dateOfBirth = LocalDate.parse(dobStr, dateFromatter);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Enter password");
        String password = scanner.nextLine().trim();

        Customer customers = new Customer(customerId,name,phone,email,password,dateOfBirth);

customer.put(customerId,customers);
        System.out.println(Arrays.asList(customer));

    }

    public static void createAccount(){
        System.out.println("/n====Creating new Account=====");
        System.out.println("Please enter customerId");
        String customerId =scanner.nextLine().trim();
        Customer customer1 = customer.get(customerId);

        if(customer1==null){
            System.out.println("Customer not found");
            return;
        }
        System.out.println("Choose Account Type");
        System.out.println("1. SAVINGS ACCOUNT (6% INTREST AND MINIMUM BALANCE OF 1000)");
        System.out.println("2. CURRENT ACCOUNT (4% INTREST AND MINIMUM BALANCE OF ");
        System.out.println("3. Transaction");

        int typeChoince = getInput();
        Account account1=null;
        System.out.println("Enter initial balance");
        String balance = scanner.nextLine().trim();

        try
        {
            BigDecimal initialbalance = new BigDecimal(balance);
            String accountNo = Main.generateAccountNo();
            switch (typeChoince){
                case 1: account1 = new SavingsAccount();
                break;
                case 2: account1=new CurrentAccount();
                break;
                case 3: performTransaction();
            }
        }catch (NumberFormatException e){
            System.out.println("Please enter a valid account no.");
        }
    }

    public static String generateAccountNo(){
        return String.format("%10",System.currentTimeMillis() %100000);
    }
    public static void performTransaction(){
        System.out.println("==Perform transaction==");
        System.out.println("1. DEPOSIT");
        System.out.println("2. WITHDRAW");

        System.out.println("Please select a transaction type");
        int transactionsChoice=getInput();

        switch (transactionsChoice){
            case 1: performDeposit();
            break;
            case 2: performWithdraw();
            break;
            case 3: performTransfer();
            break;
            case 4: viewAccountDetails();
            break;
            case 5: viewTransactionHistory();
            default:
                System.out.println("Invalid transaction type");
        }
    }

    private static void performDeposit(){
        System.out.println("Enter account number");
        String accountNo = scanner.nextLine().trim();

        Account accounts = account.get(accountNo);
        if (accounts==null){
            System.out.println("Account not found");
            return;
        }
        System.out.println("Enter the deposit amount");
        String amountstr = scanner.nextLine().trim();
        try {
            BigDecimal amount = new BigDecimal(amountstr);
            accounts.deposit(amount);
            String transactionId = generateTransactionId();
            Transaction transaction1 = new Transaction(transactionId,amount,accountNo,LocalDate.now(),TransactionType.DEPOSIT);
            transaction.add(transaction1);

            System.out.println("Transaction was succesfull!!!"+accounts.getBalance());
        }catch (NumberFormatException e){
            System.out.println("Invalid amount");
        }
    }

    private static String  generateTransactionId(){
        return "HDFC_TXN" + System.currentTimeMillis();

    }

    private static void performWithdraw(){
        System.out.println("Enter account no");
        String accountNo = scanner.nextLine().trim();

        Account accounts = account.get(accountNo);

        if(account==null){
            System.out.println("Account is not registered");
            return;
        }
        System.out.println("Enter withdrawal amount");
        String amountStr = scanner.nextLine().trim();

        try{
            BigDecimal amount = new BigDecimal(amountStr);
            accounts.withdraw(amount);
            String transactionId =generateTransactionId();
            Transaction transactions=new Transaction(transactionId,amount,accountNo,LocalDate.now(),TransactionType.WITHDRAW);
            transaction.add(transactions);
            System.out.println("Withdarwal succesful!!!!Balanec: "+accounts.getBalance());
        }catch (NumberFormatException e){
            System.out.println("Invalid amount");
        }catch (InsufficientBalanceException e){
            System.out.println("Error"+e);
        }
    }
    private static void performTransfer(){
        System.out.println("Enter source account:");
        String fromAccountNo = scanner.nextLine().trim();

        Account fromAccount = account.get(fromAccountNo);
        if(fromAccount==null){
            System.out.println("Source account not found");
            return;
        }
        System.out.println("Enter destonation account");
        String  toAccountNo = scanner.nextLine().trim();

        Account toAccount = account.get(toAccountNo);
        if(toAccount==null){
            System.out.println("Destination account no not found!");
            return;
        }
        if(fromAccountNo.equals(toAccountNo)){
            System.out.println("Can't perform transaction");
        }

        System.out.println("ENter amount");
        String amountStr = scanner.nextLine().trim();

        try {
            BigDecimal amount = new BigDecimal(amountStr);

            fromAccount.withdraw(amount);
            toAccount.deposit(amount);

            String  transactionId = generateTransactionId();
            Transaction transaction1=new Transaction(transactionId,fromAccountNo,toAccountNo,amount,LocalDate.now(),TransactionType.TRANSFER);
        }catch (NumberFormatException e){
            System.out.println("Invalid amount Entered");
        }catch (InsufficientBalanceException e){
            System.out.println("Error:"+e.getMessage());
        }



    }
    private static void viewAccountDetails(){
        System.out.println("===View Account Details==");
        System.out.println("Enter account number");
        String accountNo = scanner.nextLine().trim();

        Account accounts = account.get(accountNo);
        if(accounts==null){
            System.out.println("Account not found");
            return;
        }
        System.out.println(accounts.toString());
        if(accounts instanceof SavingsAccount){
            System.out.println("Account Type : SAVINGS ACCOUNT");
        }else {
            System.out.println("Account Type : CURRENT ACCOUNT");
        }

    }

    private static void viewTransactionHistory(){
        System.out.println("===Transaction History===");

        System.out.println("For which account you want to see history");
        String accountNo = scanner.nextLine().trim();
        Account account1 = account.get(accountNo);
        if(account1==null){
            System.out.println("Account not found!!");
            return;
        }
        List<Transaction> accountTransactions = transaction.stream()
                .filter(t->t.getAccountNo().equals(accountNo))
                .sorted(Comparator.comparing(Transaction::getTimestamp).reversed())
                .toList();

        if (accountTransactions.isEmpty()){
            System.out.println("No transactions found");
            return;
        }

        for (Transaction transaction1:accountTransactions){
            System.out.println(transaction1.toString());
        }

        Map<TransactionType, Long> transactionTypeMap =accountTransactions.stream()
                .collect(Collectors.groupingBy(Transaction::getType,Collectors.counting()));

        System.out.println("\n == Transacion Summary===");
        transactionTypeMap.forEach((type,count)->
                System.out.println(type.getDisplayName()));

    }
}
