package csc8510.covid19

class HealthProviderPrompt {
    def question
    HealthProviderPromptAnswer[] answers
    def questionType
    def id

    HealthProviderPrompt(question, answers) {
        this.question = question
        this.answers = answers
    }

    def getQuestion() {
        return question
    }

    void setQuestion(question) {
        this.question = question
    }

    def getAnswers() {
        return answers
    }

    void setAnswers(answers) {
        this.answers = answers
    }
}
