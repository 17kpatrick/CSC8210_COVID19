package csc8510.covid19

class SymptomComparisonCalculator {
    private def illness
    private def patientSymptoms
    private def illnessSymptoms

    SymptomComparisonCalculator(illness, patientSymptoms) {
        this.illness = illness
        this.patientSymptoms = patientSymptoms
    }

    def getSymptomSimilarities(){
        def symptomStatisticChecker
        switch (illness.toUpperCase()){
            case "COVID19":
                symptomStatisticChecker = new Covid19SymptomStatisticChecker()
                break
            default:
                symptomStatisticChecker = new SymptomStatisticChecker()
                break
        }
    }

    def getIllness() {
        return illness
    }

    void setIllness(illness) {
        this.illness = illness
    }

    def getPatientSymptoms() {
        return patientSymptoms
    }

    void setPatientSymptoms(patientSymptoms) {
        this.patientSymptoms = patientSymptoms
    }

    def getIllnessSymptoms() {
        return illnessSymptoms
    }

    void setIllnessSymptoms(illnessSymptoms) {
        this.illnessSymptoms = illnessSymptoms
    }
}
