package za.ac.cput.entity;

public class MedicalStaff {

    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int checkupsDone;
    private int fitnessTestsDone;
    private int medicalEmergenciesDone;

    public static class Builder {
        private final int id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;

        public Builder(MedicalStaff ms) {
            this.id = ms.id;
            this.firstName = ms.firstName;
            this.lastName = ms.lastName;
            this.phoneNumber = ms.phoneNumber;
            this.email = ms.email;
        }

        public Builder(int id) {
            this.id = id;
        }

        public Builder setFirstName(String firstName)
        {
            this.firstName = firstName;

            return this;
        }

        public Builder setLastName(String lastName)
        {
            this.lastName = lastName;

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
            ms.id = this.id;
            ms.firstName = this.firstName;
            ms.lastName = this.lastName;
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

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "MedicalStaff{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", checkupsDone=" + checkupsDone +
                ", fitnessTestsDone=" + fitnessTestsDone +
                ", medicalEmergenciesDone=" + medicalEmergenciesDone +
                '}';
    }
}
