import {ACCOUNT_DETAIL_FIND, ACCOUNT_DETAIL_FOUND} from "./actionTypes";
import Axios from 'axios'

const request = () => ({
    type: ACCOUNT_DETAIL_FIND
});

const receive = payload => ({
    type: ACCOUNT_DETAIL_FOUND,
    payload
});


export const findOne = (url) => {
    return (dispatch) => {
        dispatch(request());
        Axios.get(url)
            .then(resp => dispatch(receive(resp.data)))
            .catch(err => {
                console.log(err)
            })
    }
};
