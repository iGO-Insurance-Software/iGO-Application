package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import Insurance.Insurance;
import Dao.ComplianceTeamDao;
import Dao.PrototypeDao;
import Employee.Employee;
import Prototype.Prototype;
import static UI.Main.*;

public class ProductTeamMain {
    private PrototypeDao prototypeDao = new PrototypeDao();
    private ComplianceTeamDao complianceTeamDao = new ComplianceTeamDao();
    private String productName;
    private String productID;
    private String description;
    private String requirements;
    private String category;
    private String detailedCategory;
    private boolean isProductDefined;
    private boolean isRiskReportCreated;
    private Map<Integer, Prototype> prototypes = new HashMap<>();
    ComplianceTeamMain complianceTeamMain = new ComplianceTeamMain();

    public ProductTeamMain() {
    }
    public boolean showFunctions(BufferedReader inputReader) throws IOException {
        boolean isRemain = true;
        String userChoiceValue;
        while (isRemain) {
            System.out.println("0. Show all prototypes");
            System.out.println("1. Define New Prototype");
            System.out.println("2. Confirm Prototype");
            System.out.println("3. Update Prototype");
            System.out.println("4. Create Risk Report");
            System.out.println("5. Launch Product");
            System.out.println("x. Go Back to Previous Menu");
            userChoiceValue = inputReader.readLine().trim();
            switch (userChoiceValue) {
                case "0":
                    showPrototypes(inputReader);
                    break;
                case "1":
                    defineNewPrototype(inputReader);
                    break;
                case "2":
                    createPrototype(inputReader);
                    break;
                case "3":
                    updatePrototype(inputReader);
                    break;
                case "4":
                    createRiskReport(inputReader);
                    break;
                case "5":
                    launchPrototype(inputReader);
                    break;
                case "x":
                    System.out.println("Going back to the login menu...");
                    return false;
                default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
            }
        return true;
    }
    public boolean showPrototypes(BufferedReader inputReader) throws IOException {
        ArrayList<Prototype> myPrototypes = new ArrayList<Prototype>();
        for (Prototype prototype : prototypeDao.retrieveAll()) {
            if (prototype.getDeveloperID().equals(currentEmployee.getId())) {
                myPrototypes.add(prototype);
            }
        }
        if (myPrototypes.size() != 0) {
            System.out.println("<Prototypes in charge>");
            for (int i = 0; i < myPrototypes.size(); i++) {
                System.out.println(i + 1 + ". " + myPrototypes.get(i).getId());
            }
            System.out.println("Which prototype do you want to review?");
            System.out.print("Choice: ");
            String userChoiceValue = inputReader.readLine().trim();
            int choice = Integer.parseInt(userChoiceValue);
            Prototype choicedPrototype = myPrototypes.get(choice - 1);
            System.out.println("Prototype id: "+choicedPrototype.getId());
            System.out.println("Prototype Name: "+choicedPrototype.getName());
            System.out.println("Prototype Category: "+choicedPrototype.getCategory());
            System.out.println("Prototype DetailedCategory: "+choicedPrototype.getDetailedCategory());
            System.out.println("Prototype Requirements: "+choicedPrototype.getRequirements());
            System.out.println("Prototype Description: "+choicedPrototype.getDescription());
            System.out.println("Prototype Status: "+choicedPrototype.getStatus());
            String feedback = choicedPrototype.getFeedbacks();
            if(feedback.equals("null")) feedback = "None";
            System.out.println("Prototype Feedback: "+feedback);
            String risks = choicedPrototype.getRisks();
            if(risks.equals("null")) risks = "None";
            System.out.println("Prototype Risks: "+ risks);
            System.out.println("------------------------");
            System.out.println("x. Go back to previous menu");
            System.out.print("Choice: ");
            String userChoice = inputReader.readLine().trim();
            if(userChoice.equals("x")) return true;
        }
        else {
            System.out.println("There is no prototypes in charge");
        }
        return false;
    }
    public boolean defineNewPrototype(BufferedReader inputReader) throws IOException {
        if (isProductDefined) {
            System.out.println("Product is already defined.");
        } else {
            // Collect necessary information from the user
            System.out.println("Enter product name:");
            productName = inputReader.readLine().trim();
            System.out.println("Enter product ID:");
            productID = inputReader.readLine().trim();
            System.out.println("Enter description:");
            description = inputReader.readLine().trim();
            System.out.println("Enter requirements:");
            requirements = inputReader.readLine().trim();
            System.out.println("Select category:");
            System.out.println("1. Life Insurance");
            System.out.println("2. Damage Insurance");
            System.out.println("3. Saving Insurance");
            int categoryChoice = Integer.parseInt(inputReader.readLine().trim());
            detailedCategory = "";
            switch (categoryChoice) {
                case 1:
                    category = "Life Insurance";
                    System.out.println("Select detailed category:");
                    System.out.println("1. Cancer Insurance");
                    System.out.println("2. Death Insurance");
                    System.out.println("3. Other");
                    int lifeInsuranceChoice = Integer.parseInt(inputReader.readLine().trim());
                    switch (lifeInsuranceChoice) {
                        case 1:
                            detailedCategory = "Cancer Insurance";
                            break;
                        case 2:
                            detailedCategory = "Death Insurance";
                            break;
                        case 3:
                            System.out.println("Enter other detailed category:");
                            detailedCategory = inputReader.readLine().trim();
                            break;
                        default:
                            System.out.println("Invalid choice. Prototype creation cancelled.");
                            break;
                    }
                    break;
                case 2:
                    category = "Damage Insurance";
                    System.out.println("Select detailed category:");
                    System.out.println("1. Car Insurance");
                    System.out.println("2. Fire Insurance");
                    System.out.println("3. Marine Insurance");
                    System.out.println("4. Travel Insurance");
                    System.out.println("5. Other");
                    int damageInsuranceChoice = Integer.parseInt(inputReader.readLine().trim());

                    switch (damageInsuranceChoice) {
                        case 1:
                            detailedCategory = "Car Insurance";
                            break;
                        case 2:
                            detailedCategory = "Fire Insurance";
                            break;
                        case 3:
                            detailedCategory = "Marine Insurance";
                            break;
                        case 4:
                            detailedCategory = "Travel Insurance";
                            break;
                        case 5:
                            System.out.println("Enter other detailed category:");
                            detailedCategory = inputReader.readLine().trim();
                            break;
                        default:
                            System.out.println("Invalid choice. Prototype creation cancelled.");
                            break;
                    }
                    break;
                case 3:
                    category = "Saving Insurance";
                    System.out.println("Select detailed category:");
                    System.out.println("1. Pension Insurance");
                    System.out.println("2. Saving Insurance");
                    System.out.println("3. Other");
                    int savingInsuranceChoice = Integer.parseInt(inputReader.readLine().trim());
                    switch (savingInsuranceChoice) {
                        case 1:
                            detailedCategory = "Pension Insurance";
                            break;
                        case 2:
                            detailedCategory = "Saving Insurance";
                            break;
                        case 3:
                            System.out.println("Enter other detailed category:");
                            detailedCategory = inputReader.readLine().trim();
                            break;
                        default:
                            System.out.println("Invalid choice. Prototype creation cancelled.");
                            break;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Prototype creation cancelled.");
                    return false;
                   }
                 if (productName.isEmpty() || productID.isEmpty() || description.isEmpty() || requirements.isEmpty() || detailedCategory.isEmpty()) {
                System.out.println("Please complete all required fields. Prototype creation couldn't be completed. .");
                return false;
            }
                 isProductDefined = true;
            System.out.println("Product definition is successful!");
        }
        createPrototype(inputReader);
        return false;
    }
    public boolean createPrototype(BufferedReader inputReader) throws IOException {
        if (!isProductDefined) {
            System.out.println("Product is not defined. Cannot create prototype.");
            return false; // Return false indicating that the prototype creation failed
        }
        // Confirmation to create a new prototype
        System.out.println("Would you like to create the prototype? (yes/no)");
        String createPrototypeChoice = inputReader.readLine().trim().toLowerCase();
        if (createPrototypeChoice.equals("yes")) {
            // Perform actions to create the prototype
            Prototype newPrototype = new Prototype();
            newPrototype.setRequirements(requirements);
            newPrototype.setName(productName);
            newPrototype.setId(productID);
            newPrototype.setCategory(category);
            newPrototype.setDescription(description);
            newPrototype.setDetailedCategory(detailedCategory);
            newPrototype.setDeveloperID(currentEmployee.getId());
            newPrototype.setStatus("pending"); // Set the status to "pending"
            prototypeDao.create(newPrototype);
            // Print confirmation message
            System.out.println("New prototype created!");
            //send message to All compliance employees
            for(Employee compilanceEmployee : complianceTeamDao.retrieveAll()){
                showMessageForEmployee(compilanceEmployee, newPrototype.getId() + " is created");
            }
            return true; // Return true indicating that the prototype creation was successful
        } else {
            System.out.println("Prototype creation cancelled.");
            return false; // Return false indicating that the prototype creation was cancelled
        }
    }
    public boolean updatePrototype(BufferedReader inputReader) throws IOException {
     // Display the menu of defined prototypes
        ArrayList<Prototype> myPrototypes = new ArrayList<Prototype>();
        for (Prototype prototype : prototypeDao.retrieveAll()) {
            if (prototype.getDeveloperID().equals(currentEmployee.getId())) {
                myPrototypes.add(prototype);
            }
        }
        if (myPrototypes.size() != 0) {
            System.out.println("Select the prototype you want to update:");
            System.out.println("<Prototypes in charge>");
            for (int i = 0; i < myPrototypes.size(); i++) {
                System.out.println(i+1 + ". " + myPrototypes.get(i).getId());
            }
            System.out.println("Which prototype do you want to update?");
            System.out.print("Choice: ");
            String userChoiceValue = inputReader.readLine().trim();
            int choice = Integer.parseInt(userChoiceValue);
            Prototype choicedPrototype = myPrototypes.get(choice - 1);
            System.out.println("Current Prototype Name: " + choicedPrototype.getName());
            System.out.println("Current Description: " + choicedPrototype.getDescription());
            System.out.println("Current Requirements: " + choicedPrototype.getRequirements());
            System.out.println("Current Category: " + choicedPrototype.getCategory());
            System.out.println("Current Risks: " + choicedPrototype.getRisks());
            System.out.println();
            // Prompt the user to choose an update option
            boolean isRemain = true;
            while(isRemain) {
                System.out.println("Select the information you want to update:");
                System.out.println("1. Update Prototype Name");
                System.out.println("2. Update Description");
                System.out.println("3. Update Requirements");
                System.out.println("4. Update Category");
                System.out.println("5. Update Risks");
                System.out.println("x. Back to Menu");
                String updateOption = inputReader.readLine().trim();
                 // Perform actions based on the chosen update option
                switch (updateOption) {
                    case "1":
                        // Update Prototype Name
                        System.out.println("Enter the new name for the prototype:");
                        String newName = inputReader.readLine().trim();
                        choicedPrototype.setName(newName);
                        break;
                    case "2":
                        // Update Description
                        System.out.println("Enter the new description for the prototype:");
                        String newDescription = inputReader.readLine().trim();
                        choicedPrototype.setDescription(newDescription);
                        break;
                    case "3":
                        // Update Requirements
                        System.out.println("Enter the new requirements for the prototype:");
                        String newRequirements = inputReader.readLine().trim();
                        choicedPrototype.setRequirements(newRequirements);
                        break;
                    case "4":
                        // Update Category
                        System.out.println("Enter the new category for the prototype:");
                        String newCategory = inputReader.readLine().trim();
                        choicedPrototype.setCategory(newCategory);
                        break;
                    case "5":
                        // Update Description
                        System.out.println("Enter the new possible Risk for the prototype:");
                        String newRisks = inputReader.readLine().trim();
                        choicedPrototype.setRisks(newRisks);
                        break;
                    case "x":
                        return false;
                    default:
                        System.out.println("Invalid choice. Prototype update cancelled.");
                        return false;
                }
                choicedPrototype.setStatus("pending");
                prototypeDao.update(choicedPrototype);
            }
        } else {
            System.out.println("There is no prototypes in charge");
        }
        return true;
    }
    private boolean createRiskReport(BufferedReader inputReader) throws IOException {
        ArrayList<Prototype> myPrototypes = new ArrayList<Prototype>();
        for (Prototype prototype : prototypeDao.retrieveAll()) {
            if (prototype.getDeveloperID().equals(currentEmployee.getId())) {
                myPrototypes.add(prototype);
            }
        }
        if (myPrototypes.size() != 0) {
            System.out.println("Select the prototype you want to create risk report:");
            System.out.println("<Prototypes in charge>");
            for (int i = 0; i < myPrototypes.size(); i++) {
                System.out.println(i + 1 + ". " + myPrototypes.get(i).getId());
            }
            System.out.println("Which prototype do you want to create risk report");
            System.out.print("Choice: ");
            String userChoiceValue = inputReader.readLine().trim();
            int choice = Integer.parseInt(userChoiceValue);
            Prototype choicedPrototype = myPrototypes.get(choice - 1);
            if(!(choicedPrototype.getStatus().equals("confirmed"))){
                System.out.println("First get a confirmation from Compliance Team");
                return false;
            }
            // Perform actions to create the risk report
            System.out.println("Enter risk level (Low/Medium/High):");
            String riskLevel = inputReader.readLine().trim();
            System.out.println("Enter report details:");
            String reportDetails = inputReader.readLine().trim();
            String risk = "[" + riskLevel + "] " + reportDetails;
            choicedPrototype.setStatus("standByFinalize");
            choicedPrototype.setRisks(risk);
            prototypeDao.update(choicedPrototype);
            //send message to All compliance employees
            for(Employee compilanceEmployee : complianceTeamDao.retrieveAll()){
                showMessageForEmployee(compilanceEmployee, compilanceEmployee.getId() + "'s new Risk report is created");
            }
            isRiskReportCreated = true;
            System.out.println("Risk report created successfully.");
        }else {
            System.out.println("There is no prototypes in charge");
        }
        return true;
    }
    private boolean launchPrototype(BufferedReader inputReader) throws IOException {
        ArrayList<Prototype> myPrototypes = new ArrayList<Prototype>();
        for (Prototype prototype : prototypeDao.retrieveAll()) {
            if (prototype.getDeveloperID().equals(currentEmployee.getId())) {
                myPrototypes.add(prototype);
            }
        }
        if (myPrototypes.size() != 0) {
            System.out.println("Select the prototype you want to launch:");
            System.out.println("<Prototypes in charge>");
            for (int i = 0; i < myPrototypes.size(); i++) {
                System.out.println(i + 1 + ". " + myPrototypes.get(i).getId());
            }
            System.out.println("Which prototype do you want to launch?");
            System.out.print("Choice: ");
            String userChoiceValue = inputReader.readLine().trim();
            int choice = Integer.parseInt(userChoiceValue);
            Prototype choicedPrototype = myPrototypes.get(choice - 1);
            if (!(choicedPrototype.getStatus().contains("Approved"))) {
                System.out.println("Only release a product with the approval of the compliance team. Please get permission and try again.");
                return false;
            }
            System.out.println("Do you confirm?");
            String launchPrototypeChoice = inputReader.readLine().trim().toLowerCase();
            if (launchPrototypeChoice.equals("yes")) {
                // Perform actions to create the prototype
                Insurance newInsurance = new Insurance();
                newInsurance.setId(choicedPrototype.getId());
                System.out.println("Insurance's ID: " + newInsurance.getId());
                newInsurance.setName(choicedPrototype.getName());
                System.out.println("Insurance's name: " + newInsurance.getName());
                newInsurance.setType(choicedPrototype.getCategory());
                System.out.println("Insurance's category: " + newInsurance.getType());
                newInsurance.setDetailedCategory(choicedPrototype.getDetailedCategory());
                System.out.println("Insurance's detailedCategory: " + newInsurance.getDetailedCategory());
                newInsurance.setDescription(choicedPrototype.getDescription());
                System.out.println("Insurance's description: " + newInsurance.getDescription());
                newInsurance.setPrice(choicedPrototype.getPrice());
                System.out.println("Insurance's price: " + newInsurance.getPrice());
              //  newInsurance.setPaymentTerm(choicedPrototype.getPaymentTerm());
             //   System.out.println("Insurance's Payment Term: " + newInsurance.getPaymentTerm());
                String text = choicedPrototype.getStatus();
                int startIndex = text.indexOf(":") + 2; // Add 2 to skip the ": "
                int endIndex = text.length() - 1; // Subtract 1 to exclude the closing parenthesis
                //Create insuranceDao
                //     InsuranceDao insuranceDao = new InsuranceDao();
                //     insuranceDao.create(newInsurance);
                System.out.println("New Insurance created!");
                //send message to All compliance employees
                for (Employee compilanceEmployee : complianceTeamDao.retrieveAll()) {
                    showMessageForEmployee(compilanceEmployee, "new insurance (Id :"+ newInsurance.getId() + ") is created");
                }
                //prototypeDao.deleteById(choicedPrototype.getId());
                return true; // Return true indicating that the prototype creation was successful
            } else {
                System.out.println("Prototype creation cancelled.");
                return false; // Return false indicating that the prototype creation was cancelled
            }
        } else {
            System.out.println("There is no prototypes in charge");
        }
        return true;
    }
}

