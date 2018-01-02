import {NAVIGATION_ADD_ALL, NAVIGATION_ADDED_ALL} from "./actionTypes";
import api from '../../http/api'

const request = () => ({
    type: NAVIGATION_ADD_ALL
});

const receive = (params, payload) => ({
    type: NAVIGATION_ADDED_ALL,
    params: params,
    payload
});


export const addAll = (params) => {
    console.debug("addChildren req: %s", JSON.stringify(params));
    return (dispatch) => {
        dispatch(request());
        api.post(`/navigation-tree/nodes`, params)
            .then(resp => dispatch(receive(params, resp.data)))
            .catch(err => {
                console.log(err)
            })
    }
};
