import {applyMiddleware, combineReducers, createStore} from 'redux'
import thunk from 'redux-thunk'

import * as reducers from '../reducers'

const rootReducer = combineReducers({
    ...reducers
})

const configureStore = () => {
    return createStore(rootReducer, applyMiddleware(thunk))
}

export default configureStore
