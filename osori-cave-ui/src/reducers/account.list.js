import {SEARCH_ACCOUNTS, SEARCHED_ACCOUNTS} from "../actions/account/actionTypes";

const initial = {
    data: []
};

function list(state = initial, action) {
    switch (action.type) {
        case SEARCH_ACCOUNTS:
            return {
                ...state
            };
        case SEARCHED_ACCOUNTS:
            return {
                ...state,
                data: action.payload
            };
        default:
            return state
    }
}

export default list;
