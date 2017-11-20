import {
    ACCOUNT_DETAIL_EXPIRE,
    ACCOUNT_DETAIL_EXPIRED,
    ACCOUNT_DETAIL_FIND,
    ACCOUNT_DETAIL_FOUND,
    ACCOUNT_DETAIL_MODIFIED,
    ACCOUNT_DETAIL_MODIFY
} from "../../actions/account/actionTypes";

const initial = {
    isFetching: false,
    payload: undefined
};

export default (state = initial, action) => {
    switch (action.type) {
        case ACCOUNT_DETAIL_FIND:
            return {
                ...state,
                isFetching: true,
                payload: undefined
            };
        case ACCOUNT_DETAIL_FOUND:
            return {
                ...state,
                payload: action.payload,
                isFetching: false
            };
        case ACCOUNT_DETAIL_MODIFY:
            return {
                ...state,
                isFetching: true
            };
        case ACCOUNT_DETAIL_MODIFIED:
            return {
                ...state,
                isFetching: false
            };
        case ACCOUNT_DETAIL_EXPIRE:
            return {
                ...state,
                isFetching: true
            };
        case ACCOUNT_DETAIL_EXPIRED:
            return {
                ...state,
                payload: undefined,
                isFetching: false
            }
        default:
            return state
    }
}

