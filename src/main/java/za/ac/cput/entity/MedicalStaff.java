package za.ac.cput.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MedicalStaff {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int checkupsDone;
    private int fitnessTestsDone;
    private int medicalEmergenciesDone;

    public static class Builder {
        private int mediStaffID;
        private String mediStaffFirstName;
        private String mediStaffLastName;
        private String phoneNumber;
        private String email;

        public Builder(MedicalStaff ms) {
            this.mediStaffID = ms.id;
            this.mediStaffFirstName = ms.firstName;
            this.mediStaffLastName = ms.lastName;
            this.phoneNumber = ms.phoneNumber;
            this.email = ms.email;
        }

        public Builder(int mediStaffID) {
            this.mediStaffID = mediStaffID;
        }

        public Builder setFirstName(String firstName)
        {
            mediStaffFirstName = firstName;

            return this;
        }

        public Builder setLastName(String lastName)
        {
            mediStaffLastName = lastName;

            return this;
        }

        public Builder setPhoneNumber(String phoneNumber)
        {
            this.phoneNumber = phoneNumber;

            return this;
        }

        public Builder setEmail(String email)
        {
            this.email = email;

            return this;
        }

        public MedicalStaff build()
        {
            MedicalStaff ms = new MedicalStaff();
            ms.id = this.mediStaffID;
            ms.firstName = this.mediStaffFirstName;
            ms.lastName = this.mediStaffLastName;
            ms.phoneNumber = this.phoneNumber;
            ms.email = this.email;
            ms.checkupsDone = 0;
            ms.fitnessTestsDone = 0;
            ms.medicalEmergenciesDone = 0;
            return ms;
        }
    }

    public MedicalStaff() {}

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCheckupsDone() {
        return checkupsDone;
    }

    public int getFitnessTestsDone() {
        return fitnessTestsDone;
    }

    public int getMedicalEmergenciesDone() {
        return medicalEmergenciesDone;
    }
}
