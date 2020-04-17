import React, {Component} from 'react';

export default class MultipleChoiceRadioQuestion extends Component {
    render() {
        return [
            <div>
                <div>{this.props.question}</div>
                {this.props.answers.map(answer => {
                    return <div>
                        <div>{answer.answer}</div>
                        <input type='radio' id={answer.id} name={answer.answerGroup} value={answer} /><br /></div>
                })}
            </div>
        ]
    }
}
