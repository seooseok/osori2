import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux'
import {Route, Router} from 'react-router-dom'
//Customize css for app
import './assets/bootstrap/css/bootstrap.min.css'

import './assets/font-awesome/css/font-awesome.min.css'
import './assets/Ionicons/css/ionicons.min.css'

import './assets/lte/css/AdminLTE.min.css'
import './assets/lte/css/skins/_all-skins.min.css'
import './index.css';
//Extend js for app
import 'jquery/src/jquery'
import 'adminlte-reactjs/public/bootstrap/js/bootstrap.min'
import 'adminlte-reactjs/public/dist/js/app'
import 'react-bootstrap-table/dist/react-bootstrap-table-all.min.css'
//Default js for app
import App from './pages/App';
import registerServiceWorker from './registerServiceWorker';

import configureStore from './store/configureStore'
import createBrowserHistory from 'history/createBrowserHistory'

const history = createBrowserHistory();

const store = configureStore({
    foo: 'bar'
});


ReactDOM.render(
    <Provider store={store}>
        <Router history={history}>
            <Route path="/" component={App}/>
        </Router>
    </Provider>,
    document.getElementById('root')
);

registerServiceWorker();
