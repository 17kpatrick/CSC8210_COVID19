import React, {Component} from 'react';

export default class MultipleChoiceCheckboxQuestion extends Component {

    onCheckboxChange = (answerGroup, answer, e) =>{
        let checkboxResultsRef = this.props.checkboxResults;

        if (!checkboxResultsRef[answerGroup]){
            checkboxResultsRef[answerGroup] = []
        }

        if (e.target.checked) {
            checkboxResultsRef[answerGroup].push(answer)
        } else {
            let oldValues = checkboxResultsRef[answerGroup];
            checkboxResultsRef[answerGroup] = [];
            oldValues.filter(value => {
                if (value !== answer) {
                    checkboxResultsRef[answerGroup].push(value)
                }
            });
        }
    };

    render() {
        return [
            <div>
                <div>{this.props.question}</div>
                {this.props.answers.map(answer => {
                    return <div>
                        <div>{answer.answer}</div>
                        <input type='checkbox' id={answer.id} name={answer.answerGroup} value={answer.answer} onClick={(e) => this.onCheckboxChange(answer.answerGroup, answer.answer, e)} /><br /></div>
                })}
            </div>
        ]
    }
}
