import React, {Component} from 'react';
import {CLIENT_VERSION, REACT_VERSION, SERVER_URL} from './config';
import 'whatwg-fetch';
import MultipleChoiceQuestion from './MultipleChoiceQuestion'
import OpenEndedQuestion from './OpenEndedQuestion'

class App extends Component {

    state = {
      examinationQuestionJson: [],
      clientInfo: {
        version: CLIENT_VERSION,
        react: REACT_VERSION
      },
      collapse: false
    }

    toggle = () => {
        this.setState({collapse: !!this.state.collapse})
    }

    componentDidMount() {
        fetch(SERVER_URL + '/examination/getQuestions')
            .then(r => r.json())
            .then(json => this.setState({examinationQuestionJson: json}))
            .catch(error => console.error('Error connecting to server: ' + error));

    }

    render() {
        const {serverInfo, clientInfo, collapse} = this.state;
        return [
            this.state.examinationQuestionJson.map(question => {
                switch (question.questionType) {
                    case "MULTIPLE_CHOICE":
                        return <div><MultipleChoiceQuestion question={question.question} answers={question.answers} /><br/></div>
                    case "OPEN_ENDED":
                        return <div><OpenEndedQuestion question={question.question} /><br/></div>
                    default:
                        return <div>No questions were found, please contact the administrator.</div>
                }
            })
        ];
    }
}

export default App;
