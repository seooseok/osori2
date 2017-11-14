import {ACCOUNT_DETAIL_FIND, ACCOUNT_DETAIL_FOUND} from "../actions/account/actionTypes";

const initial = {
    payload: undefined
};

export default (state = initial, action) => {
    switch (action.type) {
        case ACCOUNT_DETAIL_FIND:
            return {
                ...state,
                payload: undefined
            };
        case ACCOUNT_DETAIL_FOUND:
            return {
                ...state,
                payload: action.payload
            };
        default:
            return state
    }
}
