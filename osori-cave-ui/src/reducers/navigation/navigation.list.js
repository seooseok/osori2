import {NAVIGATION_FIND, NAVIGATION_FOUND} from "../../actions/navigation/actionTypes"

const initial = {
    isFetching: false,
    payload: undefined
};

export default (state = initial, action) => {
    switch (action.type) {
        case NAVIGATION_FIND:
            return {
                ...state,
                isFetching: true
            };
        case NAVIGATION_FOUND:
            return {
                ...state,
                isFetching: false,
                payload: action.payload
            };
        default:
            return state
    }
}
