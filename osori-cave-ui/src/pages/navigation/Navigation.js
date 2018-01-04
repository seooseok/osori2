import React from 'react'
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'
import ContentNav from '../components/ContentNav'
import SortableTree, {addNodeUnderParent, getTreeFromFlatData, removeNodeAtPath} from 'react-sortable-tree'

import {NavigationModal} from "."
import {findAll} from "../../actions/navigation/navigation.list";
import {remove} from "../../actions/navigation/navigation.remove";


import './navigation.css'

const getNodeKey = ({treeIndex}) => treeIndex;

class Navigation extends React.Component {
    constructor(props) {
        super(props);

        this.props.findAll();

        this.state = {
            isOpenModal: false,
            treeData: [],
        };
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.payload !== undefined) {
            this.setState({
                treeData: getTreeFromFlatData({
                    flatData: nextProps.payload.content.map(node => ({
                        ...node,
                        title: node.name,
                        subtitle: node.fullUri
                    })),
                    getKey: node => node.id,
                    getParentKey: node => node.parentId,
                    rootKey: null
                })
            });
        }
    }


    onAdded = (formData, modalData) => {
        let path = modalData.nodePath;

        if (formData.getName) {
            this.setState(state => ({
                treeData: addNodeUnderParent({
                    treeData: state.treeData,
                    parentKey: path[path.length - 1],
                    expandParent: true,
                    getNodeKey,
                    newNode: {
                        title: formData.getName,
                        subtitle: modalData.parentNode.resource + formData.resource,
                        methodType: 'GET',
                        depthType: formData.depthType
                    },
                }).treeData,
            }))
        }

        if (formData.putName) {
            this.setState(state => ({
                treeData: addNodeUnderParent({
                    treeData: state.treeData,
                    parentKey: path[path.length - 1],
                    expandParent: true,
                    getNodeKey,
                    newNode: {
                        title: formData.putName,
                        subtitle: modalData.parentNode.resource + formData.resource,
                        methodType: 'PUT',
                        depthType: formData.depthType
                    },
                }).treeData,
            }))
        }

        if (formData.postName) {
            this.setState(state => ({
                treeData: addNodeUnderParent({
                    treeData: state.treeData,
                    parentKey: path[path.length - 1],
                    expandParent: true,
                    getNodeKey,
                    newNode: {
                        title: formData.postName,
                        subtitle: modalData.parentNode.resource + formData.resource,
                        methodType: 'POST',
                        depthType: formData.depthType
                    },
                }).treeData,
            }))
        }

        if (formData.deleteName) {
            this.setState(state => ({
                treeData: addNodeUnderParent({
                    treeData: state.treeData,
                    parentKey: path[path.length - 1],
                    expandParent: true,
                    getNodeKey,
                    newNode: {
                        title: formData.deleteName,
                        subtitle: modalData.parentNode.resource + formData.resource,
                        methodType: 'DELETE',
                        depthType: formData.depthType
                    },
                }).treeData,
            }))
        }

        this.onOffModal();
    };

    onModified = (formData, modalData) => {

    };

    onOffModal = () => {
        this.setState({
            isOpenModal: !this.state.isOpenModal,
        })
    };


    addChildren = (parentNode, path) => {
        this.onOffModal();
        this.setState({
            modalData: {
                name: "New child privilege URL",
                component: "AddChildrenForm",
                parentNode: parentNode,
                nodePath: path
            }
        })
    };

    modifyChild = (node, path) => {
        this.onOffModal();
        this.setState({
            modalData: {
                name: "Modify privilege URL",
                component: "ModifyForm",
                node: node,
                nodePath: path
            }
        })
    };

    remove = (node, path) => {
        let url = node.links.find(item => {
            return item.rel === 'self'
        }).href;

        if (url === undefined) {
            console.err('can\'t find remove url. links: ', JSON.stringify(this.props.detail.links));
        }

        this.props.remove(url);

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
                                            case 'GET':
                                                labelColor = 'label-primary';
                                                break;
                                            case 'POST':
                                                labelColor = 'label-success';
                                                break;
                                            case 'PUT':
                                                labelColor = 'label-warning';
                                                break;
                                            case 'DELETE':
                                                labelColor = 'label-danger';
                                                break;
                                            default:
                                                labelColor = 'label-info'
                                        }

                                        return ({
                                            buttons: [
                                                <span
                                                    className="label label-info">{node.depthType && node.depthType.substring(0, 1)}</span>,
                                                <span className={"label " + labelColor}>{node.methodType}</span>,
                                                <div className="btn-group">
                                                    <button type="button" className="btn btn-default btn-xs"
                                                            onClick={(e) => {
                                                                e.preventDefault();
                                                                this.modifyChild(node, path)
                                                            }}>Modify
                                                    </button>
                                                    <button type="button"
                                                            className="btn btn-default btn-xs dropdown-toggle"
                                                            data-toggle="dropdown" aria-expanded="false">
                                                        <span className="caret"></span>
                                                        <span className="sr-only">Toggle Dropdown</span>
                                                    </button>
                                                    <ul className="dropdown-menu" role="menu">
                                                        <li>
                                                            <a onClick={(e) => {
                                                                   e.preventDefault();
                                                                this.addChildren(node, path)
                                                               }}>Add Child
                                                            </a>
                                                        </li>
                                                        <li className="divider"></li>
                                                        <li>
                                                            <a onClick={(e) => {
                                                                   e.preventDefault();
                                                                this.remove(node, path)
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
                <NavigationModal show={this.state.isOpenModal}
                                 modalData={this.state.modalData}
                                 onAdded={this.onAdded}
                                 onModified={this.onModified}
                                 onClose={this.onOffModal}/>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    isFetching: state.navigationList.isFetching,
    payload: state.navigationList.payload
});

const mapDispatchToProps = (dispatch) => bindActionCreators({findAll, remove}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Navigation)

