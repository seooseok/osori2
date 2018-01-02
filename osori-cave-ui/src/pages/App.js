import React, {Component} from 'react'
import {Route, Switch} from 'react-router-dom'

import {MainLayout} from './components'
import {Home} from './home'

import {Account} from './account'
import {Privilege} from './privilege'
import {Navigation} from './navigation'


import './App.css';


class App extends Component {
    render() {
        return (
            <Switch>
                <MainLayout exact path="/account" component={Account}/>
                <MainLayout exact path="/privilege" component={Privilege}/>
                <MainLayout exact path="/navigation" component={Navigation}/>
                <MainLayout exact path="/" component={Home}/>
                <Route render={() => <div style={{padding: '50px'}}>404: Page Not Found</div>}/>
            </Switch>
        );
    }
}

export default App;
