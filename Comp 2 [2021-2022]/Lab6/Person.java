public class Person {
    String firstName;
    String lastName;

    public Person() {
        firstName = "John";
        lastName = "Doe";
    }

    public Person(String f, String l) {
        firstName = f;
        lastName = l;
    }

    public void setName(String f, String l) {
        firstName = f;
        lastName = l;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void printLastFirst() {
        System.out.print(lastName + ", " + firstName + " ");
    }

    public void print() {
        System.out.print(firstName + " " + lastName);
    }

    public String toString() {
        return (firstName + " " + lastName);
    }

    public boolean equals(Object obj) {
        return (this == obj);
    }

    public void copy(Person p) {
        firstName = p.firstName;
        lastName = p.lastName;
    }

    public Person getCopy() {
        return new Person(firstName, lastName);
    }

}
