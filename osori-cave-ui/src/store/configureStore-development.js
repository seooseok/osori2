import {applyMiddleware, combineReducers, createStore} from 'redux'
import thunk from 'redux-thunk'
import {createLogger} from 'redux-logger'

import * as reducers from '../reducers'

const logger = createLogger({
    level: 'info',
    collapsed: true
});

const rootReducer = combineReducers({
    ...reducers
});

const configureStore = () => {
    return createStore(rootReducer, applyMiddleware(thunk, logger))
};

export default configureStore
