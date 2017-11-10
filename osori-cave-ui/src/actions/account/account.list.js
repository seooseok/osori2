import {SEARCH_ACCOUNTS, SEARCHED_ACCOUNTS} from './actionTypes'
import api from '../../http/api'

export
request = () => ({
    type: SEARCH_ACCOUNTS
});

const receive = payload => ({
    type: SEARCHED_ACCOUNTS,
    payload
});


export const fetch = (params) => {
    return (dispatch) => {
        dispatch(request());
        api.get(`/account/users`, params)
            .then(resp => {
                dispatch(resp => dispatch(receive(resp.data)))
            })
            .catch(err => {
                console.log(err)
            })
    }
};


