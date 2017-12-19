import {ACCOUNTS_SEARCH, ACCOUNTS_SEARCHED} from './actionTypes'
import api from '../../http/api'

const request = () => ({
    type: ACCOUNTS_SEARCH
});

const receive = payload => ({
    type: ACCOUNTS_SEARCHED,
    payload
});

export const findAll = (params) => {
    console.debug("findAll req: %s", JSON.stringify(params));
    return (dispatch) => {
        dispatch(request());
        api.get(`/account/users`, {params: params})
            .then(resp => dispatch(receive(resp.data)))
            .catch(err => {
                console.log(err)
            })
    }
};


