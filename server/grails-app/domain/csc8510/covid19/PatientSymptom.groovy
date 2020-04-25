package csc8510.covid19

class PatientSymptom {

    Long patientId
    Long symptomId
    Integer symptomWeight

    static constraints = {
    }

    static mapping = {
        table 'PATIENT_SYMPTOMS'
        id false
        version false
        patientId column: 'PATIENT_ID'
        symptomId column: 'SYMPTOM_ID'
        symptomWeight column: 'SYMPTOM_WEIGHT'
    }
}
