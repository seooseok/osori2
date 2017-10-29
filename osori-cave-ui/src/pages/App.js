import React, {Component} from 'react'
import {Route, Switch} from 'react-router-dom'

import {Home} from './home'
import {MainLayout} from './components'

import './App.css';


class App extends Component {
    render() {
        return (
            <Switch>
                <MainLayout exact path="/" component={Home}/>
                <Route render={() => <div style={{padding: '50px'}}>404: Page Not Found</div>}/>
            </Switch>
        );
    }
}

export default App;
