
    import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Scanner;

    public class BankApplogging {
        private static final Logger logger = Logger.getLogger(BankApplogging.class.getName());
        private static double accountBalance = 1000.0;

        public static void main(String[] args) {
            try {
                // إعداد ملف Logging
                FileHandler fileHandler = new FileHandler("bankApp.log", true);
                fileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(fileHandler);

                Scanner scanner = new Scanner(System.in);

                logger.info("App Started");

                // Log in
                System.out.print("Enter UserName: ");
                String username = scanner.nextLine();
                System.out.print("Enter Password ");
                String password = scanner.nextLine();

                if (authenticate(username, password)) {
                    logger.info("User login successful: " + username);
                    boolean exit = false;

                    while (!exit) {
                        System.out.println("choice operation /n");
                        System.out.println("1.view balance");
                        System.out.println("2.cash withdrawal");
                        System.out.println("3.exit");
                        System.out.print("you're choice ");
                        int choice = scanner.nextInt();

                        switch (choice) {
                            case 1:
                                showBalance();
                                break;
                            case 2:
                                System.out.print("Enter the amount to be withdrawn: ");
                                double amount = scanner.nextDouble();
                                withdraw(amount);
                                break;
                            case 3:
                                exit = true;
                                logger.info("User " + username + "Exit from the app");
                                break;
                            default:
                                System.out.println("Incorrect choice.");
                                logger.warning("User selected incorrect operation");
                        }
                    }
                } else {
                    logger.warning("User login failed: " + username);
                    System.out.println("Username or password is incorrect.");
                }
            } catch (IOException e) {
                logger.severe("Error while preparing the logging file: " + e.getMessage());
            }
        }

        private static boolean authenticate(String username, String password) {
            //
            return "admin".equals(username) && "password".equals(password);
        }

        private static void showBalance() {
            System.out.println("Current account balance: " + accountBalance);
            logger.info("Balance displayed: " + accountBalance);
        }

        private static void withdraw(double amount) {
            if (amount <= accountBalance) {
                accountBalance -= amount;
                System.out.println("It was withdrawn." + amount + "Successfully. Remaining balance: " + accountBalance);
                logger.info("It was withdrawn. " + amount + "Successfully. Remaining balance: " + accountBalance);
            } else {
                System.out.println("There is not enough balance.");
                logger.warning("Withdrawal attempt failed. Amount requested: " + amount + " Current balance: " + accountBalance);
            }
        }
        ///// test
    }


