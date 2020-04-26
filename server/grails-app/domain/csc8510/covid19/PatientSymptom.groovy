package csc8510.covid19
import grails.rest.Resource

@Resource(uri='/patientSymptom', readOnly=true)
class PatientSymptom implements Serializable {

    Long patientId
    Long symptomId
    Integer symptomWeight

    static constraints = {
    }

    static mapping = {
        table 'PATIENT_SYMPTOMS'
        version false
        id column: 'PATIENT_SYMPTOMS_ID'
        patientId column: 'PATIENT_ID'
        symptomId column: 'SYMPTOM_ID'
        symptomWeight column: 'SYMPTOM_WEIGHT'
    }
}
