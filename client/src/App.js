import React, {Component} from 'react';
import {CLIENT_VERSION, REACT_VERSION, SERVER_URL} from './config';
import 'whatwg-fetch';

class App extends Component {

    state = {
      serverInfo: {},
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
        fetch(SERVER_URL + '/application')
            .then(r => r.json())
            .then(json => this.setState({serverInfo: json}))
            .catch(error => console.error('Error connecting to server: ' + error));

    }

    render() {
        const {serverInfo, clientInfo, collapse} = this.state;

        return [
            <div>Hello world!</div>
        ];
    }
}

export default App;
