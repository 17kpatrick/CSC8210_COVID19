package csc8510.covid19

import com.opencsv.CSVReader
import com.opencsv.CSVWriter

import com.xlson.groovycsv.CsvParser
import com.xlson.groovycsv.CsvIterator

class Covid19SymptomStatisticChecker extends SymptomStatisticChecker {

    private def symptomMap = [:]

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

    @Override
    def getStatistics() {
        return symptomMap
    }

}
