import {ACCOUNT_DETAIL_MODIFIED, ACCOUNT_DETAIL_MODIFY} from "./actionTypes";
import Axios from 'axios'

const request = () => ({
    type: ACCOUNT_DETAIL_MODIFY
});

const receive = (params, payload) => ({
    type: ACCOUNT_DETAIL_MODIFIED,
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
