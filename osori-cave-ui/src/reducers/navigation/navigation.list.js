import {
    NAVIGATION_ADD_ALL, NAVIGATION_ADDED_ALL, NAVIGATION_FIND, NAVIGATION_FOUND, NAVIGATION_MODIFIED,
    NAVIGATION_MODIFY, NAVIGATION_REMOVE, NAVIGATION_REMOVED
} from "../../actions/navigation/actionTypes"

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
        case NAVIGATION_ADD_ALL:
            return {
                ...state,
                isFetching: true
            };
        case NAVIGATION_ADDED_ALL:
            state.payload.content = state.payload.content.concat(action.payload.content);

            console.debug(state.payload.content);
            return {
                ...state,
                isFetching: false
            };
        case NAVIGATION_MODIFY:
            return {
                ...state,
                isFetching: true
            };
        case NAVIGATION_MODIFIED:
            let modifiedId = action.payload.content;
            let targetNode = state.payload.content.find(row => {
                return row.id === modifiedId
            });

            targetNode.name = action.params.name;
            targetNode.fullUri = targetNode.fullUri.replace(targetNode.resource, action.params.resource);
            targetNode.resource = action.params.resource;
            targetNode.depthType = action.params.depthType;
            targetNode.methodType = action.params.methodType;

            state.payload.content = state.payload.content.map((row) => row.id === modifiedId ? targetNode : row);
            return {
                ...state,
                isFetching: false
            };
        case NAVIGATION_REMOVE:
            return {
                ...state,
                isFetching: true
            };
        case NAVIGATION_REMOVED:
            state.payload.content = state.payload.content.filter((row) => row.id !== action.payload.content);
            return {
                ...state,
                isFetching: false
            };

        default:
            return state
    }
}
