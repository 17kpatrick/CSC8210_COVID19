package csc8510.covid19

class Patient {
    String firstName
    String lastName
    Integer age
    String gender
    Integer quantity

    static constraints = {
    }

    static mapping = {
        table 'PATIENT'
        version false
        id column: 'PATIENT_ID'
        firstName column: 'FIRST_NAME'
        lastName column: 'LAST_NAME'
        age column: 'AGE'
        gender column: 'GENDER'
        quantity column: 'QUANTITY'
    }
}
