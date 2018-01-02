import {NAVIGATION_FIND, NAVIGATION_FOUND} from './actionTypes'
import api from '../../http/api'

const request = () => ({
    type: NAVIGATION_FIND
});

const receive = payload => ({
    type: NAVIGATION_FOUND,
    payload
});

export const findAll = () => {
    return (dispatch) => {
        dispatch(request());
        api.get(`/navigation-tree/nodes`, {})
            .then(resp => dispatch(receive(resp.data)))
            .catch(err => {
                console.log(err)
            })
    }
};
