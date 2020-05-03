import React, {Component} from 'react';
import {CLIENT_VERSION, REACT_VERSION, SERVER_URL} from './config';

export default class Analytics extends Component {
    render() {
        return (
            <div>
                <br/><br/><br/><br/>
                <h1>All Patient Symptoms Bar Chart</h1>
                <img className='graphic-container' alt='Patient symptoms radar chart'
                     src={SERVER_URL + '/image/getAllSymptomsBarChart?illness=COVID19'}/>
            </div>
        );
    }
}
