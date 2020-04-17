import React, {Component} from 'react';

export default class OpenEndedQuestion extends Component{
    render() {
        return[
            <div>
                <label for={this.props.id}>{this.props.question}</label><input id={this.props.id} type='text'/>
            </div>
        ]
    }
}
