package csc8510.covid19

import grails.converters.*

class ExaminationController {
    static responseFormats = ['json', 'xml']

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
}
