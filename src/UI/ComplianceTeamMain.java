package UI;

import Dao.ProductDevelopmentTeamDao;
import Dao.PrototypeDao;
//import Employee.ComplianceFeedback;
import Prototype.Prototype;
import Employee.Employee;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import static UI.Main.*;

public class ComplianceTeamMain {
    private PrototypeDao prototypeDao;
    private ProductDevelopmentTeamDao productDevelopmentTeamDao;

    public ComplianceTeamMain() {
        prototypeDao = new PrototypeDao();
        productDevelopmentTeamDao = new ProductDevelopmentTeamDao();
    }
    public boolean showFunctions(BufferedReader inputReader) throws IOException {
        boolean isRemain = true;
        while (isRemain) {
            ArrayList<Prototype> prototypes = prototypeDao.retrieveAll();
            if (prototypes.size() != 0) {
                System.out.println("<Prototypes in charge>");
                for (int i = 0; i < prototypes.size(); i++) {
                    System.out.println(i + 1 + ". " + prototypes.get(i).getId());
                }
                System.out.println("x. Exit");
                System.out.println("Which prototype do you want to review?");
                System.out.print("Choice: ");
                String userChoiceValue = inputReader.readLine().trim();
                if (userChoiceValue.equals("x")) return false;
                int choiceIndex = Integer.parseInt(userChoiceValue);
                Prototype choicedPrototype = prototypes.get(choiceIndex - 1);
                System.out.println("Prototype id: " + choicedPrototype.getId());
                System.out.println("Prototype developer id: " + choicedPrototype.getDeveloperID());
                System.out.println("Prototype Name: " + choicedPrototype.getName());
                System.out.println("Prototype Category: " + choicedPrototype.getCategory());
                System.out.println("Prototype DetailedCategory: " + choicedPrototype.getDetailedCategory());
                System.out.println("Prototype Requirements: " + choicedPrototype.getRequirements());
                System.out.println("Prototype Description: " + choicedPrototype.getDescription());
                System.out.println("Prototype Status: " + choicedPrototype.getStatus());
                String feedback = choicedPrototype.getFeedbacks();
                if (feedback.equals("null")) feedback = "None";
                System.out.println("Prototype Feedback: " + feedback);
                String risks = choicedPrototype.getRisks();
                if (risks.equals("null")) risks = "None";
                System.out.println("Prototype Risks: " + risks);
                System.out.println("--------------------");
                System.out.println("1. Review Prototype");
                System.out.println("2. Provide Feedback");
                System.out.println("3. Ensure Compliance");
                System.out.println("x. Exit");
                try {
                    System.out.print("Enter your choice: ");
                    String choice = inputReader.readLine();

                    switch (choice) {
                        case "1":
                            reviewPrototype(choicedPrototype, inputReader);
                            break;
                        case "2":
                            provideFeedback(choicedPrototype, inputReader);
                            break;
                        case "3":
                            ensureCompliance(choicedPrototype, inputReader);
                            break;
                        case "x":
                            System.out.println("Going back to the login menu...");
                            return false;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (IOException e) {
                    System.out.println("Error reading input. Please try again.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            } else {
                System.out.println("There is no prototypes in charge");
                return false;
            }
        }
        return true;
    }
    public boolean reviewPrototype(Prototype choicedPrototype, BufferedReader inputReader) throws IOException {
        if (choicedPrototype.getStatus().equals("pending") || choicedPrototype.getStatus().equals("standByFinalize")) {
            System.out.println("Reviewing prototype: " + choicedPrototype.getId());
            // Update the prototype status to confirmed or updated
            System.out.print("Enter 'confirm' to confirm the prototype or 'update' to request changes: ");
            String choice = inputReader.readLine();
            if (choice.equalsIgnoreCase("confirm")) {
                choicedPrototype.setStatus("confirmed");
                System.out.println("Prototype confirmed successfully!");
            } else if (choice.equalsIgnoreCase("update")) {
                choicedPrototype.setStatus("need update");
                System.out.println("Prototype marked for update successfully!");
            } else {
                System.out.println("Invalid choice. Prototype status remains unchanged.");
            }
            // Update the prototype in the database
            prototypeDao.update(choicedPrototype);
        } else {
            System.out.println("Prototype cannot be reviewed. Invalid status.");
        }
        return true;
    }
    public boolean provideFeedback(Prototype choicedPrototype, BufferedReader inputReader) throws IOException {
        if (choicedPrototype.getStatus().equals("need update")) {
            System.out.print("Enter your feedback: ");
            String feedback = inputReader.readLine();
            // Perform actions to provide feedback on the prototype
            choicedPrototype.setFeedbacks(feedback);
            // Update the prototype in the database
            prototypeDao.update(choicedPrototype);
            System.out.println("Feedback provided successfully!");
            // Notify the Product Development Team about the feedback
            Employee productDevelopmentTeam = productDevelopmentTeamDao.retrieveById(choicedPrototype.getDeveloperID());
            showMessageForEmployee(productDevelopmentTeam, choicedPrototype.getId() + "'s Feedback is created\n" +
                    "Sender id: " + currentEmployee.getId() + ", Sender name: " + currentEmployee.getName());
        } else {
            System.out.println("Prototype cannot be provided feedback. Invalid status.");
        }
        return true;
    }
    public boolean ensureCompliance(Prototype choicedPrototype, BufferedReader inputReader) {
        if (!(choicedPrototype.getStatus().equals("standByFinalize"))) {
            System.out.println("First approve the risk report");
            return false;
        }
        try {
            System.out.println("----- Ensure Compliance -----");
            System.out.println("1. Approve");
            System.out.println("2. Abort");
            System.out.print("Enter your choice: ");
            String choice = inputReader.readLine();

            switch (choice) {
                case "1":
                    System.out.print("Set up price for this prototype: ");
                    String priceInput = inputReader.readLine().trim();
                    choicedPrototype.setPrice(Double.parseDouble(priceInput));
                    choicedPrototype.setStatus("Approved");
                    prototypeDao.update(choicedPrototype);
                    System.out.println("Prototype approved successfully!");
                    // Notify the Product Development Team that the prototype is ready to launch
                    Employee productDevelopmentTeam = productDevelopmentTeamDao.retrieveById(choicedPrototype.getDeveloperID());
                    showMessageForEmployee(productDevelopmentTeam, "Prototype " + choicedPrototype.getId() + " is ready to launch");
                    break;
                case "2":
                    choicedPrototype.setStatus("Canceled");
                    System.out.println("Reason for cancellation: ");
                    String cancelReason = inputReader.readLine().trim();
                    choicedPrototype.setCancelReason(cancelReason);
                    System.out.println("Prototype canceled successfully!");
                    // Delete the prototype from the database
                    prototypeDao.deleteById(choicedPrototype.getId());
                    break;
                default:
                    System.out.println("Invalid choice. Prototype status remains unchanged.");
                    return false;
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error reading input. Please try again.");
            return false;
        }
    }
}
