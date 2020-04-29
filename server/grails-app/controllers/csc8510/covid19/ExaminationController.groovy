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
            def patientData = json ? json : params
            def patientId = savePatientAndReturnIdOnSuccess(patientData)
            savePatientSymptoms(patientData, patientId)

            def successJson = [message: "Success"]
            render successJson as JSON
        }
        catch (Exception e) {
            println(e.getStackTrace())
            def failureJson = [message: "Failure: " + e.getMessage()]
            render failureJson as JSON
        }
    }

    private static def savePatientAndReturnIdOnSuccess(params) {
        Patient patient = new Patient()
        patient.firstName = params.firstName
        patient.lastName = params.lastName
        patient.age = Integer.valueOf(params.age)
        patient.gender = params.gender
        patient.quantity = params.quantity ? params.quantity : 1
        patient.save()
        return patient.id
    }

    private static void saveSymptom(symptom, patientId) {
        //We only want to add this patient's symptoms if they exist in the Symptoms table.
        if (Symptom.findByName(symptom instanceof String ? symptom : symptom.name)) {
            def symptomId = Symptom.findByName(symptom instanceof String ? symptom : symptom.name).id
            PatientSymptom patientSymptom = new PatientSymptom()
            patientSymptom.patientId = patientId
            patientSymptom.symptomId = symptomId
            patientSymptom.symptomWeight = symptom instanceof String ? 1 : symptom.weight
            patientSymptom.save()
        }
    }

    private static def savePatientSymptoms(params, patientId) {
        if (params.symptoms instanceof String) {
            saveSymptom(params.symptoms, patientId)
        } else {
            params.symptoms.each { symptom ->
                saveSymptom(symptom, patientId)
            }
        }
    }
}
