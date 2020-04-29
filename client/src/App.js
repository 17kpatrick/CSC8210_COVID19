import React, {Component} from 'react';
import {CLIENT_VERSION, REACT_VERSION, SERVER_URL} from './config';
import 'whatwg-fetch';
import AddPatientForm from "./AddPatientForm";
//import MultipleChoiceRadioQuestion from './MultipleChoiceRadioQuestion'
//import OpenEndedQuestion from './OpenEndedQuestion'
//import MultipleChoiceCheckboxQuestion from "./MultipleChoiceCheckboxQuestion";


class App extends Component {

    state = {
        clientInfo: {
            version: CLIENT_VERSION,
            react: REACT_VERSION
        }
    };


    componentDidMount() {
       // fetch(SERVER_URL + '')
       //     .then(r => r.json())
       //     .then(json => this.setState({x: json}))
       //     .catch(error => console.error('Error connecting to server: ' + error));
    }


    render() {
        const {serverInfo, clientInfo, collapse} = this.state;
        return [
            <AddPatientForm/>
        ];
    }
}


export default App;
