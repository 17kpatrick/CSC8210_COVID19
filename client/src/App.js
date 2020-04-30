import React, {Component} from 'react';
import {CLIENT_VERSION, REACT_VERSION, SERVER_URL} from './config';
import 'whatwg-fetch';
import AddPatientForm from "./AddPatientForm";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import GetPatientRiskScore from "./GetPatientRiskScore";
import Analytics from "./Analytics";
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
            <Router>
                <div>
                    <nav>
                        <ul>
                            <li>
                                <Link to="/addPatient">Add Patient</Link>
                            </li>
                            <li>
                                <Link to="/getPatientRisk">Get Patient Risk Score</Link>
                            </li>
                            <li>
                                <Link to="/analytics">Get Patient Analytics</Link>
                            </li>
                        </ul>
                    </nav>

                    {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
                    <Switch>
                        <Route path="/addPatient" component={AddPatientForm}/>
                    </Switch>
                    <Switch>
                        <Route path="/getPatientRisk" component={GetPatientRiskScore}/>
                    </Switch>
                    <Switch>
                        <Route path="/analytics" component={Analytics}/>
                    </Switch>
                </div>
            </Router>
        ];
    }
}


export default App;
