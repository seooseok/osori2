import {ACCOUNT_DETAIL_EXPIRE, ACCOUNT_DETAIL_EXPIRED} from "./actionTypes";
import Axios from 'axios'

const request = () => ({
    type: ACCOUNT_DETAIL_EXPIRE
});

const receive = payload => ({
    type: ACCOUNT_DETAIL_EXPIRED
});


export const expireOne = (url) => {
    return (dispatch) => {
        dispatch(request());
        Axios.delete(url, {headers: {'Content-Type': 'application/json'}})
            .then(resp => dispatch(receive(resp.data)))
            .catch(err => {
                console.log(err)
            })
    }
};
