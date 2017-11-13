import {SEARCH_ACCOUNTS, SEARCHED_ACCOUNTS} from "../actions/account/actionTypes";

const initial = {
    payload: undefined
};

export default (state = initial, action) => {
    switch (action.type) {
        case SEARCH_ACCOUNTS:
            return {
                ...state
            };
        case SEARCHED_ACCOUNTS:
            return {
                ...state,
                payload: action.payload
            };
        default:
            return state
    }
}
