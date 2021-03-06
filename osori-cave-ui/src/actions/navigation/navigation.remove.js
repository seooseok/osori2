import {NAVIGATION_REMOVE, NAVIGATION_REMOVED} from "./actionTypes";
import api from "../../http/api";


const request = () => ({
    type: NAVIGATION_REMOVE
});

const receive = (payload) => ({
    type: NAVIGATION_REMOVED,
    payload: payload
});

export const remove = (uri) => {
    console.debug("remove req: %s", uri);
    return (dispatch) => {
        dispatch(request());
        api.delete(uri, {})
            .then(resp => dispatch(receive(resp.data)))
            .catch(err => {
                console.log(err)
            })
    }
};
