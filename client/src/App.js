import React, {Component} from 'react';
import {CLIENT_VERSION, REACT_VERSION, SERVER_URL} from './config';
import 'whatwg-fetch';
import AddPatientForm from "./AddPatientForm";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    NavLink
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
                    <nav className='nav-bar'>
                      <table>
                        <tr>
                          <td>
                            <div className="nav-button">
                            <NavLink activeClassName='is-active' to="/addPatient">Add Patient</NavLink>
                            </div>
                          </td>
                          <td>
                            <div className="nav-button">
                            <NavLink activeClassName='is-active' to="/getPatientRisk">Get Patient Risk Score</NavLink>
                            </div>
                          </td>
                          <td>
                            <div className="nav-button">
                            <NavLink activeClassName='is-active' to="/analytics">Get Patient Analytics</NavLink>
                            </div>
                          </td>
                        </tr>
                      </table>
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
