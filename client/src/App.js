import React, {Component} from 'react';
import {CLIENT_VERSION, REACT_VERSION, SERVER_URL} from './config';
import 'whatwg-fetch';
import MultipleChoiceRadioQuestion from './MultipleChoiceRadioQuestion'
import OpenEndedQuestion from './OpenEndedQuestion'
import MultipleChoiceCheckboxQuestion from "./MultipleChoiceCheckboxQuestion";


class App extends Component {

    state = {
        examinationQuestionJson: [],
        clientInfo: {
            version: CLIENT_VERSION,
            react: REACT_VERSION
        },
        collapse: false,
        similaritiesBetweenPatientAndOtherCases: null
    };

    toggle = () => {
        this.setState({collapse: !!this.state.collapse})
    };

    getSimilaritiesBetweenPatientAndOtherCases = (illness, symptoms) =>{
        fetch(SERVER_URL + 'examination/getPatientSimilarities?illness=' + illness + '&symptoms=' + symptoms)
            .then(r => r.json())
            .then(json => this.setState({similaritiesBetweenPatientAndOtherCases: json.message}))
            .catch(error => console.error('Error connecting to server: ' + error));
    };


    componentDidMount() {
        fetch(SERVER_URL + '/examination/getQuestions')
            .then(r => r.json())
            .then(json => this.setState({examinationQuestionJson: json}))
            .catch(error => console.error('Error connecting to server: ' + error));
    }


    render() {
        const {serverInfo, clientInfo, collapse} = this.state;
        return [
            <div>
                {this.state.examinationQuestionJson.map(question => {
                    switch (question.questionType) {
                        case "MULTIPLE_CHOICE_RADIO":
                            return <div><MultipleChoiceRadioQuestion question={question.question} answers={question.answers} /><br/></div>;
                        case "MULTIPLE_CHOICE_CHECKBOX":
                            return <div><MultipleChoiceCheckboxQuestion question={question.question} answers={question.answers} /><br/></div>;
                        case "OPEN_ENDED":
                            return <div><OpenEndedQuestion question={question.question} /><br/></div>;
                        default:
                            return <div>No questions were found, please contact the administrator.</div>
                    }
                })}
                <button type='submit' onClick={() => this.getSimilaritiesBetweenPatientAndOtherCases('COVID19', 'test1,test2,test3')}>Submit</button>
                <div>{this.state.similaritiesBetweenPatientAndOtherCases}</div>
            </div>
        ];
    }
}


export default App;
