package csc8510.covid19

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        get "/examination/getQuestions"(controller: 'examination', action: 'getQuestions')

        //http://localhost:8080/examination/getPatientSimilarities?illness=COVID19&symptoms=symptom1,symptom2,symptom3
        get "/examination/getPatientSimilarities?${illness}?&?${symptoms}?"(controller: 'examination', action: 'getSimilarities')


        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
