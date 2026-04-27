package model;

public class Customer {

    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email) {
        if (firstName == null || lastName == null || email == null) {
            throw new IllegalArgumentException("First name, last name, and email cannot be null.");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException(
                    "Your email id is not valid.(make sure it contains @domain.com ) Please enter agian");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    public final String getFirstName() {
        return firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public final String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer\n"
                + "First name: " + firstName + "\n"
                + "Last name: " + lastName + "\n"
                + "Email: " + email + "\n";
    }
}
