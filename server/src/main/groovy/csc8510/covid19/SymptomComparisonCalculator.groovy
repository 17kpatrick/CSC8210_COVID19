package csc8510.covid19

class SymptomComparisonCalculator {
    private def illness
    private def patientSymptoms
    private def illnessSymptoms

    SymptomComparisonCalculator(illness, patientSymptoms) {
        this.illness = illness
        this.patientSymptoms = patientSymptoms
    }

    def getRiskScore(allSymptoms) {
        def symptomStatisticChecker
        switch (illness.toUpperCase()) {
            case "COVID19":
                symptomStatisticChecker = new Covid19SymptomStatisticChecker()
                break
            default:
                symptomStatisticChecker = new SymptomStatisticChecker()
                break
        }

        symptomStatisticChecker.getRiskScore(allSymptoms, patientSymptoms);
    }

    def getAllSymptomRadarChart(allSymptoms, outputStream) {
        def symptomStatisticChecker
        switch (illness.toUpperCase()) {
            case "COVID19":
                symptomStatisticChecker = new Covid19SymptomStatisticChecker()
                break
            default:
                symptomStatisticChecker = new SymptomStatisticChecker()
                break
        }

        symptomStatisticChecker.getAllSymptomsRadarChart(allSymptoms, outputStream, "Aggregate Patient Symptoms")
    }

    def getAllSymptomBarChart(allSymptoms, outputStream) {
        def symptomStatisticChecker
        switch (illness.toUpperCase()) {
            case "COVID19":
                symptomStatisticChecker = new Covid19SymptomStatisticChecker()
                break
            default:
                symptomStatisticChecker = new SymptomStatisticChecker()
                break
        }

        symptomStatisticChecker.getAllSymptomsBarChart(allSymptoms, outputStream, "Aggregate Patient Symptoms")
    }

    def getPatientSymptomRadarChart(allSymptoms, outputStream) {
        def symptomStatisticChecker
        switch (illness.toUpperCase()) {
            case "COVID19":
                symptomStatisticChecker = new Covid19SymptomStatisticChecker()
                break
            default:
                symptomStatisticChecker = new SymptomStatisticChecker()
                break
        }

        symptomStatisticChecker.getPatientSymptomsRadarChart(allSymptoms, patientSymptoms, outputStream, "Your Patient Symptoms")
    }

    def getDoubleSeriesRadarChart(allSymptoms, outputStream) {
        def symptomStatisticChecker
        switch (illness.toUpperCase()) {
            case "COVID19":
                symptomStatisticChecker = new Covid19SymptomStatisticChecker()
                break
            default:
                symptomStatisticChecker = new SymptomStatisticChecker()
                break
        }

        symptomStatisticChecker.getDoubleSeriesRadarChart(allSymptoms, patientSymptoms, outputStream, "All Patient Symptoms", "Your Patient Symptoms")
    }

    def getSymptomSimilarities() {
        def symptomStatisticChecker
        switch (illness.toUpperCase()) {
            case "COVID19":
                symptomStatisticChecker = new Covid19SymptomStatisticChecker()
                break
            default:
                symptomStatisticChecker = new SymptomStatisticChecker()
                break
        }

        def symptomMap = symptomStatisticChecker.getStatistics()

        def returnedMap = [:]
        patientSymptoms.each { symptom ->
            def foundSymptomVal = symptomMap.find { it.key.trim().toLowerCase() == symptom.trim().toLowerCase() }?.value

            if (foundSymptomVal != null) {
                returnedMap[symptom] = foundSymptomVal
            }
        }
        return returnedMap
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
