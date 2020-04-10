package csc8510.covid19

class HealthProviderPromptAnswer {
    def answer
    def riskScore

    HealthProviderPromptAnswer(answer, riskScore) {
        this.answer = answer
        this.riskScore = riskScore
    }

    def getAnswer() {
        return answer
    }

    void setAnswer(answer) {
        this.answer = answer
    }

    def getRiskScore() {
        return riskScore
    }

    void setRiskScore(riskScore) {
        this.riskScore = riskScore
    }
}
