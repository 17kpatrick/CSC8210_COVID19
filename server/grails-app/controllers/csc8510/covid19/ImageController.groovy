package csc8510.covid19

import grails.converters.JSON
import groovy.sql.Sql
import org.grails.core.io.ResourceLocator
import org.springframework.core.io.Resource

class ImageController {
    static responseFormats = ['json', 'xml']
    def dataSource
    ResourceLocator grailsResourceLocator

    def index() {}

    def getDoubleSeriesRadarChart() {
        def outputStream = response.outputStream
        response.setContentType("image/jpeg")
        try {
            def allSymptomsMap = getSqlQueryResults()
            def patientSymptoms = params?.symptoms?.split(',')

            def symptomComparisonCalculator = new SymptomComparisonCalculator(params.illness, patientSymptoms)

            symptomComparisonCalculator.getDoubleSeriesRadarChart(allSymptomsMap, outputStream)
        }
        catch (Exception e) {
            println(e.getStackTrace())
            def failureJson = [message: "Failure: " + e.getMessage()]
            render failureJson as JSON
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close()
                } catch (IOException e) {
                    println(e.getStackTrace())
                    def failureJson = [message: "Failure: " + e.getMessage()]
                    render failureJson as JSON
                }
            }
        }
    }

    def getPatientSymptomsRadarChart() {
        def outputStream = response.outputStream
        response.setContentType("image/jpeg")
        try {
            def allSymptomsMap = getSqlQueryResults()
            def patientSymptoms = params?.symptoms?.split(',')

            def symptomComparisonCalculator = new SymptomComparisonCalculator(params.illness, patientSymptoms)

            symptomComparisonCalculator.getPatientSymptomRadarChart(allSymptomsMap, outputStream)
        }
        catch (Exception e) {
            println(e.getStackTrace())
            def failureJson = [message: "Failure: " + e.getMessage()]
            render failureJson as JSON
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close()
                } catch (IOException e) {
                    println(e.getStackTrace())
                    def failureJson = [message: "Failure: " + e.getMessage()]
                    render failureJson as JSON
                }
            }
        }
    }

    def getAllSymptomsRadarChart() {

        def outputStream = response.outputStream
        response.setContentType("image/jpeg")
        try {
            def allSymptomsMap = getSqlQueryResults()
            def patientSymptoms = params?.symptoms?.split(',')

            def symptomComparisonCalculator = new SymptomComparisonCalculator(params.illness, patientSymptoms)

            symptomComparisonCalculator.getAllSymptomRadarChart(allSymptomsMap, outputStream)
        }
        catch (Exception e) {
            println(e.getStackTrace())
            def failureJson = [message: "Failure: " + e.getMessage()]
            render failureJson as JSON
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close()
                } catch (IOException e) {
                    println(e.getStackTrace())
                    def failureJson = [message: "Failure: " + e.getMessage()]
                    render failureJson as JSON
                }
            }
        }
    }

    private def getSqlQueryResults() {
        try {
            def sql = new Sql(dataSource)
            def sqlStr = "SELECT SYMPTOM_NAME, (SUM(SYMPTOM_WEIGHT) * 1.0) / (SELECT SUM(QUANTITY) FROM PATIENT) AS 'PROPORTION' FROM PATIENT_SYMPTOMS NATURAL JOIN SYMPTOMS GROUP BY SYMPTOM_ID"
            def list = sql.rows(sqlStr)
            def allSymptomsMap = [:]
            list.each {
                allSymptomsMap[it.SYMPTOM_NAME] = it.PROPORTION
            }
            return allSymptomsMap

        }
        catch (Exception e) {
            throw e
        }

    }
}
