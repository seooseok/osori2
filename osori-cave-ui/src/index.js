import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux'
import {Route, Router} from 'react-router-dom'
//Customize css for app
import './assets/css/AdminLTE.min.css'
import './index.css';

import App from './pages/App';
import registerServiceWorker from './registerServiceWorker';

import configureStore from './store/configureStore'
import createBrowserHistory from 'history/createBrowserHistory'

const history = createBrowserHistory()
const store = configureStore({
    foo: 'bar'
})

ReactDOM.render(
    <Provider store={store}>
        <Router history={history}>
            <Route path="/" component={App}/>
        </Router>
    </Provider>,
    document.getElementById('root')
);

registerServiceWorker();
