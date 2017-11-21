import React from 'react'
import ContentNav from '../components/ContentNav'

import './navigation.css'

class Navigation extends React.Component {
    render() {
        return (
            <div>
                <ContentNav category="Navigation" name="Navigation Configuration"
                            description="Manage URLs to verify permissions by tree structure"/>
                <section className="content">
                    <div className="col-md-7">
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h3 class="box-title">URL Tree Management</h3>
                            </div>
                            <div class="box-body">
                                The great content goes here
                            </div>
                        </div>
                    </div>
                    <div className="col-md-5">
                        <div class="box box-success">
                            <div class="box-header with-border">
                                <h3 class="box-title">Blank Box</h3>
                            </div>
                            <div class="box-body">
                                The great content goes here
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        )
    }
}

export default Navigation

Navigation.defaultProps = {
    treeData: [
        {
            "name": "Home",
            "resource": "",
            "depthType": "NAVI",
            "methodType": "GET",
            "id": 1,
            "parentId": null,
            "viewId": null,
            "viewParentId": null,
            "fullUri": "",
            "sorting": 0
        },
        {
            "name": "Account",
            "resource": "/account",
            "depthType": "NAVI",
            "methodType": "GET",
            "id": 2,
            "parentId": 1,
            "viewId": null,
            "viewParentId": null,
            "fullUri": "/account",
            "sorting": 0
        },
        {
            "name": "Show Users",
            "resource": "/users",
            "depthType": "FUNC",
            "methodType": "GET",
            "id": 3,
            "parentId": 2,
            "viewId": null,
            "viewParentId": null,
            "fullUri": "/account/users",
            "sorting": 0
        },
        {
            "name": "Show User Info",
            "resource": "/user/{id}",
            "depthType": "FUNC",
            "methodType": "GET",
            "id": 4,
            "parentId": 2,
            "viewId": null,
            "viewParentId": null,
            "fullUri": "/account/user/{id}",
            "sorting": 0
        },
        {
            "name": "Modify User",
            "resource": "/user/{id}",
            "depthType": "FUNC",
            "methodType": "PUT",
            "id": 5,
            "parentId": 2,
            "viewId": null,
            "viewParentId": null,
            "fullUri": "/account/user/{id}",
            "sorting": 0
        },
        {
            "name": "Expire User",
            "resource": "/user/{id}",
            "depthType": "FUNC",
            "methodType": "DELETE",
            "id": 6,
            "parentId": 2,
            "viewId": null,
            "viewParentId": null,
            "fullUri": "/account/user/{id}",
            "sorting": 0
        },
        {
            "name": "Navigation",
            "resource": "/navigation",
            "depthType": "NAVI",
            "methodType": "GET",
            "id": 7,
            "parentId": null,
            "viewId": null,
            "viewParentId": null,
            "fullUri": "/navigation",
            "sorting": 0
        },
        {
            "name": "Show Url Tree",
            "resource": "/nodes",
            "depthType": "FUNC",
            "methodType": "GET",
            "id": 8,
            "parentId": 7,
            "viewId": null,
            "viewParentId": null,
            "fullUri": "/navigation/nodes",
            "sorting": 0
        }
    ]
};
