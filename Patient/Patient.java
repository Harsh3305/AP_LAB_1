package Patient;

import java.util.ArrayList;
import java.util.Iterator;

import Hospital.Hospital;

public class Patient {
    private int ID;
    private int OxygenLevel;
    private double Temprature;
    private String Name;
    private int age;
    private Hospital hospital;
    private static int id = 1;
    private boolean delete = false;
    private int recoveryDays;

    // private static  HashSet<Patient> setOfPatient = new HashSet<>();
    // public static HashSet<Patient> Removed = new HashSet<>();
    // public static HashSet<Patient> Active = new HashSet<>();

    private static  ArrayList<Patient> setOfPatient = new ArrayList<>();
    public static ArrayList<Patient> Removed = new ArrayList<>();
    public static ArrayList<Patient> Active = new ArrayList<>();


    public static  Patient getInstance(int oxygenLevel, double temprature, String name, int age) {
        Patient P = new Patient(oxygenLevel, temprature, name, age);

        if (setOfPatient.contains(P)) {
            P = null;
        }
        else {
            Active.add(P);
            setOfPatient.add(P);
        }

        return P;
    }

    private Patient(int oxygenLevel, double temprature, String name, int age) {
        OxygenLevel = oxygenLevel;
        Temprature = temprature;
        Name = name;
        this.age = age;
        ID = id;
        id++;
    }


    public void delete () {
        if (delete) {
            return;
        }
        delete = true;
        hospital.deletePatient(this);
        Removed.add(this);
        Active.remove(this);
    }

    public boolean isDelete() {
        return delete;
    }

    public void addHospital(Hospital H) {
        hospital = H;
        H.addPatient(this);
    }

////////////////////////////////////
    public int getID() {
        return ID;
    }

    public int getOxygenLevel() {
        return OxygenLevel;
    }

    public void setOxygenLevel(int oxygenLevel) {
        OxygenLevel = oxygenLevel;
    }

    public double getTemprature() {
        return Temprature;
    }

    public void setTemprature(double temprature) {
        Temprature = temprature;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public int getRecoveryDays() {
        return recoveryDays;
    }

    public void setRecoveryDays(int recoveryDays) {
        this.recoveryDays = recoveryDays;
    }
////////////////////////////////////

    public static ArrayList<Patient> getActiveArrayList() {
        return Active;
    }

    public static ArrayList<Patient> getRemovedArrayList () {
        return Removed;
    }
 
    public void deleteAndDisplay() {
        System.out.println(ID);
        delete();
    }

    public static void getInfo(int ID) {
        Iterator<Patient> it = setOfPatient.iterator();

        while (it.hasNext()){
            Patient P = it.next();
            if (P.ID == ID) {
                System.out.println(P.getName());
                System.out.println("Temperature is " + P.getTemprature());
                System.out.println("Oxygen levels is " + P.getOxygenLevel());
                System.out.println("Admission Status – " + (P.getHospital() == null ? "Not Admitted":"Admitted"));
            }
        }
    }

    public static void displayAllPatient() {
        for (int i = 0; i < setOfPatient.size(); i++) {
            Patient P = setOfPatient.get(i);

            System.out.println(P.ID + " " + P.Name);
        }
    }

    public static boolean isAllPatientAdmitted() {
        
        for (int i = 0; i < setOfPatient.size(); i++) {
            Patient P = setOfPatient.get(i);
            if (P.hospital == null) {
                return false;
            }
        }

        return true;

    }
}
