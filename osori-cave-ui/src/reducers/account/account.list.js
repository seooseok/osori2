import {ACCOUNTS_SEARCH, ACCOUNTS_SEARCHED} from "../../actions/account/actionTypes";

const initial = {
    isFetching: false,
    payload: undefined
};

export default (state = initial, action) => {
    switch (action.type) {
        case ACCOUNTS_SEARCH:
            return {
                ...state,
                isFetching: true
            };
        case ACCOUNTS_SEARCHED:
            return {
                ...state,
                isFetching: false,
                payload: action.payload
            };
        default:
            return state
    }
}
