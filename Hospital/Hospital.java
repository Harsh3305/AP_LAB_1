package Hospital;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import Patient.Patient;

public class Hospital {
    private final String Name;
    private final double TempratureCritaria;
    private final int OxygenLevel;
    private final int NumberOfBedsAvailable;

    private int NumberOfPacient;

    private static HashSet<Hospital> set = new HashSet<>();
    private static HashMap<Hospital , ArrayList<Patient>> mapOfPatient = new HashMap<>();
////////////////////////////////////
    private Hospital(String name, double tempratureCritaria, int oxygenLevel, int numberOfBedsAvailable) {
        Name = name;
        TempratureCritaria = tempratureCritaria;
        OxygenLevel = oxygenLevel;
        NumberOfBedsAvailable = numberOfBedsAvailable;
        // NumberOfPacient = numberOfPacient;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Name == null) ? 0 : Name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hospital other = (Hospital) obj;
        if (Name == null) {
            if (other.Name != null)
                return false;
        } else if (!Name.equals(other.Name))
            return false;
        return true;
    }

    public String getName() {
        return Name;
    }

    public double getTempratureCritaria() {
        return TempratureCritaria;
    }

    public int getOxygenLevel() {
        return OxygenLevel;
    }

    public int getNumberOfBedsAvailable() {
        return NumberOfBedsAvailable;
    }

    // public int getNumberOfPacient() {
    //     return NumberOfPacient;
    // }

    // public void setNumberOfPacient(int numberOfPacient) {
    //     NumberOfPacient = numberOfPacient;
    // }


    public static Hospital getInstance(String name, double tempratureCritaria, int oxygenLevel, int numberOfBedsAvailable) {

        Hospital H= new Hospital(name, tempratureCritaria, oxygenLevel, numberOfBedsAvailable);
        if (set.contains(H)) {
            H = null;
        }
        else {
            set.add(H);
        }

        return H;
    }

////////////////////////////////////
    public void deleteHospital () {
        ArrayList<Patient> L = mapOfPatient.get(this);
        if (L != null) {
            for (int i = 0; i < L.size(); i++) {
                Patient P = L.get(i);
                P.delete();
            }
        }
        set.remove(this);
        mapOfPatient.remove(this);
    }

    public void deletePatient(Patient P) {
        ArrayList<Patient> L = mapOfPatient.get(this);
        if (L != null) {
            L.remove(P);
        }
    }

    public void addPatient (Patient P) {
        ArrayList<Patient> L = mapOfPatient.remove(this);
        if (L == null) {
            L = new ArrayList<>();
        }
        L.add(P);
        NumberOfPacient++;
        mapOfPatient.put(this, L);
    }


    public boolean isFull () {
        if (NumberOfBedsAvailable >= NumberOfPacient) {
            return false;
        }
        return true;
    }


    public boolean isInsert(Patient P) {
        if (P.getOxygenLevel() < OxygenLevel) {
            if (P.getTemprature() >= TempratureCritaria) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }   

    public static  void deleteAllPatient () {
        Iterator<Hospital> it = set.iterator();

        System.out.println("Account ID removed of admitted patients");

        while (it.hasNext()) {
            Hospital H = it.next();
            ArrayList<Patient> L = mapOfPatient.get(H);
            if (L != null) {
                for (int i = 0; i < L.size(); i++) {
                    L.get(i).deleteAndDisplay();
                }
            }
        }
    }

    public static void deleteAllHospital() {
        Iterator<Hospital> it = set.iterator();
        System.out.println("Accounts removed of Institute whose admission is closed");
        while (it.hasNext()) {
            Hospital H = it.next();
            System.out.println(H.Name);
            H.deleteHospital();
        }
    }

    public static void getOpenHospital() {
        int ans = 0;
        Iterator<Hospital> it = set.iterator();
        while (it.hasNext()) {
            Hospital H = it.next();
            if (! H.isFull()){ 
                ans++;
            }
            
        }

        System.out.println(ans + " institutes are admitting patients currently");
    }

    public static void getInfo(String Name) {
        Iterator<Hospital> it = set.iterator();
        while (it.hasNext()) {
            Hospital H = it.next();
            if (H.getName().equals(Name)) {
                System.out.println(Name);
                System.out.println("Temperature should be <= " + H.getTempratureCritaria());
                System.out.println("Oxygen levels should be >= " + H.getOxygenLevel());
                System.out.println("Number of Available beds – " + (H.getNumberOfBedsAvailable() - H.NumberOfPacient));
                System.out.println("Admission Status – " + (H.isFull()==true ? "CLOSED":"OPENED"));
            }
        }
    }

    public static void name(String Name) {
        Iterator<Hospital> it = set.iterator();
        while (it.hasNext()) {
            Hospital H = it.next();
            if (H.getName().equals(Name)) {
                ArrayList<Patient> L = mapOfPatient.get(H);
                if (L != null) {
                    for (int i = 0; i < L.size(); i++) {
                        Patient P = L.get(i);
                        int d = P.getRecoveryDays();
                        if (d != -1) {
                            System.out.println(P.getName() + ", recovery time is " + d+" days");
                        }
                        else {
                            System.out.println(P.getName() + ", recovery time is NA days");
                        }
                    }
                }
                break;
            }
        }
    }

}
