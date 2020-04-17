import React, {Component} from 'react';

export default class MultipleChoiceQuestion extends Component {
    render() {
        //TODO need to add id's to questions in JSON
        return [
            <div>
                <div>{this.props.question}</div>
                {this.props.answers.map(answer => {
                    return <div>
                        <div>{answer.answer}</div>
                        <input type='radio' value={answer} /><br /></div>
                })}
            </div>
        ]
    }
}
