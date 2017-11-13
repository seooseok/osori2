import {SEARCH_ACCOUNTS, SEARCHED_ACCOUNTS} from './actionTypes'
import api from '../../http/api'

const request = () => ({
    type: SEARCH_ACCOUNTS
});

const receive = payload => ({
    type: SEARCHED_ACCOUNTS,
    payload
});


export const fetch = (params) => {
    return (dispatch) => {
        dispatch(request());
        api.get(`/account/users`, {params: params})
            .then(resp => {
                console.debug('search res:' + JSON.stringify(resp.data))
                dispatch(resp => dispatch(receive(resp.data)))
            })
            .catch(err => {
                console.log(err)
            })
    }
};


