import {ACCOUNT_DETAIL_MODIFIED, ACCOUNT_DETAIL_MODIFY} from "./actionTypes";
import Axios from 'axios'

const request = () => ({
    type: ACCOUNT_DETAIL_MODIFY
});

const receive = payload => ({
    type: ACCOUNT_DETAIL_MODIFIED
});


export const modifyOne = (url, json) => {
    return (dispatch) => {
        dispatch(request());
        Axios.put(url, json, {headers: {'Content-Type': 'application/json'}})
            .then(resp => dispatch(receive(resp.data)))
            .catch(err => {
                console.log(err)
            })
    }
};
