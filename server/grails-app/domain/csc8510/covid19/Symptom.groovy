package csc8510.covid19
import grails.rest.Resource

@Resource(uri='/symptom', readOnly=true)
class Symptom {
    String name

    static constraints = {
    }

    static mapping = {
        table 'SYMPTOMS'
        version false
        id column: 'SYMPTOM_ID'
        name column: 'SYMPTOM_NAME'
    }
}
