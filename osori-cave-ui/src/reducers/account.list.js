import {ACCOUNTS_SEARCH, ACCOUNTS_SEARCHED} from "../actions/account/actionTypes";

const initial = {
    payload: undefined
};

export default (state = initial, action) => {
    switch (action.type) {
        case ACCOUNTS_SEARCH:
            return {
                ...state
            };
        case ACCOUNTS_SEARCHED:
            return {
                ...state,
                payload: action.payload
            };
        default:
            return state
    }
}
