package csc8510.covid19

import grails.converters.*

class ExaminationController {
	static responseFormats = ['json', 'xml']

    def index() { }

    def getQuestions(){
        def examinationQuestionSet = ExaminationQuestionSet.getInstance().getQuestionSet()
        render examinationQuestionSet as JSON
    }
}
