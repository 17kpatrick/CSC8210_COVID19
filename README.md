gradlew server:bootRun will start the server.

gradlew client:start will start the client.

GET '/patient', params: max (Specify max number of records to retrieve),
Retrieves patients from database.

GET '/patientSymptom', params: max (Specify max number of records to retrieve),
Retrieves patient symptoms table from database, which maps patients to their symptoms.

GET '/symptom', params: max (Specify max number of records to retrieve)
Retrieves symptoms from database.

GET '/examination/getQuestions'
Retrieves questions which are used on front-end to prompt health care provider.

GET '/examination/getPatientSimilarities' params: illness (i.e. COVID19), symptoms (List the symptoms that
the patient has separated by commas with no spaces.)
EXAMPLE: http://localhost:8080/examination/getPatientSimilarities?illness=COVID19&symptoms=symptom1,symptom2,symptom3

GET '/examination/getPatientRiskScore' params: illness (i.e. COVID19), symptoms (List the symptoms that
the patient has separated by commas with no spaces.)
EXAMPLE: http://localhost:8080/examination/getPatientRiskScore?illness=COVID19&symptoms=symptom1,symptom2,symptom3
Retrieves patient risk score based on data in database.

GET '/image/getDoubleSeriesRadarChart' params: illness (i.e. COVID19), symptoms (List symptoms that pertain to patient)
EXAMPLE 'http://localhost:8080/image/getDoubleSeriesRadarChart?illness=COVID19&symptoms=symptom1,symptom2,symptom3'
Generates radar chart of most common symptoms overlayed by patient symptoms.

GET '/image/getPatientSymptomsRadarChart' params: illness (i.e. COVID19), symptoms (List symptoms that pertain to patient)
EXAMPLE 'http://localhost:8080/image/getPatientSymptomsRadarChart?illness=COVID19&symptoms=symptom1,symptom2,symptom3'
Generates radar chart of patient symptoms.

GET '/image/getAllSymptomsRadarChart' params: illness (i.e. COVID19)
EXAMPLE 'http://localhost:8080/image/getAllSymptomsRadarChart?illness=COVID19'
Generates radar chart of most common symptoms.

GET '/image/getAllSymptomsBarChart' params: illness (i.e. COVID19)
EXAMPLE 'http://localhost:8080/image/getAllSymptomsBarChart?illness=COVID19'
Generates bar chart of most common symptoms.


POST '/examination/addPatient' params: JSON in the following format:

{
  "firstName": "Kyle",
  "lastName": "Patrick",
  "age": 24,
  "gender": "M",
  "quantity": 1,
  "symptoms": [
    {
      "name": "Fever",
      "weight": 1
    },
    {
      "name": "Chills",
      "weight": 1
    }
  ]
}
)

Adds a new patient to the database.
