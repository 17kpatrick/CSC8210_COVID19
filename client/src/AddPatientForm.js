import React, {Component} from 'react';
import {CLIENT_VERSION, REACT_VERSION, SERVER_URL} from "./config";

export default class AddPatientForm extends Component {

    state = {
        clientInfo: {
            version: CLIENT_VERSION,
            react: REACT_VERSION
        },
        patientData: {
            firstName: null,
            lastName: null,
            age: null,
            gender: null,
            symptoms: []
        },
        response: null
    };


    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    handleSubmit = (event) => {
        for (let i = 0; i < event.target.length; i++) {
            if ((event.target[i].outerHTML.indexOf('checkbox') !== -1 && event.target[i].checked) || event.target[i].outerHTML.indexOf('checkbox') === -1 && event.target[i].value) {
                if (event.target[i].name === 'symptoms') {
                    this.state.patientData.symptoms.push(event.target[i].value)
                } else {
                    this.state.patientData[event.target[i].name] = event.target[i].value
                }
            }
        }

        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(this.state.patientData)
        };

        fetch(SERVER_URL + '/examination/addPatient', requestOptions)
            .then(r => r.json())
            .then(json => {
                if (json.message !== "Success") {
                    throw Error("The patient was not saved successfully. Please try again.")
                } else {
                    alert("Your patient has been saved successfully.");
                    this.props.history.push('/');
                    event.preventDefault();
                }
            })
            .catch(error => {
                alert(error)
                event.preventDefault();
            });

        event.preventDefault();
    };


    render() {
        return (
            <div className='add-patient-container shadow-lg'>
                <form onSubmit={this.handleSubmit}>
                    <div className='prompt'>Please enter the data for your COVID-19 positive patient.</div>
                    <hr/>
                    <table className='add-patient-input-container'>
                        <tr>
                            <td><label htmlFor="firstName">First Name</label></td>
                            <td><input type='text' name='firstName' id='firstName' /></td>
                        </tr>
                        <tr>
                            <td><label htmlFor="lastName">Last Name</label></td>
                            <td><input type='text' name='lastName' id='lastName' /></td>
                        </tr>
                        <tr>
                            <td><label htmlFor="age">Age</label></td>
                            <td><input type='number' name='age' id='age' /></td>
                        </tr>
                        <tr>
                            <td><label htmlFor='gender'>What is your gender?</label></td>
                            <td><input type='text' list='genderList' id='gender' name='gender' /></td>
                            <datalist id='genderList'>
                                <option value="Male" />
                                <option value="Female" />
                                <option value="Do Not Wish to Disclose" />
                            </datalist>
                            <hr/>
                        </tr>
                    </table>
                    <hr/>
                    <div className='prompt'>Select all of the symptoms which apply to your COVID-19 positive patient.</div>
                    <hr/>
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
        );
    }
}
