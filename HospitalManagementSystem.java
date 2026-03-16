package com.tap;
// ============================================================
//  JAVA PROJECT: Hospital Management System
//  OOP Concepts: Encapsulation, Inheritance, Polymorphism, Abstraction
//  Java Basics:  Variables, Arrays, Loops, if-else, Methods, Scanner
// ============================================================

import java.util.Scanner;

// ─────────────────────────────────────────────────────────────
// ABSTRACTION: Abstract class — acts as a blueprint
// Cannot be instantiated directly
// ─────────────────────────────────────────────────────────────
abstract class Person {

    // ENCAPSULATION: private fields — hidden from outside
    private String name;
    private int    age;
    private String id;

    // Constructor
    public Person(String id, String name, int age) {
        this.id   = id;
        this.name = name;
        this.age  = age;
    }

    // Getters — controlled access (Encapsulation)
    public String getId()   { return id;   }
    public String getName() { return name; }
    public int    getAge()  { return age;  }

    // Setter — controlled update (Encapsulation)
    public void setName(String name) { this.name = name; }

    // ABSTRACTION: abstract method — subclass MUST implement this
    public abstract void displayInfo();
    public abstract String getRole();
}

// ─────────────────────────────────────────────────────────────
// INHERITANCE: Doctor class extends Person
// Doctor gets all Person fields + its own fields
// ─────────────────────────────────────────────────────────────
class Doctor extends Person {

    // Doctor-specific private fields (Encapsulation)
    private String specialization;
    private double consultationFee;

    // Constructor calls super() to initialize Person fields
    public Doctor(String id, String name, int age,
                  String specialization, double consultationFee) {
        super(id, name, age);           // calls Person constructor
        this.specialization  = specialization;
        this.consultationFee = consultationFee;
    }

    // Getter
    public double getConsultationFee() { return consultationFee; }
    public String getSpecialization()  { return specialization;  }

    // POLYMORPHISM: override displayInfo() with Doctor-specific details
    @Override
    public void displayInfo() {
        System.out.println("------------------------------");
        System.out.println("Role           : " + getRole());
        System.out.println("ID             : " + getId());
        System.out.println("Name           : " + getName());
        System.out.println("Age            : " + getAge());
        System.out.println("Specialization : " + specialization);
        System.out.println("Consult Fee    : Rs." + consultationFee);
        System.out.println("------------------------------");
    }

    // POLYMORPHISM: override getRole()
    @Override
    public String getRole() { return "Doctor"; }
}

// ─────────────────────────────────────────────────────────────
// INHERITANCE: Patient class extends Person
// Patient gets all Person fields + its own fields
// ─────────────────────────────────────────────────────────────
class Patient extends Person {

    // Patient-specific private fields (Encapsulation)
    private String disease;
    private int    daysAdmitted;
    private double bedChargePerDay;
    private double medicineCharge;

    // Constructor
    public Patient(String id, String name, int age,
                   String disease, int daysAdmitted,
                   double bedChargePerDay, double medicineCharge) {
        super(id, name, age);
        this.disease         = disease;
        this.daysAdmitted    = daysAdmitted;
        this.bedChargePerDay = bedChargePerDay;
        this.medicineCharge  = medicineCharge;
    }

    // Getters (Encapsulation)
    public String getDisease()       { return disease;       }
    public int    getDaysAdmitted()  { return daysAdmitted;  }

    // Method: calculate total bill using basic formula
    public double calculateBill() {
        double bedTotal = daysAdmitted * bedChargePerDay;
        double total    = bedTotal + medicineCharge;
        return total;
    }

    // POLYMORPHISM: override displayInfo() with Patient-specific details
    @Override
    public void displayInfo() {
        System.out.println("------------------------------");
        System.out.println("Role           : " + getRole());
        System.out.println("ID             : " + getId());
        System.out.println("Name           : " + getName());
        System.out.println("Age            : " + getAge());
        System.out.println("Disease        : " + disease);
        System.out.println("Days Admitted  : " + daysAdmitted);
        System.out.println("Bed Charge/Day : Rs." + bedChargePerDay);
        System.out.println("Medicine Charge: Rs." + medicineCharge);
        System.out.println("Total Bill     : Rs." + calculateBill());
        System.out.println("------------------------------");
    }

    // POLYMORPHISM: override getRole()
    @Override
    public String getRole() { return "Patient"; }
}

// ─────────────────────────────────────────────────────────────
// INHERITANCE: Nurse class extends Person
// ─────────────────────────────────────────────────────────────
class Nurse extends Person {

    // Nurse-specific private fields (Encapsulation)
    private String ward;
    private double salary;

    // Constructor
    public Nurse(String id, String name, int age,
                 String ward, double salary) {
        super(id, name, age);
        this.ward   = ward;
        this.salary = salary;
    }

    // Getter
    public String getWard()   { return ward;   }
    public double getSalary() { return salary; }

    // POLYMORPHISM: override displayInfo()
    @Override
    public void displayInfo() {
        System.out.println("------------------------------");
        System.out.println("Role    : " + getRole());
        System.out.println("ID      : " + getId());
        System.out.println("Name    : " + getName());
        System.out.println("Age     : " + getAge());
        System.out.println("Ward    : " + ward);
        System.out.println("Salary  : Rs." + salary);
        System.out.println("------------------------------");
    }

    // POLYMORPHISM: override getRole()
    @Override
    public String getRole() { return "Nurse"; }
}

// ─────────────────────────────────────────────────────────────
// INHERITANCE: Staff class extends Person (e.g., Receptionist)
// ─────────────────────────────────────────────────────────────
class Staff extends Person {

    private String department;
    private double salary;

    public Staff(String id, String name, int age,
                 String department, double salary) {
        super(id, name, age);
        this.department = department;
        this.salary     = salary;
    }

    // POLYMORPHISM: override displayInfo()
    @Override
    public void displayInfo() {
        System.out.println("------------------------------");
        System.out.println("Role       : " + getRole());
        System.out.println("ID         : " + getId());
        System.out.println("Name       : " + getName());
        System.out.println("Age        : " + getAge());
        System.out.println("Department : " + department);
        System.out.println("Salary     : Rs." + salary);
        System.out.println("------------------------------");
    }

    @Override
    public String getRole() { return "Staff"; }
}

// ─────────────────────────────────────────────────────────────
// Hospital class — manages arrays of Doctors, Patients, Nurses
// Uses basic Java: arrays, for loop, if-else, methods
// ─────────────────────────────────────────────────────────────
class Hospital {

    private String   hospitalName;

    // Basic arrays to store persons (not collections)
    private Doctor[]  doctors;
    private Patient[] patients;
    private Nurse[]   nurses;

    // Counters to track how many are added
    private int doctorCount;
    private int patientCount;
    private int nurseCount;

    // Constructor
    public Hospital(String name, int maxDoctors, int maxPatients, int maxNurses) {
        this.hospitalName = name;
        this.doctors      = new Doctor[maxDoctors];
        this.patients     = new Patient[maxPatients];
        this.nurses       = new Nurse[maxNurses];
        this.doctorCount  = 0;
        this.patientCount = 0;
        this.nurseCount   = 0;
    }

    // Add a Doctor
    public void addDoctor(Doctor d) {
        if (doctorCount < doctors.length) {
            doctors[doctorCount] = d;
            doctorCount++;
            System.out.println(">> Doctor added: " + d.getName());
        } else {
            System.out.println(">> No space for more doctors.");
        }
    }

    // Add a Patient
    public void addPatient(Patient p) {
        if (patientCount < patients.length) {
            patients[patientCount] = p;
            patientCount++;
            System.out.println(">> Patient admitted: " + p.getName());
        } else {
            System.out.println(">> No beds available.");
        }
    }

    // Add a Nurse
    public void addNurse(Nurse n) {
        if (nurseCount < nurses.length) {
            nurses[nurseCount] = n;
            nurseCount++;
            System.out.println(">> Nurse added: " + n.getName());
        } else {
            System.out.println(">> No space for more nurses.");
        }
    }

    // Display all doctors — for loop
    public void showAllDoctors() {
        System.out.println("\n========== DOCTORS ==========");
        if (doctorCount == 0) {
            System.out.println("No doctors registered.");
            return;
        }
        for (int i = 0; i < doctorCount; i++) {
            doctors[i].displayInfo();    // Polymorphism — calls Doctor's version
        }
    }

    // Display all patients — for loop
    public void showAllPatients() {
        System.out.println("\n========== PATIENTS ==========");
        if (patientCount == 0) {
            System.out.println("No patients admitted.");
            return;
        }
        for (int i = 0; i < patientCount; i++) {
            patients[i].displayInfo();   // Polymorphism — calls Patient's version
        }
    }

    // Display all nurses — for loop
    public void showAllNurses() {
        System.out.println("\n========== NURSES ==========");
        if (nurseCount == 0) {
            System.out.println("No nurses registered.");
            return;
        }
        for (int i = 0; i < nurseCount; i++) {
            nurses[i].displayInfo();     // Polymorphism — calls Nurse's version
        }
    }

    // Search patient by name — if-else inside loop
    public void searchPatient(String searchName) {
        System.out.println("\n>> Searching for patient: " + searchName);
        boolean found = false;
        for (int i = 0; i < patientCount; i++) {
            if (patients[i].getName().equalsIgnoreCase(searchName)) {
                patients[i].displayInfo();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(">> Patient not found.");
        }
    }

    // Total bill of all patients — for loop + addition
    public void showTotalRevenue() {
        double total = 0;
        for (int i = 0; i < patientCount; i++) {
            total = total + patients[i].calculateBill();
        }
        System.out.println("\n>> Total Hospital Revenue: Rs." + total);
    }

    // POLYMORPHISM DEMO:
    // Store all as Person[] — call displayInfo() on each
    // Java picks the correct overridden version at runtime
    public void showAllPersons() {
        System.out.println("\n===== ALL PERSONS (Polymorphism Demo) =====");

        // Create a Person array mixing all types
        Person[] all = new Person[doctorCount + patientCount + nurseCount];
        int index = 0;

        for (int i = 0; i < doctorCount;  i++) { all[index++] = doctors[i];  }
        for (int i = 0; i < patientCount; i++) { all[index++] = patients[i]; }
        for (int i = 0; i < nurseCount;   i++) { all[index++] = nurses[i];   }

        for (int i = 0; i < all.length; i++) {
            // Same call — different behavior based on actual object type
            System.out.println("[" + all[i].getRole() + "] " + all[i].getName());
        }
    }

    public String getHospitalName() { return hospitalName; }
}

// ─────────────────────────────────────────────────────────────
// MAIN CLASS — entry point with menu using Scanner + switch
// ─────────────────────────────────────────────────────────────
public class HospitalManagementSystem {

    public static void main(String[] args) {

        // Create hospital
        Hospital hospital = new Hospital("Java OOP Hospital", 5, 5, 5);

        // Pre-load sample data
        loadSampleData(hospital);

        // Scanner for user input
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        System.out.println("\n╔═══════════════════════════════════╗");
        System.out.println("║   HOSPITAL MANAGEMENT SYSTEM      ║");
        System.out.println("║   Java OOP: All 4 Concepts Used   ║");
        System.out.println("╚═══════════════════════════════════╝");

        // Loop until user exits
        while (choice != 8) {

            // Display menu
            System.out.println("\n-------- MENU --------");
            System.out.println("1. Show All Doctors");
            System.out.println("2. Show All Patients");
            System.out.println("3. Show All Nurses");
            System.out.println("4. Add New Patient");
            System.out.println("5. Search Patient by Name");
            System.out.println("6. Show Total Revenue");
            System.out.println("7. Polymorphism Demo (All Persons)");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            // Read integer input
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            // Switch to handle menu choices
            switch (choice) {

                case 1:
                    hospital.showAllDoctors();
                    break;

                case 2:
                    hospital.showAllPatients();
                    break;

                case 3:
                    hospital.showAllNurses();
                    break;

                case 4:
                    // Add new patient using Scanner input
                    System.out.print("Enter Patient ID   : "); String pid = sc.nextLine();
                    System.out.print("Enter Name         : "); String pname = sc.nextLine();
                    System.out.print("Enter Age          : "); int page = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Disease      : "); String pdis = sc.nextLine();
                    System.out.print("Days Admitted      : "); int pdays = sc.nextInt();
                    System.out.print("Bed Charge/Day Rs. : "); double pbed = sc.nextDouble();
                    System.out.print("Medicine Charge Rs.: "); double pmed = sc.nextDouble();
                    sc.nextLine();
                    Patient newPatient = new Patient(pid, pname, page, pdis, pdays, pbed, pmed);
                    hospital.addPatient(newPatient);
                    break;

                case 5:
                    System.out.print("Enter patient name to search: ");
                    String searchName = sc.nextLine();
                    hospital.searchPatient(searchName);
                    break;

                case 6:
                    hospital.showTotalRevenue();
                    break;

                case 7:
                    hospital.showAllPersons();
                    System.out.println("\n>> Same displayInfo() call on Person[]");
                    System.out.println(">> Java picks the right version at RUNTIME.");
                    System.out.println(">> That is POLYMORPHISM!");
                    break;

                case 8:
                    System.out.println("\n>> Exiting. Goodbye!");
                    break;

                default:
                    System.out.println(">> Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    // ── Helper: load sample data ──────────────────────────────
    static void loadSampleData(Hospital h) {

        // Doctors (Inheritance: Doctor extends Person)
        h.addDoctor(new Doctor("D001", "Dr. Arjun Mehta",  45, "Cardiology",   800.0));
        h.addDoctor(new Doctor("D002", "Dr. Priya Singh",  38, "Neurology",    900.0));
        h.addDoctor(new Doctor("D003", "Dr. Ramesh Kumar", 52, "Orthopedics",  750.0));

        // Patients (Inheritance: Patient extends Person, implements Treatable)
        h.addPatient(new Patient("P001", "Ravi Sharma",   34, "Fever",        3,  500.0,  300.0));
        h.addPatient(new Patient("P002", "Anita Patel",   60, "Heart Disease",7,  800.0, 2000.0));
        h.addPatient(new Patient("P003", "Suresh Rao",    28, "Fracture",     5,  600.0,  800.0));

        // Nurses (Inheritance: Nurse extends Person)
        h.addNurse(new Nurse("N001", "Kavita Nair",  27, "ICU Ward",    25000.0));
        h.addNurse(new Nurse("N002", "Meena Tiwari", 32, "General Ward",22000.0));

        System.out.println("\n>> Sample data loaded successfully!");
    }
}

// ════════════════════════════════════════════════════════════════
//  OOP CONCEPTS SUMMARY
// ════════════════════════════════════════════════════════════════
//
//  1. ENCAPSULATION
//     - All fields in Person, Doctor, Patient, Nurse are PRIVATE
//     - Accessed only via public getters/setters
//     - Example: private String name;  →  getName() / setName()
//
//  2. INHERITANCE
//     - Doctor   extends Person  (is-a Person)
//     - Patient  extends Person  (is-a Person)
//     - Nurse    extends Person  (is-a Person)
//     - Staff    extends Person  (is-a Person)
//     - All reuse id, name, age from Person via super()
//
//  3. POLYMORPHISM
//     - displayInfo() is overridden in each subclass
//     - getRole() is overridden in each subclass
//     - Person[] stores mix of Doctor/Patient/Nurse
//     - Calling displayInfo() on Person[] invokes the correct
//       subclass version at runtime  →  Runtime Polymorphism
//
//  4. ABSTRACTION
//     - Person is an abstract class — cannot be created directly
//     - displayInfo() and getRole() are abstract methods
//     - Forces every subclass to provide its own implementation
//     - Hides internal complexity from the user
//
// ════════════════════════════════════════════════════════════════