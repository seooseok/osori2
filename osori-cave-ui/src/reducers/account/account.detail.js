import {
    ACCOUNT_DETAIL_FIND,
    ACCOUNT_DETAIL_FOUND,
    ACCOUNT_DETAIL_MODIFIED,
    ACCOUNT_DETAIL_MODIFY
} from "../../actions/account/actionTypes";

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
        case ACCOUNT_DETAIL_MODIFY:
            return {
                ...state,
            };
        case ACCOUNT_DETAIL_MODIFIED:
            return {
                ...state,
            };
        default:
            return state
    }
}

