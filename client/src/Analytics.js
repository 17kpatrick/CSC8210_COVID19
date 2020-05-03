import React, {Component} from 'react';
import {CLIENT_VERSION, REACT_VERSION, SERVER_URL} from './config';

export default class Analytics extends Component {
    render() {
        return (
            <table className='analytics-container shadow-lg'>
                <tr>
                    <h1>Analytics</h1>
                    <hr/>
                </tr>
                <tr>
                    <h2>COVID-19 Positive Patients' Symptoms Radar Chart</h2>
                    <img className='graphic-container' src={SERVER_URL + "/image/getAllSymptomsRadarChart?illness=COVID19"}/>
                    <hr/>
                </tr>
                <tr>
                    <h2>COVID-19 Positive Patients' Most Reported Symptoms</h2>
                    <img className='graphic-container' src={SERVER_URL + "/image/getAllSymptomsBarChart?illness=COVID19"}></img>
                </tr>
            </table>
        );
    }
}
