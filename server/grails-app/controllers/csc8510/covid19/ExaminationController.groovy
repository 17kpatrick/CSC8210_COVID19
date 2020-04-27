package csc8510.covid19

import grails.converters.*
import groovy.sql.Sql

class ExaminationController {
    static responseFormats = ['json', 'xml']
    def dataSource

    def index() {}

    def getQuestions() {
        def examinationQuestionSet = ExaminationQuestionSet.getInstance().getQuestionSet()
        render examinationQuestionSet as JSON
    }

    def getSimilarities() {
        def patientSymptoms = params?.symptoms?.split(',')
        def symptomComparisonCalculator = new SymptomComparisonCalculator(params.illness, patientSymptoms)
        def returnedMap = symptomComparisonCalculator.getSymptomSimilarities()

        render returnedMap as JSON
    }

    def getRiskScore() {

        def sql = new Sql(dataSource)
        def sqlStr = "SELECT SYMPTOM_NAME, (SUM(SYMPTOM_WEIGHT) * 1.0) / (SELECT SUM(QUANTITY) FROM PATIENT) AS 'PROPORTION' FROM PATIENT_SYMPTOMS NATURAL JOIN SYMPTOMS GROUP BY SYMPTOM_ID"
        def list = sql.rows(sqlStr)
        def allSymptomsMap = [:]
        list.each {
            allSymptomsMap[it.SYMPTOM_NAME] = it.PROPORTION
        }
        def patientSymptoms = params?.symptoms?.split(',')

        def symptomComparisonCalculator = new SymptomComparisonCalculator(params.illness, patientSymptoms)
        def returnedMap = symptomComparisonCalculator.getRiskScore(allSymptomsMap)

        render returnedMap as JSON
    }

    def addPatient() {
        try {
            def json = request.JSON
            def patientId = savePatientAndReturnIdOnSuccess(json)
            savePatientSymptoms(json, patientId)

            def successJson = [message: "Success"]
            render successJson as JSON
        }
        catch (Exception e) {
            println(e.getStackTrace())
            def failureJson = [message: "Failure: " + e.getMessage()]
            render failureJson as JSON
        }
    }

    private static def savePatientAndReturnIdOnSuccess(json) {
        Patient patient = new Patient()
        patient.firstName = json.firstName
        patient.lastName = json.lastName
        patient.age = json.age
        patient.gender = json.gender
        patient.quantity = json.quantity
        patient.save()
        return patient.id
    }

    private static def savePatientSymptoms(json, patientId) {
        json.symptoms.each { symptom ->
            //We only want to add this patient's symptoms if they exist in the Symptoms table.
            if (Symptom.findByName(symptom.name)) {
                def symptomId = Symptom.findByName(symptom.name).id
                PatientSymptom patientSymptom = new PatientSymptom()
                patientSymptom.patientId = patientId
                patientSymptom.symptomId = symptomId
                patientSymptom.symptomWeight = symptom.weight
                patientSymptom.save()
            }
        }
    }
}
