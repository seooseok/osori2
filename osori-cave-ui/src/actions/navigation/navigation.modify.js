import Axios from 'axios'
import {NAVIGATION_MODIFIED, NAVIGATION_MODIFY} from "./actionTypes";

const request = () => ({
    type: NAVIGATION_MODIFY
});

const receive = (params, payload) => ({
    type: NAVIGATION_MODIFIED,
    params: params,
    payload: payload
});

export const modifyOne = (url, params) => {
    console.debug("modifyOne req: %s", JSON.stringify(params));
    return (dispatch) => {
        dispatch(request());
        Axios.put(url, params, {headers: {'Content-Type': 'application/json'}})
            .then(resp => dispatch(receive(params, resp.data)))
            .catch(err => {
                console.log(err)
            })
    }
};
