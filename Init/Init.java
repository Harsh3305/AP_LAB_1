package Init;

import java.io.IOException;
import java.util.ArrayList;

import Hospital.Hospital;
import Patient.Patient;
import Reader.Reader;

public class Init {
    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        int NumberOfPatient = Reader.nextInt();
        
        while (NumberOfPatient --!= 0) {
            String Name = Reader.next();
            double temp = Reader.nextDouble();
            int OxygenLevel = Reader.nextInt();
            int Age = Reader.nextInt();
            Patient P = Patient.getInstance(OxygenLevel, temp, Name, Age);

        }


        // addHospital1();

        startReading();
        



    }


    public static void addHospital1() throws IOException {
        
        String Name = Reader.next();
        System.out.println(Name);
        
        System.out.print("Temperature Criteria – ");
        double temp = Reader.nextDouble();
        

        
        System.out.print("Oxygen Levels – ");
        int Oxy = Reader.nextInt();

        
        System.out.print(
            "Number of Available beds – "
        );
        int beds = Reader.nextInt();


        Hospital hospital = Hospital.getInstance(Name, temp, Oxy, beds);

        System.out.println("Temperature should be <= " + hospital.getTempratureCritaria());
        System.out.println("Oxygen levels should be >= " + hospital.getOxygenLevel());
        System.out.println("Number of Available beds – " + hospital.getNumberOfBedsAvailable());
        System.out.println("Admission Status – " + (hospital.isFull() == true ? "CLOSED":"OPENED"));

        ArrayList<Patient> list = Patient.Active;
        if (hospital !=null) {
            for (int i = 0; i < list.size(); i++) {
                if (hospital.isFull()) {
                    break;
                }

                else if (! list.get(i).isDelete() && list.get(i).getHospital() == null) {
                    if (hospital.isInsert(list.get(i))) {
                        hospital.addPatient(list.get(i));
                        list.get(i).addHospital(hospital);
                        System.out.print("Recovery days for admitted patient ID - " + list.get(i).getID() + " ");
                        int days = Reader.nextInt();
                        list.get(i).setRecoveryDays(days);
                    }
                }
            }
        }


    }

    public static void removedPatients2() {
        Hospital.deleteAllPatient();
    }


    public static void addHospital3() {
        // System.out.println("Accounts removed of Institute whose admission is closed");
        Hospital.deleteAllHospital();
    }

    public static void getPatientNotAddmitted4() {
        ArrayList<Patient> L = Patient.Active;
        int ans = 0;
        for (int i = 0; i < L.size(); i++) {
            if (L.get(i).getHospital() == null) {
                ans++;
            }
        }

        System.out.println(ans + " patients");

    }

    public static void getOpenHospital5() {
        Hospital.getOpenHospital();
    }

    public static void displayHospitalInfo6(String Name) {
        Hospital.getInfo(Name); // TODO: Wrong
    }

    public static void displayPatientInfo7(int ID) {
        Patient.getInfo(ID); // TODO: When deleted, then also shows Info :( Wrong
    }

    public static void displayAllPatient8() {
        Patient.displayAllPatient();
    }

    public static void displayHostipalAllpatientWithTime9() throws IOException {
        String Name = Reader.next();

        Hospital.name(Name);

    }

    private static void printError(Exception e) {
        System.out.println(e.toString() + " => " + e.getMessage());
    }

    public static void startReading() {
        try {


            while (! Patient.isAllPatientAdmitted()) {
                int command = Reader.nextInt();
    
                if (command == 1) {
                    addHospital1();
                }
                if (command == 2) {
                    removedPatients2();
                }
                if (command == 3) {
                    addHospital3();
                }
                if (command == 4) {
                    getPatientNotAddmitted4() ;
                }
                if (command == 5) {
                    getOpenHospital5();
                }
                if (command == 6) {
                    String Name = Reader.next();
                    displayHospitalInfo6(Name);
                }
                if (command == 7) {
                    int ID = Reader.nextInt();
                    displayPatientInfo7(ID);
                }
    
                if (command == 8) {
                    displayAllPatient8();
                }
                if (command == 9) {
                    displayHostipalAllpatientWithTime9();
                }
            }
        } catch (Exception e) {
            printError(e);
        }
    }
}