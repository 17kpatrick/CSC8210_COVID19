import React, {Component} from 'react';
import {CLIENT_VERSION, REACT_VERSION, SERVER_URL} from './config';
import Score from "react-score-indicator";

export default class GetPatientRiskScore extends Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    state = {
        clientInfo: {
            version: CLIENT_VERSION,
            react: REACT_VERSION
        },
        patientData: {
            symptoms: ""
        },
        response: null
    };

    handleSubmit = (event) => {
        this.state.patientData.symptoms = "";

        for (let i = 0; i < event.target.length; i++) {
            if ((event.target[i].outerHTML.indexOf('checkbox') !== -1 && event.target[i].checked) || event.target[i].outerHTML.indexOf('checkbox') === -1 && event.target[i].value) {
                if (event.target[i].name === 'symptoms') {
                    this.state.patientData.symptoms = this.state.patientData.symptoms + (event.target[i].value + ",")
                }
            }
        }

        if (this.state.patientData.symptoms !== "") {
            this.state.patientData.symptoms = this.state.patientData.symptoms.substring(0, this.state.patientData.symptoms.length - 1)
        }

        fetch(SERVER_URL + '/examination/getPatientRiskScore?illness=COVID19&symptoms=' + this.state.patientData.symptoms)
            .then(r => r.json())
            .then(json => {
                if (json.message === "Failure") {
                    throw Error("An error occurred retrieving the patient's risk score. Please try again.")
                } else {
                    this.setState({
                        response: json
                    });
                    event.preventDefault();
                }
            })
            .catch(error => {
                alert(error);
                event.preventDefault();
            });

        event.preventDefault();
    };


    render() {
        if (this.state.response === null) {
            return (
                <div className='risk-score-container shadow-lg'>
                    <div className='prompt'>Select all of the symptoms that apply to your patient to calculate a risk score.</div>
                    <hr />
                    <form onSubmit={this.handleSubmit}>
                        <ul>
                            <li><label htmlFor='diarrhea'>Diarrhea</label><input id='diarrhea' type='checkbox' name='symptoms' value='Diarrhea' /></li>
                            <li><label htmlFor='conjuctival'>Conjunctival congestion</label><input id='conjuctival' type='checkbox' name='symptoms' value='Conjunctival congestion' /></li>
                            <li><label htmlFor='headache'>Headache</label><input id='headache' type='checkbox' name='symptoms' value='Headache' /></li>
                            <li><label htmlFor='sputnum'>Sputum production</label><input id='sputnum' type='checkbox' name='symptoms' value='Sputum production' /></li>
                            <li><label htmlFor='fatigue'>Fatigue</label><input id='fatigue' type='checkbox' name='symptoms' value='Fatigue' /></li>
                            <li><label htmlFor='hemptysis'>Hemoptysis</label><input id='hemptysis' type='checkbox' name='symptoms' value='Hemoptysis' /></li>
                            <li><label htmlFor='nausea'>Nausea or vomiting</label><input id='nausea' type='checkbox' name='symptoms' value='Nausea or vomiting' /></li>
                            <li><label htmlFor='myalgia'>Myalgia or arthralgia</label><input id='myalgia' type='checkbox' name='symptoms' value='Myalgia or arthralgia' /></li>
                            <li><label htmlFor='chills'>Chills</label><input id='chills' type='checkbox' name='symptoms' value='Chills' /></li>
                            <li><label htmlFor='throat'>Throat congestion</label><input id='throat' type='checkbox' name='symptoms' value='Throat congestion' /></li>
                            <li><label htmlFor='tonsil'>Tonsil swelling</label><input id='tonsil' type='checkbox' name='symptoms' value='Tonsil swelling' /></li>
                            <li><label htmlFor='lymph'>Enlargement of lymph nodes</label><input id='lymph' type='checkbox' name='symptoms' value='Enlargement of lymph nodes' /></li>
                            <li><label htmlFor='rash'>Rash</label><input id='rash' type='checkbox' name='symptoms' value='Rash' /></li>
                            <li><label htmlFor='fever'>Fever above 38 Celcius (100.5F)</label><input id='fever' type='checkbox' name='symptoms' value='Fever above 38 Celcius (100.5F)' /></li>
                            <li><label htmlFor='breathing'>Difficulty breathing</label><input id='breathing' type='checkbox' name='symptoms' value='Difficulty breathing' /></li>
                            <li><label htmlFor='soreThroat'>Sore throat</label><input id='soreThroat' type='checkbox' name='symptoms' value='Sore throat' /></li>
                            <li><label htmlFor='smell'>Lack of smell</label><input id='smell' type='checkbox' name='symptoms' value='Lack of smell' /></li>
                            <li><label htmlFor='taste'>Lack of taste</label><input id='taste' type='checkbox' name='symptoms' value='Lack of taste' /></li>
                            <li><label htmlFor='dryCough'>Dry cough</label><input id='dryCough' type='checkbox' name='symptoms' value='Dry cough' /></li>
                            <li><label htmlFor='dryMouth'>Dry mouth</label><input id='dryMouth' type='checkbox' name='symptoms' value='Dry mouth' /></li>
                        </ul>
                        <button className='submit-btn btn btn-primary' type='submit'>Submit</button>
                    </form>
                </div>
            )
        } else {
            return (
                <table className="risk-score-results-container">
                    <tr>
                        <h1>Patient's Risk Score</h1>
                        <Score
                            value={Math.floor(this.state.response.finalRiskScore * 100)}
                            maxValue={100}
                            borderWidth={20}
                            gap={5}
                            maxAngle={260}
                            rotation={90}
                            stepsColors={[
                                '#3da940',
                                '#3da940',
                                '#3da940',
                                '#53b83a',
                                '#84c42b',
                                '#f1bc00',
                                '#ed8d00',
                                '#d12000',
                            ]}
                        />
                        <hr />
                    </tr>
                    <br />
                    <tr>
                        <h1>Patient's Symptoms Radar Chart</h1>
                        <img className='graphic-container' alt='Patient symptoms radar chart'
                             src={SERVER_URL + '/image/getPatientSymptomsRadarChart?illness=COVID19&symptoms=' + this.state.patientData.symptoms} />
                        <hr />
                    </tr>
                    <br />
                    <tr>
                        <h1>Patient's Symptoms Versus COVID-19 Positive Patients Radar Chart</h1>
                        <img className='graphic-container' alt='Patient symptoms versus COVID19 positive patients radar chart'
                             src={SERVER_URL + '/image/getDoubleSeriesRadarChart?illness=COVID19&symptoms=' + this.state.patientData.symptoms} />
                        <hr />
                    </tr>
                    <br />
                    <tr>
                        <h1>Risk Score for each Patient Symptom</h1>
                        <table className="symptomsTable">
                            <thead>
                            <tr>
                                <th>Patient's Symptom</th>
                                <th>Symptom Risk Score</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.response.yourPatientSymptomsList.map((symptom) => {
                                    return Object.keys(symptom).map((key, value) => {
                                        return <tr>
                                            <td>{key}</td>
                                            <td>{symptom[key].toFixed(2)}</td>
                                        </tr>
                                    })
                                })
                            }
                            </tbody>
                        </table>
                        <hr />
                    </tr>
                    <br />
                    <tr>
                        <h1>Patient's Risk Score Details</h1>
                        <table className="riskTable">
                            <thead>
                            <tr>
                                <th>Max Possible Symptom Score</th>
                                <th>Your Patient Symptom Score</th>
                                <th>Final Risk Score</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>{this.state.response.maxPossibleSymptomScore.toFixed(2)}</td>
                                <td>{this.state.response.yourPatientSymptomScore.toFixed(2)}</td>
                                <td>{this.state.response.finalRiskScore.toFixed(3)}</td>
                            </tr>
                            </tbody>
                        </table>
                    </tr>
                    <tr>
                        <button className='reset-button btn btn-primary' onClick={() => this.setState({response: null})}>Get Another Risk Score</button>
                    </tr>
                </table>
            )
        }
    }
}
