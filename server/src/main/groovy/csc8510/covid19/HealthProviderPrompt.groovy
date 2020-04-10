package csc8510.covid19

class HealthProviderPrompt {
    def question
    ArrayList<HealthProviderPromptAnswer> answers

    HealthProviderPrompt(question, ArrayList<HealthProviderPromptAnswer> answers) {
        this.question = question
        this.answers = answers
    }

    def getQuestion() {
        return question
    }

    void setQuestion(question) {
        this.question = question
    }

    ArrayList<HealthProviderPromptAnswer> getAnswers() {
        return answers
    }

    void setAnswers(ArrayList<HealthProviderPromptAnswer> answers) {
        this.answers = answers
    }
}
