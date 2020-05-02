import React, {Component} from 'react';
import {CLIENT_VERSION, REACT_VERSION, SERVER_URL} from './config';

export default class Analytics extends Component {
    render() {
        return (
            <div>
                <p>
                    <br/><br/><br/>
                    This is the analytics page.
                    <br/><br/><br/>
                    <img
                        src="http://localhost:8080/image/getDoubleSeriesRadarChart?illness=COVID19&symptoms=Headache,Sore throat,Lack of Taste,Difficulty breathing,Sputum Production,Lack of Smell,Rash"></img>
                </p>
            </div>
        );
    }
}
