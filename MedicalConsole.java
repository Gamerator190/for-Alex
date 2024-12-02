import java.util.*;
public class MedicalConsole {
    public static Scanner scanner = new Scanner(System.in);
    public static HealthService healthservice = new HealthService("Medical");
    public static List<MedicalFacility> medFacHis = new ArrayList<>();
    public static List<Patient> patHis = new ArrayList<>();
    public static List<Procedure> proHis = new ArrayList<>();
    public static void main(String[] args) {
        Patient patient1 = new Patient("John Doe", true);
        Patient patient2 = new Patient("Jane Doe", true);
        Patient patient3 = new Patient("Matthew Simons", false);
        Patient patient4 = new Patient("Luke Skywalker", false);
        Hospital hospital1 = new Hospital("Grace", 0.73);
        Hospital hospital2 = new Hospital("Busy", 0.37);
        Clinic clinic1 = new Clinic("Zion", 100.0, 10.0);
        Clinic clinic2 = new Clinic("Expensive", 500.0, 20.0);
        Procedure procedure1 = new Procedure("Nose Job Surgery", "Patient gets his or her nose fixed.", true, 500.0);
        Procedure procedure2 = new Procedure("Broken Arm Surgery", "Patient is given a splint to fix the broken bones in his or her arm.", false, 700.0);
        Procedure procedure3 = new Procedure("Appendicitis Surgery", "Patient gets surgery to remove his or her appendix.", false, 800.0);
        healthservice.addPatient(patient1);
        healthservice.addPatient(patient2);
        healthservice.addPatient(patient3);
        healthservice.addPatient(patient4);
        healthservice.addMedicalFacility(hospital1);
        healthservice.addMedicalFacility(hospital2);
        healthservice.addMedicalFacility(clinic1);
        healthservice.addMedicalFacility(clinic2);
        hospital1.addProcedure(procedure1);
        hospital1.addProcedure(procedure2);
        hospital2.addProcedure(procedure1);
        hospital2.addProcedure(procedure3);
        boolean quitVar = false;
        while (!quitVar) {
            System.out.println("\nWelcome to " + healthservice.getName() + " HealthService.");
            System.out.println("Type 1 to add an object.");
            System.out.println("Type 2 to list objects.");
            System.out.println("Type 3 to delete an object.");
            System.out.println("Type 4 for a patient to visit a medical facility.");
            System.out.println("Type 5 to operate a patient.");
            System.out.println("Type 6 to edit an object.");
            System.out.println("Type 7 to show history of deleted objects.");
            System.out.println("Type 8 to quit the program.");
            int decision = intValidCheck1(8);
            switch(decision) {
                case 1:
                    addObject();
                    break;
                case 2:
                    listObjects();
                    break;
                case 3:
                    deleteObject();
                    break;
                case 4:
                    patientVisit();
                    break;
                case 5:
                    operate();
                    break;
                case 6:
                    editObject();
                    break;
                case 7:
                    showHistory();
                    break;
                case 8:
                    quitVar = true;
                    break;
                default:
                    System.out.println();
                    break;
            }
        }
    }
    public static void addObject() {
        System.out.println("\nWhich type of object do you want to create?");
        System.out.println("Type 1 for a medical facility.");
        System.out.println("Type 2 for a patient.");
        System.out.println("Type 3 for a procedure.");
        int decisionAdd = intValidCheck1(3);
        switch(decisionAdd) {
            case 1:
                System.out.println("\nWhat is the type of medical facility?");
                System.out.println("Type 1 for a hospital.");
                System.out.println("Type 2 for a clinic.");
                int decisionMed = intValidCheck1(2);
                switch(decisionMed) {
                    case 1:
                        addHospital2();
                        break;
                    case 2:
                        System.out.println("\nWhat is the clinic's name? (No need to type Clinic)");
                        scanner.nextLine(); // Consumes the \n left from a non-nextLine() method usage.
                        String clinicName1 = scanner.nextLine();
                        if (listClinic().size() != 0) {
                            for (Clinic cli : listClinic()) {
                                while (cli.getName().equals(clinicName1)) {
                                    System.out.println("\nSorry, we already have that name. Maybe add a digit at the end to differentiate between clinics.");
                                    clinicName1 = scanner.nextLine();
                                }
                            }
                        }
                        System.out.println("\nWhat is the clinic's fee for consultation?");
                        double clinicDouble1 = doubleValidCheck1();
                        System.out.println("\nWhat is the clinic's gap percentage? (No need to type %)");
                        double clinicDouble2 = doubleValidCheck2();
                        Clinic clinicNew = new Clinic(clinicName1, clinicDouble1, clinicDouble2);
                        healthservice.addMedicalFacility(clinicNew);
                        System.out.println("\nThe " + clinicName1 + " Clinic has been added.");
                        break;
                }
                break;
            case 2:
                System.out.println("\nWhat is the patient's name? (No need to type Patient)");
                scanner.nextLine(); // Consumes the \n left from a non-nextLine method usage.
                String patientname1 = scanner.nextLine();
                if (healthservice.getPatients().size() != 0) {
                    for (Patient pat : healthservice.getPatients()) {
                        while (pat.getName().equals(patientname1)) {
                            System.out.println("\nSorry, we already have that name. Perhaps add a digit at the end to differentiate between patients.");
                            patientname1 = scanner.nextLine();
                        }
                    }
                }
                System.out.println("\nIs the patient private? (y/n)");
                boolean patientBool1 = boolValidCheck1();
                Patient patientnew = new Patient(patientname1, patientBool1);
                healthservice.addPatient(patientnew);
                System.out.println("\nThe patient " + patientname1 + " has been added.");
                break;
            case 3:
                if (listHospital().size() == 0) {
                    System.out.println("\nThere are no hospitals. Please add one, then you can return to add the procedure and its details.");
                }
                else {
                    if (listProcedure1().size() == 0) {
                        addProcedure();
                    }
                    else {
                        System.out.println("\nType 1 to add a new procedure.");
                        System.out.println("Type 2 to add an already existing procedure to hospitals one at a time (the program will keep prompting).");
                        System.out.println("Type 3 to add an already existing procedure to all hospitals.");
                        int proAdd = intValidCheck1(3);
                        switch(proAdd) {
                            case 1:
                                addProcedure();
                                break;
                            case 2:
                                List<Integer> bigmemory11 = new ArrayList<>();
                                List<Procedure> proList1 = new ArrayList<>(listProcedure2());
                                for (Procedure pro : proList1) {
                                    System.out.println("\n" + pro);
                                    bigmemory11.add(pro.getId());
                                }
                                System.out.println("\nType the id of the procedure you wish to add to hospitals one at a time.");
                                int proId1 = intValidCheck2(bigmemory11);
                                partAddProcedure(proList1.get(bigmemory11.indexOf(proId1)));
                                break;
                            case 3:
                                List<Integer> bigmemory12 = new ArrayList<>();
                                List<Procedure> proList2 = new ArrayList<>(listProcedure2());
                                for (Procedure pro : proList2) {
                                    System.out.println("\n" + pro);
                                    bigmemory12.add(pro.getId());
                                }
                                System.out.println("\nType the id of the procedure you wish to add to all hospitals.");
                                int proId2 = intValidCheck2(bigmemory12);
                                for (Hospital hos : listHospital()) {
                                    if (!(listHospital().get(bigmemory12.indexOf(proId2)).getProcedures().contains(proList2.get(bigmemory12.indexOf(proId2))))) {
                                        hos.addProcedure(proList2.get(bigmemory12.indexOf(proId2)));
                                        System.out.println("\n" + proList2.get(bigmemory12.indexOf(proId2)).getName() + " Procedure has been added to " + hos.getName() + " Hospital.");
                                    }
                                    else {
                                        System.out.println("\n" + listHospital().get(bigmemory12.indexOf(proId2)).getName() + " Hospital already has " + proList2.get(bigmemory12.indexOf(proId2)).getName() + " procedure.");
                                    }
                                }
                                break;
                        }
                    }
                    break;
                }
            default:
                System.out.println();
                break;
        }
    }
    public static void listObjects() {
        System.out.println("\nWhich type of object do you want to list?");
        System.out.println("Type 1 for medical facilities.");
        System.out.println("Type 2 for patients.");
        System.out.println("Type 3 for procedures (including procedures in hospitals).");
        int decisionList = intValidCheck1(3);
        switch(decisionList) {
            case 1:
                System.out.println("\nWhat is the type of medical facility?");
                System.out.println("Type 1 for all.");
                System.out.println("Type 2 for hospital.");
                System.out.println("Type 3 for clinic.");
                int decisionType = intValidCheck1(3);
                switch(decisionType) {
                    case 1:
                        if (healthservice.getMedicalFacilities().size() == 0) {
                            System.out.println("\nThere are no medical facilities.");
                        }
                        for (MedicalFacility mf : healthservice.getMedicalFacilities()) {
                            System.out.println("\n" + mf);
                        }
                        break;
                    case 2:
                        if (listHospital().size() == 0) {
                            System.out.println("\nThere are no hospitals.");
                        }
                        for (Hospital hos : listHospital()) {
                            System.out.println("\n" + hos);
                        }
                        break;
                    case 3:
                        if (listClinic().size() == 0) {
                            System.out.println("\nThere are no clinics.");
                        }
                        for (Clinic cli : listClinic()) {
                            System.out.println("\n" + cli);
                        }
                        break;
                }
                break;
            case 2:
                if (healthservice.getPatients().size() == 0) {
                    System.out.println("\nThere are no patients.");
                }
                else {
                    System.out.println("\nHow would you like your patients sorted?");
                    System.out.println("Type 1 for no sorting.");
                    System.out.println("Type 2 to sort in order of name.");
                    System.out.println("Type 3 to sort in order of balance.");
                    int decisionSort = intValidCheck1(3);
                    switch(decisionSort) {
                        case 1:
                            for (Patient pat : healthservice.getPatients()) {
                                System.out.println("\n" + pat);
                            }
                            break;
                        case 2:
                            List<Patient> sortedpat1 = new ArrayList<>(healthservice.getPatients());
                            sortedpat1.sort(Comparator.comparing(Patient::getName));
                            for (Patient pat : sortedpat1) {
                                System.out.println("\n" + pat);
                            }
                            break;
                        case 3:
                            List<Patient> sortedpat2 = new ArrayList<>(healthservice.getPatients());
                            sortedpat2.sort(Comparator.comparingDouble(pat -> pat.getBalance()));
                            Collections.reverse(sortedpat2);
                            for (Patient pat : sortedpat2) {
                                System.out.println("\n" + pat);
                            }
                            break;
                    }
                }
                break;
            case 3:
                if (listProcedure1().size() == 0) {
                    System.out.println("\nThere are no procedures.");
                }
                else {
                    String details1 = "";
                    List<Procedure> chosenPro = new ArrayList<>();
                    System.out.println("\nHow would you like your procedures sorted?");
                    System.out.println("Type 1 for no sorting.");
                    System.out.println("Type 2 to sort in order of id.");
                    System.out.println("Type 3 to sort in order of name.");
                    System.out.println("Type 4 to sort in order of cost.");
                    int decisionPro = intValidCheck1(4);
                    switch(decisionPro) {
                        case 1:
                            chosenPro = listProcedure1();
                            break;
                        case 2:
                            chosenPro = listProcedure2();
                            break;
                        case 3:
                            List<Procedure> orderer2 = listProcedure1();
                            orderer2.sort(Comparator.comparing(pro -> pro.getName()));
                            chosenPro = orderer2;
                            break;
                        case 4:
                            List<Procedure> orderer3 = listProcedure1();
                            orderer3.sort(Comparator.comparingDouble(pro -> pro.getCost()));
                            Collections.reverse(orderer3);
                            chosenPro = orderer3;
                            break;
                    }
                    for (Procedure ele : chosenPro) {
                        details1 += "\n\n" + ele;
                    }
                    System.out.println(details1);
                    System.out.println("\nWould you like to see which hospitals have which procedures? (y/n)");
                    boolean confBool1 = boolValidCheck1();
                    System.out.println();
                    if (confBool1) {
                        List<Hospital> allHos2 = new ArrayList<>(listHospital());
                        List<Procedure> proResult = new ArrayList<>();
                        String proNames = "";
                        String lilmemory5 = "";
                        for (Hospital ele : allHos2) {
                            for (Procedure ele2 : ele.getProcedures()) {
                                if (!(lilmemory5.contains(Integer.toString(ele2.getId())))) {
                                    proResult.add(ele2);
                                    lilmemory5 += Integer.toString(ele2.getId());
                                }
                            }
                            for (Procedure ele3 : proResult) {
                                proNames += ele3.getName() + ", ";
                            }
                            proNames = proNames.substring(0, proNames.length()-2);
                            System.out.println(ele.getName() + " Hospital has " + proNames + " Procedure(s).");
                            proResult = new ArrayList<>();
                            lilmemory5 = "";
                            proNames = "";
                        }
                    }
                }
                break;
            default:
                System.out.println();
                break;
        }
    }
    public static void deleteObject() {
        System.out.println("\nWhich type of object do you want to delete?");
        System.out.println("Type 1 for a medical facility.");
        System.out.println("Type 2 for a patient.");
        System.out.println("Type 3 for a procedure.");
        int decisionDel = intValidCheck1(3);
        switch(decisionDel) {
            case 1:
                if (healthservice.getMedicalFacilities().size() == 0) {
                    System.out.println("\nThere are no medical facilities.");
                }
                else {
                    System.out.println("\nWhat is the type of medical facility?");
                    System.out.println("Type 1 for hospital.");
                    System.out.println("Type 2 for clinic.");
                    int decisionDel1 = intValidCheck1(2);
                    switch(decisionDel1) {
                        case 1:
                            if (listHospital().size() == 0) {
                                System.out.println("\nThere are no hospitals.");
                            }
                            else {
                                List<Hospital> allHos = new ArrayList<>(listHospital());
                                List<Integer> bigmemory3 = new ArrayList<>();
                                for (Hospital hos : allHos) {
                                    System.out.println("\n" + hos);
                                    bigmemory3.add(hos.getId());
                                }
                                System.out.println("\nType in the id of the hospital you wish to delete.");
                                int delVar2 = intValidCheck2(bigmemory3);
                                Hospital theHos = allHos.get(bigmemory3.indexOf(delVar2));
                                if(theHos.getProcedures().size() == 0) {
                                    String tempName2 = theHos.getName();
                                    medFacHis.add(theHos);
                                    healthservice.delMedicalFacility(theHos);
                                    System.out.println(tempName2 + " Hospital has been deleted.");
                                }
                                else{
                                    System.out.println("\n" + theHos.getName() + " Hospital has " + theHos.getProcedures().size() + " Procedure(s)... do you still want to delete it? (y/n)");
                                    boolean confBool2 = boolValidCheck1();
                                    if (confBool2) {
                                        String tempName4 = theHos.getName();
                                        medFacHis.add(theHos);
                                        healthservice.delMedicalFacility(theHos);
                                        System.out.println(tempName4 + " Hospital has been deleted.");
                                    }
                                }
                            }
                            break;
                        case 2:
                            if (listClinic().size() == 0) {
                                System.out.println("\n There are no clinics.");
                            }
                            else {
                                List<Clinic> allCli = new ArrayList<>(listClinic());
                                List<Integer> bigmemory2 = new ArrayList<>();
                                for (Clinic cli : allCli) {
                                    System.out.println("\n" + cli);
                                    bigmemory2.add(cli.getId());
                                }
                                System.out.println("\nType in the id of the clinic you wish to delete.");
                                int delVar1 = intValidCheck2(bigmemory2);
                                String tempName1 = allCli.get(bigmemory2.indexOf(delVar1)).getName();
                                medFacHis.add(allCli.get(bigmemory2.indexOf(delVar1)));
                                healthservice.delMedicalFacility(allCli.get(bigmemory2.indexOf(delVar1)));
                                System.out.println(tempName1 + " Clinic has been deleted.");
                            }
                            break;
                        }
                    }
                break;
            case 2:
                if (healthservice.getPatients().size() == 0) {
                    System.out.println("\nThere are no patients.");
                }
                else {
                    List<Integer> bigmemory4 = new ArrayList<>();
                    for (Patient pat : healthservice.getPatients()) {
                        System.out.println("\n" + pat);
                        bigmemory4.add(pat.getId());
                    }
                    System.out.println("\nType in the id of the patient you wish to delete.");
                    int patId = intValidCheck2(bigmemory4);
                    Patient delPat = healthservice.getPatients().get(bigmemory4.indexOf(patId));
                    patHis.add(delPat);
                    healthservice.delPatient(delPat);
                    System.out.print("\nPatient " + delPat.getName() + " has been deleted.");
                }
                break;
            case 3:
                if (listProcedure1().size() == 0) {
                    System.out.println("\nThere are no procedures.");
                }
                else {
                    System.out.println("\nType 1 to delete a procedure from hospitals one at a time.");
                    System.out.println("Type 2 to delete a procedure from all hospitals.");
                    int proDel = intValidCheck1(2);
                    switch(proDel) {
                        case 1:
                            List<Integer> bigmemory6 = new ArrayList<>();
                            List<Procedure> proList1 = new ArrayList<>(listProcedure2());
                            for (Procedure ele : proList1) {
                                System.out.println("\n" + ele);
                                bigmemory6.add(ele.getId());
                            }
                            System.out.println("\nType in the id of the procedure you wish to delete from hospitals one at a time.");
                            int proId1 = intValidCheck2(bigmemory6);
                            Procedure procedureNew = proList1.get(bigmemory6.indexOf(proId1));
                            List<Hospital> allHos = new ArrayList<>(listHospital());
                            List<Integer> bigmemory1 = new ArrayList<>();
                            for (Hospital hos : allHos) {
                                System.out.println("\n" + hos);
                                bigmemory1.add(hos.getId());
                            }
                            int proChoice = 1;
                            while (proChoice != 0) {
                                System.out.println("\nType in an id of one of your chosen hospitals. (One by one; the program will keep prompting. Type 0 to stop)");
                                proChoice = intValidCheck2(bigmemory1);
                                if (!(proChoice == 0)) {
                                    if ((allHos.get(bigmemory1.indexOf(proChoice)).getProcedures().contains(procedureNew))) {
                                        allHos.get(bigmemory1.indexOf(proChoice)).delProcedure(procedureNew);
                                        System.out.println("\n" + procedureNew.getName() + " Procedure has been deleted from " + allHos.get(bigmemory1.indexOf(proChoice)).getName() + " Hospital.");
                                        if (!listProcedure1().contains(procedureNew)) {
                                            proHis.add(procedureNew);
                                        }
                                    }
                                    else {
                                        System.out.println("\n" + allHos.get(bigmemory1.indexOf(proChoice)).getName() + " Hospital does not have " + procedureNew.getName() + " Procedure.");
                                    }
                                }
                            }
                            break;
                        case 2:
                            List<Integer> bigmemory5 = new ArrayList<>();
                            List<Procedure> proList2 = new ArrayList<>(listProcedure2());
                            for (Procedure ele : proList2) {
                                System.out.println("\n" + ele);
                                bigmemory5.add(ele.getId());
                            }
                            System.out.println("\nType in the id of the procedure you wish to delete from all hospitals.");
                            int proId2 = intValidCheck2(bigmemory5);
                            for (MedicalFacility mf : healthservice.getMedicalFacilities()) {
                                if (mf instanceof Hospital) {
                                    Hospital hos = (Hospital) mf;
                                    List<Procedure> hosPros = new ArrayList<>(hos.getProcedures());
                                    for (Procedure pro : hosPros) {
                                        if (pro == proList2.get(bigmemory5.indexOf(proId2))) {
                                            hos.delProcedure(proList2.get(bigmemory5.indexOf(proId2)));
                                        };
                                    }
                                }
                            }
                            proHis.add(proList2.get(bigmemory5.indexOf(proId2)));
                            System.out.println("\n" + proList2.get(bigmemory5.indexOf(proId2)).getName() + " Procedure has been deleted.");
                            break;
                    }
                }
                break;
            default:
                System.out.println();
                break;
        }
    }
    public static void patientVisit() {
        List<Integer> bigmemory6 = new ArrayList<>();
        for (Patient pat : healthservice.getPatients()) {
            System.out.println("\n" + pat);
            bigmemory6.add(pat.getId());
        }
        System.out.println("\nType the id of the patient that will visit a medical facility.");
        int visitVar1 = intValidCheck2(bigmemory6);
        Patient thePatient = healthservice.getPatients().get(bigmemory6.indexOf(visitVar1));
        List<Integer> bigmemory7 = new ArrayList<>();
        for (MedicalFacility mf : healthservice.getMedicalFacilities()) {
            System.out.println("\n" + mf);
            bigmemory7.add(mf.getId());
        }
        System.out.println("\nType the id of the medical facility that the patient will visit.");
        int visitVar2 = intValidCheck2(bigmemory7);
        MedicalFacility theMedicalFacility = healthservice.getMedicalFacilities().get(bigmemory7.indexOf(visitVar2));
        String oneMedFac = "";
        if (theMedicalFacility instanceof Hospital) {
            oneMedFac = "Hospital";
        }
        else {
            oneMedFac = "Clinic";
        }
        if (theMedicalFacility.equals(thePatient.getCurrentFacility()) && theMedicalFacility instanceof Hospital) {
            System.out.println("\n" + thePatient.getName() + " is already in " + theMedicalFacility.getName() + " Hospital.");
        }
        else {
            double patBalance = thePatient.getBalance();
            MedicalFacility patCurFac = thePatient.getCurrentFacility();
            boolean visitResult = theMedicalFacility.visit(thePatient);
            int visitFails = 0;
            while (!visitResult) {
                visitFails += 1;
                System.out.println("\n" + thePatient.getName() + " has failed to visit " + theMedicalFacility.getName() + " Hospital " + visitFails + " time(s). Try again? (y/n)");
                boolean choiceCheck = boolValidCheck1();
                if (!choiceCheck) {
                    System.out.println("\n" + thePatient.getName() + " has not succeeded visiting " + theMedicalFacility.getName() + " Hospital after " + visitFails + " failed attempt(s).");
                    break;
                }
                visitResult = theMedicalFacility.visit(thePatient);
            }
            if (visitResult) {
                if (oneMedFac.equals("Hospital")) {
                    System.out.println("\n" + thePatient.getName() + " has succeeded visiting " + theMedicalFacility.getName() + " Hospital after " + visitFails + " failed attempt(s).");
                }
                else {
                    if (patCurFac == theMedicalFacility) {
                        System.out.println("\n" + thePatient.getName() + " has succeeded visiting " + theMedicalFacility.getName() + " Clinic and has received a consultation making " + thePatient.getName() + "\'s balance go from " + patBalance + " to " + thePatient.getBalance() + ".");
                    }
                    else {
                        System.out.println("\n" + thePatient.getName() + " has succeeded visiting " + theMedicalFacility.getName() + " Clinic and has registered to receive consultations in subsequent visits.");
                    }
                }
            }
        }
        
    }
    public static void operate() {
        List<Integer> bigmemory8 = new ArrayList<>();
        for (Hospital hos : listHospital()) {
            System.out.println("\n" + hos);
            bigmemory8.add(hos.getId());
        }
        System.out.println("\nType the id of the hospital where the patient will be operated.");
        int operateVar1 = intValidCheck2(bigmemory8);
        Hospital theHospital = listHospital().get(bigmemory8.indexOf(operateVar1));
        List<Integer> bigmemory9 = new ArrayList<>();
        for (Patient pat : healthservice.getPatients()) {
            System.out.println("\n" + pat);
            bigmemory9.add(pat.getId());
        }
        System.out.println("\nType the id of the patient that will get operated.");
        int operateVar2 = intValidCheck2(bigmemory9);
        Patient thePatient = healthservice.getPatients().get(bigmemory9.indexOf(operateVar2));
        if (!(thePatient.getCurrentFacility() == theHospital)) {
            System.out.println("\nPatient " + thePatient.getName() + " has not been admitted to " + theHospital.getName() + " Hospital.");
        }
        else {
            List<Integer> bigmemory10 = new ArrayList<>();
            for (Procedure pro : theHospital.getProcedures()) {
                System.out.println("\n" + pro);
                bigmemory10.add(pro.getId());
            }
            System.out.println("\nType the id of the procedure that should be performed.");
            int operateVar3 = intValidCheck2(bigmemory9);
            Procedure theProcedure = theHospital.getProcedures().get(bigmemory10.indexOf(operateVar3));
            double patBalance = thePatient.getBalance();
            System.out.println("\nPerforming " + theProcedure.getName() + " Procedure on " + thePatient.getName() + " at " + theHospital.getName() + " Hospital...");
            theHospital.operate(thePatient, theProcedure);
            System.out.println("\nThe operation has been performed; " + thePatient.getName() + "\'s balance went from " + patBalance + " to " + thePatient.getBalance() + ".");
            boolean visitResult = theHospital.visit(thePatient);
            if (visitResult) {
                System.out.println("\n" + thePatient.getName() + " remains in " + theHospital.getName() + " Hospital. An additional procedure may be performed by selecting the Operate option again for the same patient.");
            }
            else {
                thePatient.setCurrentFacility(null);
                System.out.println("\n" + thePatient.getName() + " is discharged from " + theHospital.getName() + " Hospital.");
            }
        }
        
    }
    public static void editObject() {
        System.out.println("\nWhich type of object do you want to edit?");
        System.out.println("Type 1 for a medical facility.");
        System.out.println("Type 2 for a patient.");
        System.out.println("Type 3 for a procedure.");
        System.out.println("Type 4 for the health service.");
        int decisionEdit = intValidCheck1(4);
        switch(decisionEdit) {
            case 1:
                List<Integer> bigmemory11 = new ArrayList<>();
                for (MedicalFacility mf : healthservice.getMedicalFacilities()) {
                    System.out.println("\n" + mf);
                    bigmemory11.add(mf.getId());
                }
                System.out.println("\nType the id of the medical facility you would like to edit.");
                int mfId = intValidCheck2(bigmemory11);
                MedicalFacility theMedicalFacility = healthservice.getMedicalFacilities().get(bigmemory11.indexOf(mfId));
                if (theMedicalFacility instanceof Hospital) {
                    Hospital theHos = (Hospital) theMedicalFacility;
                    System.out.println("\nWhich attribute of " + theHos.getName() + " Hospital would you like to edit?");
                    System.out.println("Type 1 for the name.");
                    System.out.println("Type 2 for the probability of admission.");
                    int decisionHos = intValidCheck1(2);
                    switch(decisionHos) {
                        case 1:
                            String oldName = theHos.getName();
                            System.out.println("\nWhat would be the new name of " + oldName + " Hospital? (No need to type Hospital)");
                            scanner.nextLine(); //Consumes the \n left from a non-nextLine method usage.
                            String newName = scanner.nextLine();
                            if (oldName.equals(newName)) {
                                System.out.println("\nThe name is not changing.");
                            }
                            else {
                                theHos.setName(newName);
                                System.out.println(oldName + " Hospital is now named " + newName + " Hospital.");
                            }
                            break;
                        case 2:
                            double oldProb = theHos.getProbAdmit();
                            System.out.println("\nWhat would be the new probability of admission of " + theHos.getName() + " Hospital? (0.0-1.0)");
                            double newProb = doubleValidCheck3();
                            while (!(0 <= newProb && newProb <= 1)) {
                                System.out.println("\nPlease type in a valid probability of admission (0.0-1.0).");
                                newProb = doubleValidCheck3();
                            }
                            if (oldProb == newProb) {
                                System.out.println("\nThe probability of admission is not changing.");
                            }
                            else {
                                theHos.setProbAdmit(newProb);
                                System.out.println(theHos.getName() + " Hospital's probability of admission went from " + oldProb + " to " + newProb + ".");
                            }
                            break;
                        default:
                            System.out.println();
                            break;
                    }
                } 
                else {
                    Clinic theCli = (Clinic) theMedicalFacility;
                    System.out.println("\nWhich attribute of " + theCli.getName() + " Clinic would you like to edit?");
                    System.out.println("Type 1 for the name.");
                    System.out.println("Type 2 for the fee.");
                    System.out.println("Type 3 for the gap percentage.");
                    int decisionCli = intValidCheck1(3);
                    switch(decisionCli) {
                        case 1:
                            String oldName = theCli.getName();
                            System.out.println("\nWhat would be the new name of " + oldName + " Clinic?");
                            scanner.nextLine(); //Consumes the \n left from a non-nextLine method usage.
                            String newName = scanner.nextLine();
                            if (oldName.equals(newName)) {
                                System.out.println("\nThe name is not changing.");
                            }
                            else {
                                theCli.setName(newName);
                                System.out.println(oldName + " Clinic is now named " + newName + " Clinic.");
                            }
                            break;
                        case 2:
                            double oldFee = theCli.getFee();
                            System.out.println("\nWhat would be the new fee of " + theCli.getName() + " Clinic?");
                            double newFee = doubleValidCheck1();
                            if (oldFee == newFee) {
                                System.out.println("\nThe fee is not changing.");
                            }
                            else {
                                theCli.setFee(newFee);
                                System.out.println(theCli.getName() + " Clinic's fee went from " + oldFee + " to " + newFee + ".");
                            }
                            break;
                        case 3:
                            double oldGap = theCli.getGapPercent();
                            System.out.println("\nWhat would be the new gap percentage of " + theCli.getName() + " Clinic? (No need to type %)");
                            double newGap = doubleValidCheck2();
                            if (oldGap == newGap) {
                                System.out.println("\nThe gap percentage is not changing.");
                            }
                            else {
                                theCli.setGapPercent(newGap);
                                System.out.println(theCli.getName() + " Clinic's gap percentage went from " + oldGap*100 + "% to " + newGap + "%.");
                            }
                            break;
                        default:
                            System.out.println();
                            break;
                    }
                }
                break;
            case 2:
                List<Integer> bigmemory12 = new ArrayList<>();
                for (Patient pat: healthservice.getPatients()) {
                    System.out.println("\n" + pat);
                    bigmemory12.add(pat.getId());
                }
                System.out.println("\nType the id of the patient you would like to edit.");
                int patId = intValidCheck2(bigmemory12);
                Patient thePat = healthservice.getPatients().get(bigmemory12.indexOf(patId));
                System.out.println("\nWhich attribute of Patient " + thePat.getName() + " would you like to edit?");
                System.out.println("Type 1 for the name.");
                System.out.println("Type 2 for the private/public status.");
                System.out.println("Type 3 for the balance.");
                System.out.println("Type 4 for the current facility (Discharge patient from the medical facility he or she is currently at).");
                int decisionPat = intValidCheck1(4);
                switch(decisionPat) {
                    case 1:
                        String oldName = thePat.getName();
                        System.out.println("\nWhat would be the new name of Patient " + oldName + "? (No need to type Patient)");
                        scanner.nextLine(); //Consumes the \n left from a non-nextLine method usage.
                        String newName = scanner.nextLine();
                        if (oldName.equals(newName)) {
                            System.out.println("\n" + oldName + "\'s name is not changing.");
                        }
                        else {
                            thePat.setName(newName);
                            System.out.println("\nPatient " + oldName + " is now named Patient " + newName);
                        }
                        break;
                    case 2:
                        boolean oldStat = thePat.getIsPrivate();
                        String statStr = "";
                        System.out.println("\nWhat would be the new status of Patient " + thePat.getName() + "? (private/public)");
                        boolean newStat = boolValidCheck2();
                        if (newStat) {
                            statStr = "private";
                        }
                        else {
                            statStr = "public";
                        }
                        if (newStat == oldStat) {
                            System.out.println("\n" + thePat.getName() + " is already a " + statStr + " patient!");
                        }
                        else {
                            thePat.setIsPrivate(newStat);
                            System.out.println("\nPatient " + thePat.getName() + " is now a " + statStr + " Patient.");
                        }
                        break;
                    case 3:
                        double oldBalance = thePat.getBalance();
                        System.out.println("\nWhat would be the new balance of Patient " + thePat.getName() + "?");
                        double newBalance = doubleValidCheck1();
                        if (oldBalance == newBalance) {
                            System.out.println("\nThe patient's balance remains the same.");
                        }
                        else {
                            thePat.setBalance(newBalance);
                            System.out.println("\nPatient " + thePat.getName() + "\'s balance went from " + oldBalance + " to " + newBalance + ".");
                        }
                        break;
                    case 4:
                        if (thePat.getCurrentFacility() != null) {
                            MedicalFacility oldCurFac = thePat.getCurrentFacility();
                            String oldCurFacName = oldCurFac.getName();
                            String medFacStr = "";
                            if (oldCurFac instanceof Hospital) {
                                medFacStr = "Hospital";
                            }
                            else {
                                medFacStr = "Clinic";
                            }
                            System.out.println("\nPatient " + thePat.getName() + " has been discharged from " + oldCurFacName + " " + medFacStr + ".");
                            thePat.setCurrentFacility(null);
                        }
                        else {
                            System.out.println("\nPatient " + thePat.getName() + " has already been discharged.");
                        }
                }
                break;
            case 3:
                System.out.println("\nType 1 to edit a procedure in hospitals one at a time (the program will keep prompting).");
                System.out.println("\nType 2 to edit a procedure in all hospitals.");
                int proEdit = intValidCheck1(2);
                switch(proEdit) {
                    case 1:
                        List<Integer> bigmemory6 = new ArrayList<>();
                        List<Procedure> proList1 = new ArrayList<>(listProcedure2());
                        for (Procedure ele : proList1) {
                            System.out.println("\n" + ele);
                            bigmemory6.add(ele.getId());
                        }
                        System.out.println("\nType in the id of the procedure you wish to edit in hospitals one at a time.");
                        int proId1 = intValidCheck2(bigmemory6);
                        Procedure procedureNew = proList1.get(bigmemory6.indexOf(proId1));
                        System.out.println("\nType in the id of the new procedure.");
                        int proId2 = intValidCheck2(bigmemory6);
                        while (!bigmemory6.contains(proId2) || proId1 == proId2) {
                            if (!bigmemory6.contains(proId2)) {
                                System.out.println("\nPlease type in a valid id.");
                            }
                            else {
                                System.out.println("\nPlease do not choose the same procedure.");
                            }
                            proId2 = intValidCheck2(bigmemory6);
                        }
                        Procedure procedureNew2 = proList1.get(bigmemory6.indexOf(proId2));
                        List<Hospital> allHos = new ArrayList<>(listHospital());
                        List<Integer> bigmemory1 = new ArrayList<>();
                        for (Hospital hos : allHos) {
                            System.out.println("\n" + hos);
                            bigmemory1.add(hos.getId());
                        }
                        int proChoice = 1;
                        while (proChoice != 0) {
                            System.out.println("\nType in an id of one of your chosen hospitals. (One by one; the program will keep prompting. Type 0 to stop)");
                            proChoice = intValidCheck2(bigmemory1);
                            if (!(proChoice == 0)) {
                                if (bigmemory1.contains(proChoice) && (allHos.get(bigmemory1.indexOf(proChoice)).getProcedures().contains(procedureNew))) {
                                    allHos.get(bigmemory1.indexOf(proChoice)).editProcedure(allHos.get(bigmemory1.indexOf(proChoice)).getProcedures().indexOf(procedureNew), procedureNew2);
                                    System.out.println("\n" + procedureNew.getName() + " Procedure has been changed to " + procedureNew2.getName() + " Procedure in " + allHos.get(bigmemory1.indexOf(proChoice)).getName() + " Hospital.");
                                    if (!listProcedure1().contains(procedureNew)) {
                                        proHis.add(procedureNew);
                                    }
                                }
                                else {
                                    if(!bigmemory1.contains(proChoice)) {
                                        System.out.println("\nInvalid id, please try again.");
                                    }
                                    else {
                                        System.out.println("\n" + procedureNew.getName() + " Procedure is not in " + allHos.get(bigmemory1.indexOf(proChoice)).getName() + " Hospital.");
                                    }
                                }
                            }
                        }
                        break;
                    case 2:
                        List<Integer> bigmemory13 = new ArrayList<>();
                        List<Integer> bigmemory14 = new ArrayList<>();
                        List<Procedure> proList2 = new ArrayList<>(listProcedure2());
                        for (Procedure ele : proList2) {
                            System.out.println("\n" + ele);
                            bigmemory13.add(ele.getId());
                        }
                        System.out.println("\nType in the id of the procedure you wish to edit in all hospitals.");
                        int proId3 = intValidCheck2(bigmemory13);
                        Procedure procedureNew3 = proList2.get(bigmemory13.indexOf(proId3));
                        System.out.println("\nType in the id of the new procedure.");
                        int proId4 = intValidCheck2(bigmemory13);
                        while (!bigmemory13.contains(proId4) || proId3 == proId4) {
                            if (!bigmemory13.contains(proId4)) {
                                System.out.println("\nPlease type in a valid id.");
                            }
                            else {
                                System.out.println("\nPlease do not choose the same procedure.");
                            }
                            proId4 = intValidCheck2(bigmemory13);
                        }
                        Procedure procedureNew4 = proList2.get(bigmemory13.indexOf(proId4));
                        for (MedicalFacility mf : healthservice.getMedicalFacilities()) {
                            if (mf instanceof Hospital) {
                                Hospital hos = (Hospital) mf;
                                for (Procedure pro : hos.getProcedures()) {
                                    bigmemory14.add(pro.getId());
                                }
                                for (Procedure pro : hos.getProcedures()) {
                                    if (pro == proList2.get(bigmemory13.indexOf(proId3))) {
                                        hos.editProcedure(bigmemory14.indexOf(proId3), procedureNew4);
                                    };
                                }
                            }
                        }
                        proHis.add(procedureNew3);
                        System.out.println("\n" + procedureNew3.getName() + " Procedure has been changed to " + procedureNew4.getName() + " Procedure in all Hospitals, effectively deleting " + procedureNew3.getName() + " Procedure.");
                        break;
                    default:
                        System.out.println();
                        break;
                }
                break;
            case 4:
                String oldName = healthservice.getName();
                System.out.println(healthservice);
                System.out.println("\nWhat would be the new name of the health service?");
                scanner.nextLine(); //Consumes the \n left from a non-nextLine() method usage.
                String newName = scanner.nextLine();
                if (oldName.equals(newName)) {
                    System.out.println("\nThe name is not changing.");
                }
                else {
                    healthservice.setName(newName);
                    System.out.println(oldName + " Health Service is now named " + newName + " Health Service.");
                }
                break;
            default:
                System.out.println();
                break;
        }
    }
    public static void addHospital1() {
        System.out.println("\nWhat is the hospital's name?");
        scanner.nextLine(); // Consumes the \n left from a non-nextLine() method usage.
        String hospitalName1 = scanner.nextLine();
        if (listHospital().size() != 0) {
            for (Hospital hos : listHospital()) {
                while (hos.getName().equals(hospitalName1)) {
                    System.out.println("\nSorry, we already have that name. Maybe add a digit at the end to differentiate between hospitals.");
                    hospitalName1 = scanner.nextLine();
                }
            }
        }
        System.out.println("\nWhat is the hospital's probability of admission for a patient? (0.0-1.0)");
        double hospitalDouble1 = doubleValidCheck3();
        Hospital hospitalNew = new Hospital(hospitalName1, hospitalDouble1);
        healthservice.addMedicalFacility(hospitalNew);
        System.out.println("\n" + hospitalName1 + " Hospital has been added.");
    }
    public static void addHospital2() {
        System.out.println("\nWhat is the hospital's name? (No need to type Hospital)");
        scanner.nextLine(); // Consumes the \n left from a non-nextLine() method usage.
        String hospitalName1 = scanner.nextLine();
        if (listHospital().size() != 0) {
            for (Hospital hos : listHospital()) {
                while (hos.getName().equals(hospitalName1)) {
                    System.out.println("\nSorry, we already have that name. Maybe add a digit at the end to differentiate between hospitals.");
                    hospitalName1 = scanner.nextLine();
                }
            }
        }
        System.out.println("\nWhat is the hospital's probability of admission for a patient? (0.0-1.0)");
        double hospitalDouble1 = doubleValidCheck3();
        String details2 = "";
        String lilmemory2 = "";
        List<Integer> bigmemory = new ArrayList<>();
        List<Procedure> tempPro = new ArrayList<>(listProcedure2());
        List<Procedure> hospitalPros = new ArrayList<>();
        if (tempPro.size() != 0)
            for (Procedure ele : tempPro) {
                details2 += "\n\n" + ele;
                bigmemory.add(ele.getId());
            }
            System.out.println(details2);
            System.out.println("\nWould you like any of these procedures in " + hospitalName1 + " Hospital? (y/n)");
            boolean hospitalBool1 = boolValidCheck1();
            if (hospitalBool1) {
                int chosenPro = 1;
                while (chosenPro != 0) {
                    System.out.println("\nType in an id of one of your chosen procedures. (One by one; the program will keep prompting. Type 0 to stop)");
                    chosenPro = intValidCheck2(bigmemory);
                    if (chosenPro == 0) {
                        break;
                    }
                    if (!(lilmemory2.contains(Integer.toString(chosenPro))) && bigmemory.contains(chosenPro)) {
                        hospitalPros.add(tempPro.get(bigmemory.indexOf(chosenPro)));
                        lilmemory2 += Integer.toString(chosenPro);
                    }
                    else {
                        if (!bigmemory.contains(chosenPro)) {
                            System.out.println("\nInvalid id.");
                        }
                        else {
                            System.out.println("\n" + tempPro.get(bigmemory.indexOf(chosenPro)).getName() + " Procedure is already in " + hospitalName1 + " Hospital.");
                        }
                    }
                }
            }
        if (hospitalPros.size() == 0) {
            Hospital hospitalNew = new Hospital(hospitalName1, hospitalDouble1);
            healthservice.addMedicalFacility(hospitalNew);
            System.out.println("\nAdding hospital without procedures...");
        }
        else {
            Hospital hospitalNew = new Hospital(hospitalName1, hospitalDouble1, hospitalPros);
            healthservice.addMedicalFacility(hospitalNew);
        }
        System.out.println("\n" + hospitalName1 + " Hospital has been added.");
    }
    public static void addProcedure() {
        scanner.nextLine(); // Consumes the \n left from a non-nextLine method usage.
        System.out.println("\nWhat is the procedure's name?");
        String proName1 = scanner.nextLine();
        if (listProcedure1().size() != 0) {
            for (Procedure pro : listProcedure1()) {
                while (pro.getName().equals(proName1)) {
                    System.out.println("\nSorry, we already have that name. Maybe add a digit at the end to differentiate the procedures.");
                    proName1 = scanner.nextLine();
                }
            }
        }
        System.out.println("\nWhat is the procedure's description?");
        String proDesc1 = scanner.nextLine();
        System.out.println("\nIs the procedure elective? (y/n)");
        boolean proBool1 = boolValidCheck1();
        System.out.println("\nWhat is the cost of the procedure?");
        double proDouble1 = doubleValidCheck1();
        Procedure procedureNew = new Procedure(proName1, proDesc1, proBool1, proDouble1);
        System.out.println(proName1 + " Procedure has been added.");
        partAddProcedure(procedureNew);
    }
    public static void partAddProcedure(Procedure procedureNew) {
        List<Hospital> allHos = new ArrayList<>(listHospital());
        List<Integer> bigmemory1 = new ArrayList<>();
        for (Hospital hos : allHos) {
            System.out.println("\n" + hos);
            bigmemory1.add(hos.getId());
        }
        int proChoice = 1;
        while (proChoice != 0) {
            System.out.println("\nType in an id of one of your chosen hospitals. (One by one; the program will keep prompting. Type 0 to stop)");
            proChoice = intValidCheck2(bigmemory1);
            if (proChoice == 0) {
                break;
            }
            if (bigmemory1.contains(proChoice) && !(allHos.get(bigmemory1.indexOf(proChoice)).getProcedures().contains(procedureNew))) {
                allHos.get(bigmemory1.indexOf(proChoice)).addProcedure(procedureNew);
                System.out.println("\n" + procedureNew.getName() + " Procedure has been added to " + allHos.get(bigmemory1.indexOf(proChoice)).getName() + " Hospital.");
            }
            else {
                if (!bigmemory1.contains(proChoice)) {
                    System.out.println("\nInvalid id.");
                }
                else {
                    System.out.println("\n" + procedureNew.getName() + " Procedure is already in " + allHos.get(bigmemory1.indexOf(proChoice)).getName() + " Hospital.");
                }
            }
        }
    }
    public static List<Hospital> listHospital() {
        List<Hospital> hosList = new ArrayList<>();
        for (MedicalFacility mf : healthservice.getMedicalFacilities()) {
            if (mf instanceof Hospital) {
                Hospital hos = (Hospital) mf;
                hosList.add(hos);
            }
        }
        return hosList;
    }
    public static List<Clinic> listClinic() {
        List<Clinic> cliList = new ArrayList<>();
        for (MedicalFacility mf : healthservice.getMedicalFacilities()) {
            if (mf instanceof Clinic) {
                Clinic cli = (Clinic) mf;
                cliList.add(cli);
            }
        }
        return cliList;
    }
    public static List<Procedure> listProcedure1() {
        String lilmemory = "";
        List<Procedure> orderer0 = new ArrayList<>();
        for (MedicalFacility mf : healthservice.getMedicalFacilities()) {
            if (mf instanceof Hospital) {
                Hospital hos = (Hospital) mf;
                for (Procedure pro : hos.getProcedures()) {
                    if (!lilmemory.contains(Integer.toString(pro.getId()))) {
                        orderer0.add(pro);
                        lilmemory += pro.getId();
                    };
                }
            }
        }
        return orderer0;
    }
    public static List<Procedure> listProcedure2() {
        List<Procedure> orderer1 = listProcedure1();
        orderer1.sort(Comparator.comparingInt(pro -> pro.getId()));
        return orderer1;
    }
    public static void showHistory() {
        System.out.println("\nWhich type of object's history?");
        System.out.println("Type 1 for medical facilities.");
        System.out.println("Type 2 for patients.");
        System.out.println("Type 3 for procedures.");
        int decisionHis = intValidCheck1(3);
        switch(decisionHis) {
            case 1:
                System.out.println("\nWhich type of medical facility's history?");
                System.out.println("Type 1 for all.");
                System.out.println("Type 2 for hospitals.");
                System.out.println("Type 3 for clinics.");
                int decisionMF = intValidCheck1(3);
                switch(decisionMF) {
                    case 1:
                        if (medFacHis.size() == 0) {
                            System.out.println("\nThere are no deleted medical facilities.");
                        }
                        for (MedicalFacility mf : medFacHis) {
                            System.out.println("\n" + mf);
                        }
                        break;
                    case 2:
                        int hosCounter = 0;
                        for (MedicalFacility mf : medFacHis) {
                            if (mf instanceof Hospital) {
                                hosCounter += 1;
                            }
                        }
                        if (hosCounter == 0) {
                            System.out.println("\nThere are no deleted hospitals.");
                        }
                        for (MedicalFacility mf : medFacHis) {
                            if (mf instanceof Hospital) {
                                System.out.println("\n" + mf);
                            }
                        }
                        break;
                    case 3:
                        int cliCounter = 0;
                        for (MedicalFacility mf : medFacHis) {
                            if (mf instanceof Clinic) {
                                cliCounter += 1;
                            }
                        }
                        if (cliCounter == 0) {
                            System.out.println("\nThere are no deleted clinics.");
                        }
                        for (MedicalFacility mf : medFacHis) {
                            if (mf instanceof Clinic) {
                                System.out.println("\n" + mf);
                            }
                        }
                        break;
                    default:
                        System.out.println();
                        break;
                }
                break;
            case 2:
                if(patHis.size() == 0) {
                    System.out.println("\nThere are no deleted patients.");
                }
                for (Patient pat : patHis) {
                    System.out.println("\n" + pat);
                }
                break;
            case 3:
                if(proHis.size() == 0) {
                    System.out.println("\nThere are no deleted procedures.");
                }
                for (Procedure pro : proHis) {
                    System.out.println("\n" + pro);
                }
                break;
            default:
                System.out.println();
                break;
        }
    }
    public static int intValidCheck1(int limit) {
        int id = 0;
        boolean validity = false;
        while (!validity) {
            try {
                id = scanner.nextInt();
                if (id == 0) {
                    break;
                }
                if (1 <= id && id <= limit) {
                    validity = true;
                }
                else {
                    System.out.println("\nPlease enter a valid number.");
                }
            }
            catch(Exception e) {
                System.out.println("\nPlease enter a valid number (type 0 to stop).");
                scanner.nextLine(); //This clears the invalid input.
            }
        }
        return id;
    }
    public static int intValidCheck2(List<Integer> bigmemory) {
        int id = 0;
        boolean validity = false;
        while (!validity && !bigmemory.contains(id)) {
            try {
                id = scanner.nextInt();
                if (id == 0) {
                    break;
                }
                if (!bigmemory.contains(id)) {
                    System.out.println("\nPlease enter a valid id.");
                }
                else {
                    validity = true;
                }
            }
            catch(Exception e) {
                System.out.println("\nPlease enter a valid number (type 0 to stop).");
                scanner.nextLine(); //This clears the invalid input.
            }
        }
        return id;
    }
    public static boolean boolValidCheck1() {
        String inputStr = "";
        boolean confBool = false;
        boolean validity = false;
        while (!validity) {
            try {
                inputStr = scanner.next();
                if (inputStr.equals("0")) {
                    break;
                }
                if (inputStr.substring(0, 1).equalsIgnoreCase("y")) {
                    confBool = true;
                    validity = true;
                }
                else if (inputStr.substring(0, 1).equalsIgnoreCase("n")) {
                    confBool = false;
                    validity = true;
                }
                else {
                    System.out.println("\nPlease enter a yes or a no (Type 0 to stop).");
                }
            }
            catch(Exception e) {
                System.out.println("\nPlease enter a yes or a no (Type 0 to stop).");
                scanner.nextLine(); //This clears the invalid input.
            }
        }
        return confBool;
    }
    public static boolean boolValidCheck2() {
        String inputStr = "";
        boolean confBool = false;
        boolean validity = false;
        while (!validity) {
            try {
                inputStr = scanner.next();
                if (inputStr.equals("0")) {
                    break;
                }
                if (inputStr.equalsIgnoreCase("public")) {
                    confBool = false;
                    validity = true;
                }
                else if (inputStr.equalsIgnoreCase("private")) {
                    confBool = true;
                    validity = true;
                }
                else {
                    System.out.println("\nPlease enter private or public (Type 0 to stop).");
                }
            }
            catch(Exception e) {
                System.out.println("\nPlease enter private or public (Type 0 to stop).");
                scanner.nextLine(); //This clears the invalid input.
            }
        }
        return confBool;
    }
    public static double doubleValidCheck1() {
        String inputStr = "";
        double doubleVar = 0;
        boolean validity = false;
        while (!validity && doubleVar < 0) {
            try {
                inputStr = scanner.next();
                if (inputStr.equalsIgnoreCase("stop")) {
                    break;
                }
                doubleVar = Double.parseDouble(inputStr);
                if (doubleVar < 0) {
                    System.out.println("\nPlease enter a non-negative number (Type stop to stop).");
                }
                else {
                    validity = true;
                }
            }
            catch(Exception e) {
                System.out.println("\nPlease enter a valid number (Type stop to stop).");
                scanner.nextLine(); //This clears the invalid input.
            }
        }
        return doubleVar;
    }
    public static double doubleValidCheck2() {
        String inputStr = "";
        double doubleVar = 0;
        boolean validity = false;
        while (!validity && !(0 <= doubleVar && doubleVar <= 100)) {
            try {
                inputStr = scanner.next();
                if(inputStr.equalsIgnoreCase("stop")) {
                    break;
                }
                doubleVar = Double.parseDouble(inputStr);
                if (!(0 <= doubleVar && doubleVar <= 100)) {
                    System.out.println("\nPlease enter a gap percentage between 0 and 100.");
                }
                else {
                    validity = true;
                }
            }
            catch(Exception e) {
                System.out.println("\nPlease enter a valid number.");
                scanner.nextLine(); //This clears the invalid input.
            }
        }
        return doubleVar;
    }
    public static double doubleValidCheck3() {
        String inputStr = "";
        double doubleVar = 0;
        boolean validity = false;
        while (!validity && !(0 <= doubleVar && doubleVar <= 1)) {
            try {
                inputStr = scanner.next();
                if(inputStr.equalsIgnoreCase("stop")) {
                    break;
                }
                doubleVar = Double.parseDouble(inputStr);
                if (!(0 <= doubleVar && doubleVar <= 1)) {
                    System.out.println("\nPlease enter a probability of admission between 0 and 1.");
                }
                else {
                    validity = true;
                }
            }
            catch(Exception e) {
                System.out.println("\nPlease enter a valid number.");
                scanner.nextLine(); //This clears the invalid input.
            }
        }
        return doubleVar;
    }
}
