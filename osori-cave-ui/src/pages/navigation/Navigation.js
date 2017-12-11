import React from 'react'
import ContentNav from '../components/ContentNav'
import SortableTree, {addNodeUnderParent, getTreeFromFlatData, removeNodeAtPath} from 'react-sortable-tree'
import AddChildModal from './AddChildModal'

import './navigation.css'

const getNodeKey = ({treeIndex}) => treeIndex;

class Navigation extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            isOpenAddModal: true,
            treeData: getTreeFromFlatData({
                flatData: this.props.treeData.map(node => ({
                    ...node,
                    title: node.name,
                    subtitle: node.fullUri
                })),
                getKey: node => node.id,
                getParentKey: node => node.parentId,
                rootKey: null
            }),
        };
    }

    toggleAddModal = (path) => {
        this.setState({
            isOpenAddModal: !this.state.isOpenAddModal
        })
    };

    addChild = (path) => {
        this.setState(state => ({
            treeData: addNodeUnderParent({
                treeData: state.treeData,
                parentKey: path[path.length - 1],
                expandParent: true,
                getNodeKey,
                newNode: {
                    title: 'test',
                },
            }).treeData,
        }))
    };

    removeChild = (node, path) => {
        this.setState(state => ({
            treeData: removeNodeAtPath({
                treeData: state.treeData,
                path,
                getNodeKey,
            }),
        }))
    };

    render() {
        return (
            <div>
                <ContentNav category="Navigation" name="Navigation Configuration"
                            description="Manage URLs to verify permissions by tree structure"/>
                <section className="content">
                    <div className="box box-info">
                        <div className="box-header with-border">
                            <h3 className="box-title">URL Tree Management</h3>
                        </div>
                        <div className="box-body">
                            <div style={{height: 500}}>
                                <SortableTree
                                    treeData={this.state.treeData}
                                    onChange={treeData => this.setState({treeData})}
                                    generateNodeProps={({node, path}) => {

                                        let labelColor = '';
                                        switch (node.methodType) {
                                            case 'GET': {
                                                labelColor = 'label-primary';
                                                break;
                                            }
                                            case 'POST': {
                                                labelColor = 'label-success';
                                                break;
                                            }
                                            case 'PUT': {
                                                labelColor = 'label-warning';
                                                break;
                                            }
                                            case 'DELETE': {
                                                labelColor = 'label-danger';
                                                break;
                                            }
                                        }

                                        return ({
                                            buttons: [
                                                <span
                                                    className="label label-default">{node.depthType && node.depthType.substring(0, 1)}</span>,
                                                <span className={"label " + labelColor}>{node.methodType}</span>,
                                                <div className="btn-group">
                                                    <button type="button" className="btn btn-info btn-xs">Detail
                                                    </button>
                                                    <button type="button"
                                                            className="btn btn-info btn-xs dropdown-toggle"
                                                            data-toggle="dropdown" aria-expanded="false">
                                                        <span className="caret"></span>
                                                        <span className="sr-only">Toggle Dropdown</span>
                                                    </button>
                                                    <ul className="dropdown-menu" role="menu">
                                                        <li>
                                                            <a href="#"
                                                               onClick={(e) => {
                                                                   e.preventDefault();
                                                                   this.toggleAddModal(path)
                                                               }}>Add Child
                                                            </a>
                                                        </li>
                                                        <li className="divider"></li>
                                                        <li>
                                                            <a href="#"
                                                               onClick={(e) => {
                                                                   e.preventDefault();
                                                                   this.removeChild(node, path)
                                                               }}>Remove
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            ]
                                        })
                                    }}
                                />
                            </div>
                        </div>
                    </div>
                </section>
                <AddChildModal show={this.state.isOpenAddModal} onClose={this.toggleAddModal}/>
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
            "parentId": 1,
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
