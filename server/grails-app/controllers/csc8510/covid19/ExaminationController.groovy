package csc8510.covid19

import grails.converters.*

class ExaminationController {
	static responseFormats = ['json', 'xml']
	
    def index() { }

    def getQuestions(){
        def examinationQuestionSet = ExaminationQuestionSet.getInstance().getQuestionSet()

        def questionTest = examinationQuestionSet.get(0).getQuestion()
        def answerTest = examinationQuestionSet.get(0).getAnswers().get(0).getAnswer()
        def map = [question: questionTest, answer: answerTest]

        render map as JSON
    }
}
