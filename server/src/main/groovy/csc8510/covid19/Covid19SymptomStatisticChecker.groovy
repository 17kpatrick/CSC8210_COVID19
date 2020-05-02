package csc8510.covid19

import com.opencsv.CSVReader

import org.knowm.xchart.RadarChart
import org.knowm.xchart.RadarChartBuilder
import org.knowm.xchart.BitmapEncoder
import org.knowm.xchart.BitmapEncoder.BitmapFormat
import org.knowm.xchart.style.Styler.LegendPosition

class Covid19SymptomStatisticChecker extends SymptomStatisticChecker {

    private def symptomMap = [:]
    private final def chartWidth = 900
    private final def chartHeight = 900

    Covid19SymptomStatisticChecker() {
        this.readStatisticsFile()
    }

    private def readStatisticsFile() {

        String symptomsCSV = "src\\main\\groovy\\csc8510\\covid19\\questionData\\Covid-19 Symptom Gathering Tool.csv"
        new File(symptomsCSV).withReader {
            reader ->
                CSVReader csvReader = new CSVReader(reader)
                csvReader.readNext()
                String[] csvFieldNames = csvReader.readNext()

                def firstIDX = 14
                def lastIDX = 34

                for (int i = firstIDX; i <= lastIDX; i++) {
                    symptomMap[csvFieldNames[i]] = 0
                }
                def len = 0
                csvReader.each { fieldValuesByNumber ->

                    for (int i = firstIDX; i <= lastIDX; i++) {
                        if (!fieldValuesByNumber[i].trim().isEmpty())
                            symptomMap[csvFieldNames[i]]++
                    }
                    len++
                }
                for (int i = firstIDX; i <= lastIDX; i++) {
                    symptomMap[csvFieldNames[i]] /= len
                }
        }

    }

    private def generateSingleSeriesRadarChart(symptoms, weights, seriesLbl, titleLbl, outputStream) {

        RadarChart chart = new RadarChartBuilder().width(chartWidth).height(chartHeight).title(titleLbl).build();
        chart.setVariableLabels(symptoms)
        chart.getStyler().setLegendPosition(LegendPosition.InsideSW);

        chart.addSeries(seriesLbl, weights)

        BitmapEncoder.saveBitmap(chart, outputStream, BitmapFormat.JPG)
    }

    private def generateDoubleSeriesRadarChart(symptoms, weightsSeries1, seriesLbl1, weightsSeries2, seriesLbl2, titleLbl, outputStream) {

        RadarChart chart = new RadarChartBuilder().width(chartWidth).height(chartHeight).title(titleLbl).build();
        chart.setVariableLabels(symptoms)
        chart.getStyler().setLegendPosition(LegendPosition.InsideSW);

        chart.addSeries(seriesLbl1, weightsSeries1)
        chart.addSeries(seriesLbl2, weightsSeries2)

        BitmapEncoder.saveBitmap(chart, outputStream, BitmapFormat.JPG)
    }

    def getAllSymptomsRadarChart(allSymptomsMap, outputStream, seriesLbl) {

        generateSingleSeriesRadarChart(allSymptomsMap.keySet() as String[], allSymptomsMap.values() as double[],
                seriesLbl, 'COVID-19 Symptoms Radar Chart', outputStream)
    }

    def getPatientSymptomsRadarChart(allSymptomsMap, patientSymptoms, outputStream, seriesLbl) {

        def patientSymptomsMap = allSymptomsMap.clone()

        patientSymptomsMap.each {
            if (!(it.key.trim().toLowerCase() in patientSymptoms.collect { it.trim().toLowerCase() }))
                it.value = 0.0
        }

        generateSingleSeriesRadarChart(patientSymptomsMap.keySet() as String[], patientSymptomsMap.values() as double[],
                seriesLbl, 'COVID-19 Symptoms Radar Chart', outputStream)
    }

    def getDoubleSeriesRadarChart(allSymptomsMap, patientSymptoms, outputStream, seriesLbl1, seriesLbl2) {

        def patientSymptomsMap = allSymptomsMap.clone()

        patientSymptomsMap.each {
            if (!(it.key.trim().toLowerCase() in patientSymptoms.collect { it.trim().toLowerCase() }))
                it.value = 0.0
        }

        generateDoubleSeriesRadarChart(allSymptomsMap.keySet() as String[], allSymptomsMap.values() as double[],
                seriesLbl1, patientSymptomsMap.values() as double[], seriesLbl2, 'COVID-19 Symptoms Radar Chart', outputStream)
    }

    def getRiskScore(allSymptomsMap, patientSymptoms) {

        def returnedMap = [:]
        returnedMap['maxPossibleSymptomScore'] = allSymptomsMap.values().sum()

        def yourPatientSymptomsMap = allSymptomsMap.findAll {
            it.key.trim().toLowerCase() in patientSymptoms.collect { it.trim().toLowerCase() }
        }
        returnedMap['yourPatientSymptomScore'] = yourPatientSymptomsMap.values().sum()

        returnedMap['yourPatientSymptomsList'] = yourPatientSymptomsMap.collect { symptom, prop -> [(symptom): prop] }

        returnedMap['finalRiskScore'] = returnedMap.yourPatientSymptomScore / returnedMap.maxPossibleSymptomScore

        returnedMap
    }

    @Override
    def getStatistics() {
        return symptomMap
    }

}
