import {
    ACCOUNT_DETAIL_EXPIRED, ACCOUNT_DETAIL_MODIFIED, ACCOUNTS_SEARCH,
    ACCOUNTS_SEARCHED
} from "../../actions/account/actionTypes";

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
        case ACCOUNT_DETAIL_MODIFIED:
            let modifiedId = action.payload.content;
            let targetRow = state.payload.content.find(row => {
                return row.id === modifiedId
            });

            targetRow.name = action.params.name;
            targetRow.status = action.params.status;

            state.payload.content = state.payload.content.map((row) => row.id === modifiedId ? targetRow : row);
            return {
                ...state
            };
        case ACCOUNT_DETAIL_EXPIRED:
            state.payload.content = state.payload.content.filter((row) => row.id !== action.payload.content);
            return {
                ...state
            };
        default:
            return state
    }
}
